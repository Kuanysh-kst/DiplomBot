package kz.kuanysh.bot.chain;

import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

@Slf4j
public abstract class DialogStateChain {

    private final DialogStateChain nextChain;

    protected DialogStateChain(DialogStateChain nextChain) {
        this.nextChain = nextChain;
    }

    public void processState(Message message, Dialog context,String command, UserService userService, SendBotMessageServiceImp executeService) {
        if (shouldProcessState(context.getState())) {
            log.info("shouldProcessState in factory {} in {}", context.getClass().getSimpleName(), context.getState().getClass().getSimpleName());
             doProcess(message, context,command, userService,executeService);
        } else if (nextChain != null) {
           nextChain.processState(message, context,command, userService, executeService);
        } else {
            throw new IllegalStateException(context.getClass().getSimpleName());
        }
    }

    protected abstract void doProcess(Message message, Dialog context,String command, UserService userService,SendBotMessageServiceImp executeService);

    protected abstract boolean shouldProcessState(UserActivity userActivity);
}
