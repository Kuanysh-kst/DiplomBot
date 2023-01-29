package kz.kuanysh.bot;


import kz.kuanysh.bot.factory.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.dialogs.FirstDialog;
import kz.kuanysh.bot.factory.dialogs.SecondDialog;
import kz.kuanysh.bot.factory.factories.SecondDialogFactory;
import kz.kuanysh.bot.factory.factories.StartDialogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {

    Map<Long, Dialog> users = new HashMap<>();
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

            DialogFactory dialogFactory = createDialogFactory(update.getMessage().getText());
            Dialog dialog = dialogFactory.createDialog();
            String text = dialog.getText();
            sendMessage(chatId, text);
            createDialogFactory(update.getMessage().getText());
            users.put(chatId, dialog);
        }
    }

    public static DialogFactory createDialogFactory(String text) {
        if (text.equals("/second")) {
            return new SecondDialogFactory();
        } else {
            return new StartDialogFactory();
        }
    }


    public static BotApiMethod<Message> sendTextMessage(Message message, String text) {
        return SendMessage.builder()
                .chatId(message.getChatId().toString())
                .text(text)
                .build();
    }


    private void sendMessage(Long chatId, String content) {
        ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();
        replyMarkup.setOneTimeKeyboard(true);
        var keyboard = new ArrayList<KeyboardRow>();

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add("First button");
        firstRow.add("Second button");
        keyboard.add(firstRow);

        replyMarkup.setKeyboard(keyboard);
        SendMessage message = SendMessage.builder()
                .chatId(chatId.toString())
                .replyMarkup(replyMarkup)
                .text(content)
                .build();

        try {
            execute(message);
        } catch (TelegramApiException e) {
            LOGGER.error("Ошибка ввода имени: ", e);
        }
    }
}
