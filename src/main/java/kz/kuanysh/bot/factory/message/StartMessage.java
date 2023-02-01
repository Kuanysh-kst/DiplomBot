package kz.kuanysh.bot.factory.message;

import kz.kuanysh.bot.factory.Sender;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;

import java.util.ArrayList;

public class StartMessage implements Sender {
    @Override
    public BotApiMethod<Message> sendMessage(Message message, String content) {
        String resultText = "Привет " + message.getChat().getFirstName() + content;

        WebAppInfo webAppInfo = new WebAppInfo();
        webAppInfo.setUrl("https://hh.kz/");
        ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();
        replyMarkup.setOneTimeKeyboard(true);
        var keyboard = new ArrayList<KeyboardRow>();
        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();
        firstRow.add("next");
        KeyboardButton about = KeyboardButton.builder()
                .text("О нас \uD83E\uDD16")
                .webApp(webAppInfo)
                .build();
        secondRow.add(about);
        secondRow.add("Реклама \uD83E\uDD41");
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        replyMarkup.setResizeKeyboard(true);
        replyMarkup.setKeyboard(keyboard);
        return SendMessage.builder()
                .chatId(message.getChatId().toString())
                .replyMarkup(replyMarkup)
                .text(resultText)
                .build();
    }
}
