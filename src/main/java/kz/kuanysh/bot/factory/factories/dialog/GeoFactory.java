package kz.kuanysh.bot.factory.factories.dialog;

import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.Event.CategoryEvent;
import kz.kuanysh.bot.factory.Event.Event;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.dialogs.GeoDialog;
import kz.kuanysh.bot.factory.keyboards.GeoKeyboard;
import kz.kuanysh.bot.factory.keyboards.SenderKeyboard;

public class GeoFactory implements DialogFactory {
    @Override
    public Dialog createDialog() {
        return new GeoDialog();
    }

    @Override
    public SenderKeyboard createSender() {
        return new GeoKeyboard();
    }

    @Override
    public Event serviceEvent() {
        return new CategoryEvent();
    }
}
