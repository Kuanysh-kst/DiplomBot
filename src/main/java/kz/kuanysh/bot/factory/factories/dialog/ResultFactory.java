package kz.kuanysh.bot.factory.factories.dialog;

import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.dialogs.ResultDialog;
import kz.kuanysh.bot.factory.keyboards.Keyboard;
import kz.kuanysh.bot.factory.keyboards.ResultKeyBoard;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

public class ResultFactory implements DialogFactory {
    @Override
    public Dialog createDialog() {
        return new ResultDialog();
    }

    @Override
    public Keyboard createKeyBoard() {
        return new ResultKeyBoard();
    }

    @Override
    public void doEvent(UserService userService, Message message, String text) {
        userService.saveUserCategory(message,text);
        userService.saveUserContact(message);
    }

    @Override
    public void execute(BotApiMethod<Serializable> response, SendBotMessageServiceImp sendBotMessageServiceImp) {
        sendBotMessageServiceImp.sendMessageSerializable(response);
    }
}
