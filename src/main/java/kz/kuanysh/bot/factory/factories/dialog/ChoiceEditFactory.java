package kz.kuanysh.bot.factory.factories.dialog;

import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.dialogs.ChoiceDialog;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.message.ChoiceEditMessage;
import kz.kuanysh.bot.factory.message.Sender;

public class ChoiceEditFactory implements DialogFactory {
    @Override
    public Dialog createDialog() {
        return new ChoiceDialog();
    }

    @Override
    public Sender createSender() {
        return new ChoiceEditMessage();
    }
}
