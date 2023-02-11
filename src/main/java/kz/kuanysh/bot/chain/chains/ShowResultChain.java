package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ShowResultChain extends DialogChain {

    public ShowResultChain(DialogChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog context, String command, UserService userService, SendBotMessageServiceImp executeService) {

    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return false;
    }
}
