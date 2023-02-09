package kz.kuanysh.bot.chain;

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

    public <T extends Serializable >BotApiMethod<T> processState(Message message , Dialog context,UserService userService){
        if (shouldProcessState(context.getState())){
            log.info("shouldProcessState in factory {} in {}", context.getClass().getSimpleName(),context.getState().getClass().getSimpleName());
            return doProcess(message, context,userService);
        }else if (nextChain != null){
            return nextChain.processState(message, context,userService);
        }else {
            throw new IllegalStateException(context.getClass().getSimpleName());
        }
    }

    protected abstract <T extends Serializable >BotApiMethod<T> doProcess(Message message, Dialog context, UserService userService);

    protected abstract boolean shouldProcessState(UserActivity userActivity);
}
