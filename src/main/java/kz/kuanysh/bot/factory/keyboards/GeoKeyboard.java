package kz.kuanysh.bot.factory.keyboards;

import kz.kuanysh.bot.buttons.PatternKeyboard;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

public class GeoKeyboard implements SenderKeyboard {
    @Override
    public  BotApiMethod<Message> sendMessage(Message message, String content) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
         var keyboard = new ArrayList<KeyboardRow>();
        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();

        KeyboardButton firstButton = KeyboardButton.builder()
                .text("geodate")
                .requestLocation(true)
                .build();
        firstRow.add(firstButton);

        KeyboardButton secondButton = KeyboardButton.builder()
                .text("/start")
                .build();
        secondRow.add(secondButton);
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return PatternKeyboard.sendReply(message.getChatId(),content,replyKeyboardMarkup);
    }
}
