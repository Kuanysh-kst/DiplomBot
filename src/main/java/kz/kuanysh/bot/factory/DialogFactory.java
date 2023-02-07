package kz.kuanysh.bot.factory;

import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.keyboards.SenderKeyboard;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

public interface DialogFactory {
    Dialog createDialog();

    SenderKeyboard createSender();

    void doEvent(UserService userService, Message message , String text);

    void execute(BotApiMethod<Serializable> response, SendBotMessageServiceImp sendBotMessageServiceImp);
}
