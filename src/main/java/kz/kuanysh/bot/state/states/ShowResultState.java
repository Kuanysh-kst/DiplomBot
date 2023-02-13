package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.*;

import java.io.Serializable;
import java.util.List;

public class ShowResultState implements UserActivity<List<User>> {

    private final String choice;
    private final String category;
    private final String about;
    private final List<PhotoSize> photo;
    private final Contact contact;
    private final Location location;

    public ShowResultState(String choice, String category, String about, List<PhotoSize> photo, Contact contact, Location location) {
        this.choice = choice;
        this.category = category;
        this.about = about;
        this.photo = photo;
        this.contact = contact;
        this.location = location;
    }

    @Override
    public UserActivity nextDialogState(List<User> par) {
        return new FinishState(choice, category, about, photo, contact, location,par);
    }

    @Override
    public UserActivity backDialogState() {
        return new ResultState(choice, category, about, photo, contact);
    }

    @Override
    public String getText(Message message) {
        return "it's show result state";
    }

    @Override
    public <T extends Serializable> BotApiMethod<Serializable> getKeyBoard(Message message, String text, String command) {
        return null;
    }
}
