package kz.kuanysh.bot.factory.factories;

import kz.kuanysh.bot.factory.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.Sender;
import kz.kuanysh.bot.factory.dialogs.SorryDialog;
import kz.kuanysh.bot.factory.message.TextMessage;

public class SorryDialogFactory implements DialogFactory {
    @Override
    public Dialog createDialog() {
        return new SorryDialog();
    }

    @Override
    public Sender createSender() {
        return new TextMessage();
    }
}
