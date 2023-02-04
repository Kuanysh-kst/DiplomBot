package kz.kuanysh.bot.factory.factories.admin;

import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.dialogs.ChoiceDialog;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.message.ChoiceMessage;
import kz.kuanysh.bot.factory.message.Sender;

public class SendFactory implements DialogFactory {
    @Override
    public Dialog createDialog() {
        return new ChoiceDialog();
    }

    @Override
    public Sender createSender() {
        return new ChoiceMessage();
    }
}
