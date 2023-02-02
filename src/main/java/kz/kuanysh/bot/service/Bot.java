package kz.kuanysh.bot.service;

import kz.kuanysh.bot.buttons.ButtonController;
import kz.kuanysh.bot.config.BotConfig;
import kz.kuanysh.bot.factory.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.Sender;
import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.grizzly.http.util.TimeStamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private final BotConfig botConfig;

    public Bot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotUserName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String userFirstName = update.getMessage().getChat().getFirstName();

            DialogFactory dialogFactory = ButtonController.createDialogFactory(update.getMessage().getText());
            executeMessage(update.getMessage(), dialogFactory);
        } else if (update.hasCallbackQuery()) {

            DialogFactory dialogFactory = ButtonController.createDialogFactory(update.getCallbackQuery().getData());
            executeMessage(update.getCallbackQuery().getMessage(), dialogFactory);
        }
    }

    private void executeMessage(Message message, DialogFactory dialogFactory) {
        Dialog dialog = dialogFactory.createDialog();
        Sender sender = dialogFactory.createSender();
        try {
            var response = sender.sendMessage(message, dialog.getText(message));
            execute(response);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }


}
