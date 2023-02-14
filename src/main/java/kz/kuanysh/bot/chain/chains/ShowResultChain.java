package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.ShowResultState;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public class ShowResultChain extends DialogChain {

    public ShowResultChain(DialogChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog state, String command, UserService userService, SendBotMessageServiceImp execute) {
        if (command.equals("/result")){
            userService.saveUserParameters(message,state);
//            List<User> list = userService.findByChoiceAndCategory(message);

//            var response = state.getKeyBoard(message, command);
//            execute.sendMessageSerializable(response);

        }
    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof ShowResultState;
    }
}
