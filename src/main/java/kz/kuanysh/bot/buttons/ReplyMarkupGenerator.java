package kz.kuanysh.bot.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ReplyMarkupGenerator {

    public static ReplyKeyboardMarkup createKeyboard(List<List<String>> buttons, boolean isOneTime) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(isOneTime);

        List<KeyboardRow> keyboard = new ArrayList<>();
        for (List<String> row : buttons) {
            KeyboardRow keyboardRow = new KeyboardRow();
            for (String button : row) {
                keyboardRow.add(button);
            }
            keyboard.add(keyboardRow);
        }
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    public static ReplyKeyboardMarkup createListKeyboard(List<List<KeyboardButton>> buttons, boolean isOneTime) {
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        for(List<KeyboardButton> rowButtons :buttons){
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.addAll(rowButtons);
            keyboardRows.add(keyboardRow);
        }
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setOneTimeKeyboard(isOneTime);
        markup.setKeyboard(keyboardRows);
        markup.setResizeKeyboard(true);
        return markup;
    }
}
