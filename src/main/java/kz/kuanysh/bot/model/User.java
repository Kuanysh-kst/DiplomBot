package kz.kuanysh.bot.model;

import kz.kuanysh.bot.state.Dialog;
import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.File;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Data
@Entity(name = "usersDataTable")
public class User {

    @Id
    private Long chatId;
    private String firstName;
    private String lastName;
    private String userName;
    private Timestamp registeredAt;
    private String choice;
    private String category;
    private String about;
    private File file;
    private Contact contact;
    private Location location;
    private Dialog dialog;

    public String getAbout() {
        return Objects.requireNonNullElse(about, "....");
    }

    @Override
    public String toString() {
        return "User{" +
                "chatId=" + chatId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", registeredAt=" + registeredAt + '\'' +
                ", status=" + choice + '\'' +
                ", about=" + about + '\'' +
                ", file=" + file + '\'' +
                ", contact=" + contact + '\'' +
                ", location=" + location + '\'' +
                ", dialog=" + dialog + '\'' +
                '}';
    }
}
