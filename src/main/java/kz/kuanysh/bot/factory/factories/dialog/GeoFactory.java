package kz.kuanysh.bot.factory.factories.dialog;

import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.dialogs.GeoDialog;
import kz.kuanysh.bot.factory.keyboards.GeoKeyboard;
import kz.kuanysh.bot.factory.keyboards.SenderKeyboard;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

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
    public void doEvent(UserService userService, Message message, String text) {
        userService.saveUserCategory(message, text);
    }

    @Override
    public void execute(BotApiMethod<Serializable> response, SendBotMessageServiceImp sendBotMessageServiceImp) {
        sendBotMessageServiceImp.sendMessageSerializable(response);
    }
}
