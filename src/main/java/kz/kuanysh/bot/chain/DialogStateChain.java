package kz.kuanysh.bot.chain;

import kz.kuanysh.bot.factory.DialogFactory;
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

    public <T extends Serializable >BotApiMethod<T> processState(Message message , DialogFactory dialogFactory){
        if (shouldProcessState(dialogFactory)){
            log.info("shouldProcessState in state{} ",dialogFactory.getClass().getSimpleName());
            return doProcess(message,dialogFactory);
        }else if (nextChain != null){
            return nextChain.processState(message,dialogFactory);
        }else {
            throw new IllegalStateException(dialogFactory.getClass().getSimpleName());
        }
    }

    protected abstract <T extends Serializable >BotApiMethod<T> doProcess(Message message,DialogFactory dialogFactory);

    protected abstract boolean shouldProcessState(DialogFactory dialogFactory);
}
