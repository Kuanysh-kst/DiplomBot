package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.chain.DialogStateChain;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.ChoiceUserActivity;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

@Slf4j
public class ChoiceDialogChiang extends DialogStateChain {

    public ChoiceDialogChiang(DialogStateChain nextChain) {
        super(nextChain);
    }

    @Override
    protected <T extends Serializable> BotApiMethod<T> doProcess(Message message, Dialog context, UserService userService) {
        if (message.getText().equals("next")) {
            log.info("it's Choice /findjob");
            String content = context.getContent();
            UserActivity userActivity = context.getState();
            context.nextDialogState(message.getText());
            userService.saveDialog(message, context);
            return userActivity.getKeyBoard(message, content);
        } else {
            context.backDialogState();
            UserActivity userActivity = context.getState();
            userService.saveDialog(message, context);
            return userActivity.getKeyBoard(message, context.getContent());
        }
    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof ChoiceUserActivity;
    }


}
