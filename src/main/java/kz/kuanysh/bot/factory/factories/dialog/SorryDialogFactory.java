package kz.kuanysh.bot.factory.factories.dialog;

import kz.kuanysh.bot.factory.Event.Event;
import kz.kuanysh.bot.factory.Event.StartEvent;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.keyboards.SenderKeyboard;
import kz.kuanysh.bot.factory.dialogs.SorryDialog;
import kz.kuanysh.bot.factory.keyboards.TextKeyboard;

public class SorryDialogFactory implements DialogFactory {
    @Override
    public Dialog createDialog() {
        return new SorryDialog();
    }

    @Override
    public SenderKeyboard createSender() {
        return new TextKeyboard();
    }

    @Override
    public Event serviceEvent() {
        return new StartEvent();
    }


}
