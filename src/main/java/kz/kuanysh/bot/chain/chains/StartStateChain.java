package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.chain.DialogStateChain;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.factories.dialog.StartDialogFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

public class StartStateChain extends DialogStateChain {

    protected StartStateChain(DialogStateChain nextChain) {
        super(nextChain);
    }

    @Override
    protected <T extends Serializable> BotApiMethod<T> doProcess(Message message, DialogFactory dialogFactory) {
        return dialogFactory.;
    }

    @Override
    protected boolean shouldProcessState(DialogFactory dialogFactory) {
        return dialogFactory instanceof StartDialogFactory;
    }
}
