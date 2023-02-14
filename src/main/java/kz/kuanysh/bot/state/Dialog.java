package kz.kuanysh.bot.state;

import kz.kuanysh.bot.model.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

import java.io.File;
import java.io.Serializable;
import java.util.List;

@Data
@Slf4j
public class Dialog implements Serializable {

    private String choice;
    private String category;
    private String about;
    private File file;
    private Contact contact;
    private Location location;
    private UserActivity state;


    public Dialog(UserActivity state) {
        this.state = state;
    }

    public void nextDialogState() {
        state = state.nextDialogState();
        log.info("Current state:{}", state.getClass().getSimpleName());
    }

    public BotApiMethod getKeyBoard(Message message, String command) {
        return state.getKeyBoard(message, state.getText(message), command);
    }

    public void backDialogState() {
        state = state.backDialogState();
    }

    public UserActivity getState() {
        return state;
    }


}
