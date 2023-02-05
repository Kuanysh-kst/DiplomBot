package kz.kuanysh.bot.factory.factories.dialog;

import kz.kuanysh.bot.factory.Event.ChoiceEvent;
import kz.kuanysh.bot.factory.Event.Event;
import kz.kuanysh.bot.factory.Event.StartEvent;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.message.Sender;
import kz.kuanysh.bot.factory.dialogs.ChoiceDialog;
import kz.kuanysh.bot.factory.message.ChoiceMessage;

public class ChoiceDialogFactory implements DialogFactory {
    @Override
    public Dialog createDialog() {
        return new ChoiceDialog();
    }

    @Override
    public Sender createSender() {
        return new ChoiceMessage();
    }

    @Override
    public Event serviceEvent() {
        return new StartEvent();
    }
}
