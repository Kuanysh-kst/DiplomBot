package kz.kuanysh.bot.factory;

import kz.kuanysh.bot.factory.Event.Event;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.keyboards.SenderKeyboard;

public interface DialogFactory {
    Dialog createDialog();

    SenderKeyboard createSender();

    Event serviceEvent();
}
