package kz.kuanysh.bot.factory.factories;

import kz.kuanysh.bot.factory.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.Sender;
import kz.kuanysh.bot.factory.dialogs.FindWorkerDialog;
import kz.kuanysh.bot.factory.message.FindJobMessage;

public class FindWorkerFactory implements DialogFactory {
    @Override
    public Dialog createDialog() {
        return new FindWorkerDialog();
    }

    @Override
    public Sender createSender() {
        return new FindJobMessage();
    }
}
