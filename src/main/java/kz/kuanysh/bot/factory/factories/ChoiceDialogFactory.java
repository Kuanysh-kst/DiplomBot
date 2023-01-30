package kz.kuanysh.bot.factory.factories;

import kz.kuanysh.bot.factory.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.Sender;
import kz.kuanysh.bot.factory.dialogs.ChoiceDialog;
import kz.kuanysh.bot.factory.message.ChoiceMessage;

public class ChoiceDialogFactory implements DialogFactory {
    @Override
    public Dialog createDialog() {
        return new ChoiceDialog();
    }

    @Override
    public Sender createSender() {
        return new ChoiceMessage();
    }
}
