package kz.kuanysh.bot.factory.factories.dialog;

import kz.kuanysh.bot.factory.Event.ChoiceEvent;
import kz.kuanysh.bot.factory.Event.Event;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.keyboards.SenderKeyboard;
import kz.kuanysh.bot.factory.dialogs.FindJobDialog;
import kz.kuanysh.bot.factory.keyboards.FindJobKeyboard;

public class FindJobDialogFactory implements DialogFactory {
    @Override
    public Dialog createDialog() {
        return new FindJobDialog();
    }

    @Override
    public SenderKeyboard createSender() {
        return new FindJobKeyboard();
    }

    @Override
    public Event serviceEvent() {
        return new ChoiceEvent();
    }
}
