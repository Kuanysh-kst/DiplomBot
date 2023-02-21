package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.objects.*;

import java.io.Serializable;

public class ShowResultState implements UserActivity {


    @Override
    public UserActivity nextDialogState() {
        return new CategoryState();
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
    public void executeMessage(Message message, String text, String command, SendBotMessageServiceImp execute) {

    }


}
