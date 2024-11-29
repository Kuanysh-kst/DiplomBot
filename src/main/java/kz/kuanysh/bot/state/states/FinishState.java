package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class FinishState implements UserActivity{
    @Override
    public UserActivity nextDialogState() {
        return new FinishState();
    }

    @Override
    public UserActivity backDialogState() {
        return new ShowResultState();
    }

    @Override
    public String getText(Message message) {
        return null;
    }

    @Override
    public ReplyKeyboard getMarkup() {
        return null;
    }
}
