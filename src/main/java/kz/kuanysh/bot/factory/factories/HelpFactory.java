package kz.kuanysh.bot.factory.factories;

import kz.kuanysh.bot.factory.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.Sender;
import kz.kuanysh.bot.factory.dialogs.HelpDialog;
import kz.kuanysh.bot.factory.message.TextMessage;

public class HelpFactory implements DialogFactory {

    @Override
    public Dialog createDialog() {
        return new HelpDialog();
    }

    @Override
    public Sender createSender() {
        return new TextMessage();
    }
}
