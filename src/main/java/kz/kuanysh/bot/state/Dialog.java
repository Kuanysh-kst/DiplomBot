package kz.kuanysh.bot.state;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.File;
import java.io.Serializable;

import static java.util.Objects.requireNonNullElse;

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
    public ReplyKeyboard getMarkup(){
        return state.getMarkup();
    }

    public String getText(Message message){
        return state.getText(message);
    }

    public File getFile() {
        return requireNonNullElse(file,new File("src/main/resources/standarts/default.png"));
    }

    public void backDialogState() {
        state = state.backDialogState();
    }

    public UserActivity getState() {
        return state;
    }


}
