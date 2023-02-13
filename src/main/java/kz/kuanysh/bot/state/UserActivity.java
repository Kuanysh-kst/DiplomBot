package kz.kuanysh.bot.state;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

public interface UserActivity<T> extends Serializable {
    UserActivity nextDialogState(T par);

    UserActivity backDialogState();

    String getText(Message message);

    <T extends Serializable> BotApiMethod<Serializable> getKeyBoard(Message message,String text,String command);
}
