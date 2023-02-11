package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ResultState implements UserActivity<List<User>> {
    private final String choice;
    private final String category;
    private final String about;
    private final InputFile photo;
    private final Contact contact;
    private final Location location;

    public ResultState(String choice, String category, String about, InputFile photo, Contact contact, Location location) {
        this.choice = choice;
        this.category = category;
        this.about = about;
        this.photo = photo;
        this.contact = contact;
        this.location = location;
    }

    @Override
    public UserActivity nextDialogState(List<User> par) {
        return new ShowResultState(choice, category, about, photo, contact, location, par);
    }

    @Override
    public UserActivity backDialogState() {
        return new LocationState(choice, category, about, photo, contact);
    }

    @Override
    public String getText(Message message) {
        return "Результат основан на ваших данных \n "+
                "подробнее о лицах которые вам пришли "+
                "вы можете узнать через кнопку  show \uD83D\uDCE0: ";
    }

    @Override
    public BotApiMethod<Message> getKeyBoard(Message message, String text, String command) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton button = InlineKeyboardButton.builder()
                .text("назад")
                .callbackData("/back")
                .build();
        InlineKeyboardButton skip = InlineKeyboardButton.builder()
                .text("получить результат")
                .callbackData("/result")
                .build();
        row1.add(button);
        row2.add(skip);
        keyboard.add(row2);
        keyboard.add(row1);

        inlineKeyboardMarkup.setKeyboard(keyboard);
        return PatternKeyboard.sendInline(message.getChatId(), text, inlineKeyboardMarkup);
    }
}
