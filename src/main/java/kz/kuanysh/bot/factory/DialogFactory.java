package kz.kuanysh.bot.factory;

import kz.kuanysh.bot.factory.Event.Event;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.message.Sender;

public interface DialogFactory {
    Dialog createDialog();

    Sender createSender();

    Event serviceEvent();
}
