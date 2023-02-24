package kz.kuanysh.bot.buttons.keyboards;

import kz.kuanysh.bot.commands.Commands;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class ResultMarkup {
    public static InlineKeyboardMarkup getMarkup() {
        InlineKeyboardButton back = InlineKeyboardButton.builder()
                .text(Commands.GO_TO_MENU.getText())
                .callbackData(Commands.GO_TO_MENU.getCallback())
                .build();
        InlineKeyboardButton getResult = InlineKeyboardButton.builder()
                .text(Commands.GET_RESULT.getText())
                .callbackData(Commands.GET_RESULT.getCallback())
                .build();
        List<List<InlineKeyboardButton>> keyboard = List.of(List.of(getResult), List.of(back));
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(keyboard);
        return markup;
    }
}
