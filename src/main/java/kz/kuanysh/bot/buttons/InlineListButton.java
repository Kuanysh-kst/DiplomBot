package kz.kuanysh.bot.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.List;

public class InlineListButton extends InlineKeyboardMarkup {
    public InlineListButton(List<String> buttonNames, List<String> callBackNames) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (int i = 0; i < buttonNames.size(); i++) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonNames.get(i));
            button.setCallbackData(callBackNames.get(i));
            row.add(button);
            keyboard.add(row);
        }


        this.setKeyboard(keyboard);
    }
}
