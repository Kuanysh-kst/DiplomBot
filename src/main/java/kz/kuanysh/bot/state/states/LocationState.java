package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.ReplyMarkupGenerator;
import kz.kuanysh.bot.commands.Commands;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

import java.util.List;

public class LocationState implements UserActivity {


    @Override
    public UserActivity nextDialogState() {
        return new ResultState();
    }

    @Override
    public UserActivity backDialogState() {
        return new ContactState();
    }

    @Override
    public String getText(Message message) {
        return "Вы можето поделится свойм местоположением для других соискателей \uD83D\uDDFA";
    }

    @Override
    public ReplyKeyboardMarkup getMarkup() {
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
