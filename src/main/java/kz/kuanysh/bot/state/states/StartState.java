package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.ReplyMarkupGenerator;
import kz.kuanysh.bot.commands.Commands;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import java.util.List;

public class StartState implements UserActivity{
    @Override
    public UserActivity nextDialogState() {
        return new ChoiceState();
    }

    @Override
    public UserActivity backDialogState() {
        return null;
    }

    @Override
    public String getText(Message message) {
        return "Привет " + message.getChat().getFirstName()
                + " \uD83E\uDD20, это бот для поиска работы и найма сотрудников, \n" +
                "бот может работать со следующими командами \n" +
                "/help : для ознакомления";
    }

    @Override
    public ReplyKeyboardMarkup getMarkup() {
        KeyboardButton next = KeyboardButton.builder()
                .text(Commands.NEXT.getText())
                .build();
        List<List<KeyboardButton>> list = List.of(List.of(next));
        return ReplyMarkupGenerator.createListKeyboard(list, true);
    }
}

