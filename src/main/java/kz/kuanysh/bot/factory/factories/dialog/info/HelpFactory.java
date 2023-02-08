package kz.kuanysh.bot.factory.factories.dialog.info;

import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.keyboards.Keyboard;
import kz.kuanysh.bot.factory.dialogs.info.HelpDialog;
import kz.kuanysh.bot.factory.keyboards.TextKeyboard;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

public class HelpFactory implements DialogFactory {

    @Override
    public Dialog createDialog() {
        return new HelpDialog();
    }

    @Override
    public Keyboard createKeyBoard() {
        return new TextKeyboard();
    }

    @Override
    public void doEvent(UserService userService, Message message , String text) {

    }

    @Override
    public void execute(BotApiMethod<Serializable> response, SendBotMessageServiceImp sendBotMessageServiceImp) {
        sendBotMessageServiceImp.sendMessageSerializable(response);
    }
}
