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

    public void processState(Message message, Dialog state,String command, UserService userService, SendBotMessageServiceImp execute) {
        if (shouldProcessState(state.getState())) {
            log.info("shouldProcessState in factory {} in {}", state.getClass().getSimpleName(), state.getState().getClass().getSimpleName());
             doProcess(message, state,command, userService,execute);
        } else if (nextChain != null) {
           nextChain.processState(message, state,command, userService, execute);
        } else {
            throw new IllegalStateException(state.getState().getClass().getSimpleName());
        }
    }

    protected abstract void doProcess(Message message, Dialog context,String command, UserService userService,SendBotMessageServiceImp executeService);

    protected abstract boolean shouldProcessState(UserActivity userActivity);
}
