package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.*;

import java.io.Serializable;
import java.util.List;

public class ShowResultState implements UserActivity {



    @Override
    public UserActivity nextDialogState() {
        return new FinishState();
    }

    @Override
    public UserActivity backDialogState() {
        return new ResultState();
    }

    @Override
    public String getText(Message message) {
        return "it's show result state";
    }

    @Override
    public <T extends Serializable> BotApiMethod<Serializable> getKeyBoard(Message message, String text, String command) {
        return null;
    }
}
