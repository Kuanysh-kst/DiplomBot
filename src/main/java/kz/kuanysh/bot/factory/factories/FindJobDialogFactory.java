package kz.kuanysh.bot.factory.factories;

import kz.kuanysh.bot.factory.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.Sender;
import kz.kuanysh.bot.factory.dialogs.FindJobDialog;
import kz.kuanysh.bot.factory.message.FindJobMessage;

public class FindJobDialogFactory implements DialogFactory {
    @Override
    public Dialog createDialog() {
        return new FindJobDialog();
    }

    @Override
    public Sender createSender() {
        return new FindJobMessage();
    }
}
