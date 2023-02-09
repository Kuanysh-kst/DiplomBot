package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.chain.DialogStateChain;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.CategoryActivity;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.io.Serializable;

public class CategoryDialogChain extends DialogStateChain {


    public CategoryDialogChain(DialogStateChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog context, String command, UserService userService, SendBotMessageServiceImp executeService) {
        if (command.equals("/workLoader")) {
            UserActivity userActivity = context.getState();
            context.nextDialogState(message.getText());
        } else {
            context.backDialogState();
            UserActivity userActivity = context.getState();
        }
    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof CategoryActivity;
    }
}
