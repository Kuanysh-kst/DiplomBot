package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.chain.DialogStateChain;
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
    protected <T extends Serializable> BotApiMethod<T> doProcess(Message message, Dialog context, UserService userService) {
        if (message.getText().equals("/workLoader")) {
            UserActivity userActivity = context.getState();
            context.nextDialogState(message.getText());
            return userActivity.getKeyBoard(message, context.getContent());
        } else {
            context.backDialogState();
            UserActivity userActivity = context.getState();
            return userActivity.getKeyBoard(message, context.getContent());
        }
    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof CategoryActivity;
    }
}
