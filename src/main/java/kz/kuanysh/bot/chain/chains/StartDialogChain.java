package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.chain.DialogStateChain;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.StartUserActivity;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

@Slf4j
public class StartDialogChain extends DialogStateChain {

    public StartDialogChain(DialogStateChain nextChain) {
        super(nextChain);
    }

    @Override
    protected <T extends Serializable> BotApiMethod<T> doProcess(Message message, Dialog context, UserService userService) {
        if (message.getText().equals("/start")) {
            log.info("it's /start: " + context.getState().getClass().getSimpleName());
            String content = context.getContent();
            UserActivity userActivity = context.getState();
            context.nextDialogState(message.getText());
            userService.saveDialog(message, context);
            return userActivity.getKeyBoard(message, content);
        } else {
            log.info("it's /start else: " + context.getState().getClass().getSimpleName());
            UserActivity userActivity = context.getState();
            return userActivity.getKeyBoard(message, context.getContent());
        }
    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof StartUserActivity;
    }
}
