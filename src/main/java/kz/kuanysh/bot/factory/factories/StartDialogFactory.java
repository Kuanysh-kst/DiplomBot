package kz.kuanysh.bot.factory.factories;

import kz.kuanysh.bot.factory.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.Sender;
import kz.kuanysh.bot.factory.dialogs.StartDialog;
import kz.kuanysh.bot.factory.message.StartMessage;

public class StartDialogFactory implements DialogFactory {

    @Override
    public Dialog createDialog() {
        return new StartDialog();
    }

    @Override
    public Sender createSender() {
        return new StartMessage();
    }
}
