package kz.kuanysh.bot.factory.factories;

import kz.kuanysh.bot.factory.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.dialogs.SecondDialog;

public class SecondDialogFactory implements DialogFactory {
    @Override
    public Dialog createDialog() {
        return new SecondDialog();
    }
}
