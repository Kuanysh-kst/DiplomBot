package kz.kuanysh.bot;

import kz.kuanysh.bot.buttons.ButtonController;
import kz.kuanysh.bot.factory.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {

    Map<Long, DialogFactory> users = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);

    private final String botUserName;

    private final String botToken;

    public Bot(String botUserName, String botToken) {
        this.botUserName = botUserName;
        this.botToken = botToken;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            DialogFactory dialogFactory = ButtonController.createDialogFactory(update.getMessage().getText());
            executeMessage(update.getMessage(), dialogFactory);
        } else if (update.hasCallbackQuery()) {
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            DialogFactory dialogFactory = ButtonController.createDialogFactory(update.getCallbackQuery().getData());
            executeMessage(update.getCallbackQuery().getMessage(), dialogFactory);
        }
    }

    private void executeMessage(Message message, DialogFactory dialogFactory) {
        Dialog dialog = dialogFactory.createDialog();
        Sender sender = dialogFactory.createSender();
        try {
            var response = sender.sendMessage(message, dialog.getText());
            execute(response);
        } catch (TelegramApiException e) {
            LOGGER.error("Ошибка ввода : ", e);
        }
    }
}
