package kz.kuanysh.bot.state;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

public interface UserActivity extends Serializable {
//    Dialog createDialog();
//
//    Keyboard createKeyBoard();
//
//    void doEvent(UserService userService, Message message , String text);
//
//    void execute(BotApiMethod<Serializable> response, SendBotMessageServiceImp sendBotMessageServiceImp);

    UserActivity nextDialogState();

    UserActivity backDialogState();

    String getText(Message message);

    <T extends Serializable> BotApiMethod<Serializable> getKeyBoard(Message message,String text,String command);
}
