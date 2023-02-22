package kz.kuanysh.bot.state;

import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

public interface UserActivity extends Serializable {
    UserActivity nextDialogState();

    UserActivity backDialogState();

    String getText(Message message, User user);

    void executeMessage(Message message, String text, String command, SendBotMessageServiceImp execute);


}
