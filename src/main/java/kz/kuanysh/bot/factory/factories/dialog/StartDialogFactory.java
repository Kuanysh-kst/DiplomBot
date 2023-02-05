package kz.kuanysh.bot.factory.factories.dialog;

import kz.kuanysh.bot.factory.Event.Event;
import kz.kuanysh.bot.factory.Event.StartEvent;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.keyboards.SenderKeyboard;
import kz.kuanysh.bot.factory.dialogs.StartDialog;
import kz.kuanysh.bot.factory.keyboards.StartMessage;

public class StartDialogFactory implements DialogFactory {

    @Override
    public Dialog createDialog() {
        return new StartDialog();
    }

    @Override
    public SenderKeyboard createSender() {
        return new StartMessage();
    }

    @Override
    public Event serviceEvent() {
        return new StartEvent();
    }
}

