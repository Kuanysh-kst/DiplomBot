package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;
import java.util.List;

public class FinishState implements UserActivity<String> {
    private final String choice;
    private final String category;
    private final String about;
    private final InputFile photo;
    private final Contact contact;
    private final Location location;
    private final List<User> list;

    public FinishState(String choice, String category, String about, InputFile photo, Contact contact, Location location, List<User> list) {
        this.choice = choice;
        this.category = category;
        this.about = about;
        this.photo = photo;
        this.contact = contact;
        this.location = location;
        this.list = list;
    }

    @Override
    public UserActivity nextDialogState(String par) {
        return new FinishState(choice, category, about, photo, contact, location, list);
    }

    @Override
    public UserActivity backDialogState() {
        return new ResultState(choice, category, about, photo, contact, location);
    }

    @Override
    public String getText(Message message) {
        return null;
    }

    @Override
    public BotApiMethod<Serializable> getKeyBoard(Message message, String text, String command) {
        return null;
    }
}
