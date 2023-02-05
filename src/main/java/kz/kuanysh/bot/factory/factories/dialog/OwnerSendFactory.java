package kz.kuanysh.bot.factory.factories.dialog;

import kz.kuanysh.bot.factory.Event.Event;
import kz.kuanysh.bot.factory.Event.StartEvent;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.message.Sender;

public class OwnerSendFactory implements DialogFactory {
    @Override
    public Dialog createDialog() {
        return null;
    }

    @Override
    public Sender createSender() {
        return null;
    }

    @Override
    public Event serviceEvent() {
        return new StartEvent();
    }
}
