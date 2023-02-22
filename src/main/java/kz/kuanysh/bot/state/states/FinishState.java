package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.objects.Message;

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
    public void executeMessage(Message message, String text, String command, SendBotMessageServiceImp execute) {

    }


}
