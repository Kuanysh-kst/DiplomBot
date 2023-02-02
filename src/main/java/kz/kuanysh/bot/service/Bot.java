package kz.kuanysh.bot.service;

import kz.kuanysh.bot.buttons.ButtonController;
import kz.kuanysh.bot.config.BotConfig;
import kz.kuanysh.bot.factory.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.Sender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


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
