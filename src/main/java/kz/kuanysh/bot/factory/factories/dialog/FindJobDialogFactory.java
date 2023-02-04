package kz.kuanysh.bot.factory.factories.dialog;

import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.message.Sender;
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
