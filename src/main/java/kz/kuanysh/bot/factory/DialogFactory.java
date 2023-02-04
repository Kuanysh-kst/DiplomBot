package kz.kuanysh.bot.factory;

import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.message.Sender;

public interface DialogFactory  {
    public Dialog createDialog();
    public Sender createSender();
}
