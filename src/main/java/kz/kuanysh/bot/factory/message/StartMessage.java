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
    public   BotApiMethod<Message> sendMessage(Message message, String content) {
        ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();
        replyMarkup.setOneTimeKeyboard(true);
        var keyboard = new ArrayList<KeyboardRow>();
        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();
        firstRow.add("next");
        secondRow.add("О нас \uD83E\uDD16");
        secondRow.add("Реклама \uD83E\uDD41");
        keyboard.add(firstRow);
        keyboard.add(secondRow);

        replyMarkup.setKeyboard(keyboard);
       return SendMessage.builder()
                .chatId(message.getChatId().toString())
                .replyMarkup(replyMarkup)
                .text(content)
                .build();
    }
}
