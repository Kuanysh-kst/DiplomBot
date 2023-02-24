package kz.kuanysh.bot.buttons.keyboards;

import kz.kuanysh.bot.buttons.ReplyMarkupGenerator;
import kz.kuanysh.bot.commands.Commands;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import java.util.List;

public class LocationMarkup {
    public static ReplyKeyboardMarkup ResultMarkup() {
        KeyboardButton location = KeyboardButton.builder()
                .text(Commands.SET_LOCATION.getText())
                .requestLocation(true)
                .build();
        KeyboardButton skip = KeyboardButton.builder()
                .text(Commands.SKIP.getText())
                .build();
        KeyboardButton back = KeyboardButton.builder()
                .text(Commands.BACK.getText())
                .build();
        List<List<KeyboardButton>> list = List.of(List.of(location, skip), List.of(back));
        return ReplyMarkupGenerator.createListKeyboard(list, true);
    }
}
