package kz.kuanysh.bot.factory.factories.dialog;

import kz.kuanysh.bot.factory.Event.Event;
import kz.kuanysh.bot.factory.Event.StartEvent;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.message.Sender;
import kz.kuanysh.bot.factory.dialogs.StartDialog;
import kz.kuanysh.bot.factory.message.StartMessage;

public class StartDialogFactory implements DialogFactory {

    @Override
    public Dialog createDialog() {
        return new StartDialog();
    }

    @Override
    public Sender createSender() {
        return new StartMessage();
    }

    @Override
    public Event serviceEvent() {
        return new StartEvent();
    }
}

