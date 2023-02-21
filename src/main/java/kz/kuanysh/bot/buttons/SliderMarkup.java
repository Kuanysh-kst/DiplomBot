package kz.kuanysh.bot.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class SliderMarkup {


    public static ReplyKeyboardMarkup rightLeftSlide() {
        List<List<String>> list = List.of(List.of("<<", ">>"), List.of("получить контакт"), List.of("вернуться к выбору"));
        return createKeyboard(list,false);
    }
    public static ReplyKeyboardMarkup oneSlide() {
        List<List<String>> list = List.of(List.of("получить контакт"), List.of("вернуться к выбору"));
        return createKeyboard(list,false);
    }

    public static ReplyKeyboardMarkup emptySlide() {
        List<List<String>> list = List.of(List.of("вернуться к выбору"));
        return createKeyboard(list,true);
    }

    private static ReplyKeyboardMarkup createKeyboard(List<List<String>> buttons, boolean isOneTime) {
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
}
