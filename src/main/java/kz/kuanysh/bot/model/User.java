package kz.kuanysh.bot.model;

import kz.kuanysh.bot.state.Dialog;
import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Contact;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.HashMap;

@Data
@Entity(name = "usersDataTable")
public class User {

    @Id
    private Long chatId;

    private String firstName;

    private String lastName;

    private String userName;

    private Timestamp registeredAt;

    private String status;

    private String category;

    private Contact contact;

    private Dialog dialog;

    @Override
    public String toString() {
        return "User{" +
                "chatId=" + chatId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", registeredAt=" + registeredAt + '\'' +
                ", status=" + status + '\'' +
                ", category=" + category + '\'' +
                ", contact=" + contact + '\'' +
                    ", dialog=" + dialog + '\'' +
                '}';
    }
}
