package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.InlineListGenerator;
import kz.kuanysh.bot.commands.Commands;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

public class ChoiceState implements UserActivity {

    @Override
    public UserActivity nextDialogState() {
        return new CategoryState();
    }


    @Override
    public UserActivity backDialogState() {
        return new StartState();
    }

    @Override
    public String getText(Message message) {
        return "Вы хотите найти работу или \n" +
                "вы хотите нанять сотрудника?";
    }

    @Override
    public InlineKeyboardMarkup getMarkup() {
        List<String> text = List.of(Commands.FIND_JOB.getText(),
                Commands.FIND_WORKER.getText(),
                Commands.BACK.getText());
        List<String> callback = List.of(Commands.FIND_JOB.getCallback(),
                Commands.FIND_WORKER.getCallback(),
                Commands.BACK.getCallback());
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(InlineListGenerator.listButtons(text,callback));
        return markup;
    }

}


