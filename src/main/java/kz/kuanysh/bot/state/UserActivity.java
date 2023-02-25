package kz.kuanysh.bot.state;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.Serializable;

public interface UserActivity extends Serializable {
    UserActivity nextDialogState();

    UserActivity backDialogState();

    String getText(Message message);

    ReplyKeyboard getMarkup();

}
