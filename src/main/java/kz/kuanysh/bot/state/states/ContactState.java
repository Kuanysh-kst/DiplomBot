package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.ReplyMarkupGenerator;
import kz.kuanysh.bot.commands.Commands;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

import java.util.List;

public class ContactState implements UserActivity {
    @Override
    public UserActivity nextDialogState() {
        return new LocationState();
    }

    @Override
    public UserActivity backDialogState() {
        return new PhotoState();
    }

    @Override
    public String getText(Message message) {
        return "Для дальнейшего пойска нужен ваш контак \uD83D\uDCDE";
    }

    @Override
    public ReplyKeyboardMarkup getMarkup() {
        KeyboardButton contact = KeyboardButton.builder()
                .text(Commands.SET_CONTACT.getText())
                .requestContact(true)
                .build();
        KeyboardButton back = KeyboardButton.builder()
                .text(Commands.BACK.getText())
                .build();
        List<List<KeyboardButton>> list = List.of(List.of(contact), List.of(back));
        return ReplyMarkupGenerator.createListKeyboard(list, true);
    }
}
