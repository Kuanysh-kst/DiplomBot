package kz.kuanysh.bot.state;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.persistence.Id;
import java.io.Serializable;

@Slf4j
@Data
public class Dialog implements Serializable {

    @Id
    private UserActivity state;


    public Dialog(UserActivity state) {
        this.state = state;
    }

    public void nextDialogState(String command) {
        var previousState = state;
        state = state.nextDialogState(command);
        log.info("Changing state from {} to {}", previousState.getClass().getSimpleName(),
                state.getClass().getSimpleName());
    }

    public <T extends Serializable> BotApiMethod getKeyBoard(Message message,String command) {
        return state.getKeyBoard(message, state.getText(message),command);
    }

    public void nextDialogState(Contact contact) {
        state = state.nextDialogState(contact);
    }

    public void nextDialogState(Location location) {
        state = state.nextDialogState(location);
    }

    public void backDialogState() {
        state = state.backDialogState();
    }

    public String getContent(Message message) {
        return this.state.getText(message);
    }

    public UserActivity getState() {
        return state;
    }
}
