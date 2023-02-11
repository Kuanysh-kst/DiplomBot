package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

public class FinishState implements UserActivity {


    @Override
    public UserActivity nextDialogState() {
        return null;
    }

    @Override
    public UserActivity backDialogState() {
        return new ResultState(choice, category, about, photo, contact, location);
    }

    @Override
    public String getText(Message message) {
        return null;
    }

    @Override
    public BotApiMethod<Serializable> getKeyBoard(Message message, String text, String command) {
        return null;
    }
}
