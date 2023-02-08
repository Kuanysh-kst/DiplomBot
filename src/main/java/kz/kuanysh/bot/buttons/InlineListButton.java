package kz.kuanysh.bot.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineListButton extends InlineKeyboardMarkup {

    public static List<List<InlineKeyboardButton>> listButtons(List<String> buttonNames, List<String> callBackNames) {

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (int i = 0; i < buttonNames.size(); i++) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonNames.get(i));
            button.setCallbackData(callBackNames.get(i));
            row.add(button);
            keyboard.add(row);

        }
        return keyboard;
    }

    public static List<InlineKeyboardButton> backButton(String callBack) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = InlineKeyboardButton.builder()
                .text("Назад")
                .callbackData(callBack)
                .build();
        row.add(button);
        return row;
    }
}
