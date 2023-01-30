package kz.kuanysh.bot.factory.message;

import kz.kuanysh.bot.factory.Sender;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

public class StartMessage implements Sender {
    @Override
    public   BotApiMethod<Message> sendMessage(Long chatId, String content) {
        ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();
        replyMarkup.setOneTimeKeyboard(true);
        var keyboard = new ArrayList<KeyboardRow>();

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add("First button");
        firstRow.add("Second button");
        keyboard.add(firstRow);

        replyMarkup.setKeyboard(keyboard);
       return SendMessage.builder()
                .chatId(chatId.toString())
                .replyMarkup(replyMarkup)
                .text(content)
                .build();
    }
}
