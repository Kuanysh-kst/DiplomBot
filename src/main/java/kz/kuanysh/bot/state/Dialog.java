package kz.kuanysh.bot.state;

import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Data
@Slf4j
public class Dialog implements Serializable {

    private String choice;
    private String category;
    private String about;
    private File file;
    private Contact contact;
    private Location location;
    private int userIndex;
    private UserActivity state;


    public Dialog(UserActivity state) {
        this.state = state;
    }

    public void nextDialogState() {
        state = state.nextDialogState();
        log.info("Current state:{}", state.getClass().getSimpleName());
    }

    public void executeMessage(Message message, String command, SendBotMessageServiceImp execute) {
        state.executeMessage(message, state.getText(message), command, execute);
    }

    public void backDialogState() {
        state = state.backDialogState();
    }

    public UserActivity getState() {
        return state;
    }


}
