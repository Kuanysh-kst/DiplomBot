package kz.kuanysh.bot.factory.factories.dialog;

import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.Event.Event;
import kz.kuanysh.bot.factory.Event.StartEvent;
import kz.kuanysh.bot.factory.dialogs.ChoiceDialog;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.keyboards.ChoiceEditKeyboard;
import kz.kuanysh.bot.factory.keyboards.SenderKeyboard;

public class ChoiceEditFactory implements DialogFactory {
    @Override
    public Dialog createDialog() {
        return new ChoiceDialog();
    }

    @Override
    public SenderKeyboard createSender() {
        return new ChoiceEditKeyboard();
    }

    @Override
    public Event serviceEvent() {
        return new StartEvent();
    }
}
