package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ResultState implements UserActivity<Location> {
    private final String choice;
    private final String category;
    private final String about;
    private final List<PhotoSize> photo;
    private final Contact contact;

    public ResultState(String choice, String category, String about, List<PhotoSize> photo, Contact contact) {
        this.choice = choice;
        this.category = category;
        this.about = about;
        this.photo = photo;
        this.contact = contact;
    }

    @Override
    public UserActivity nextDialogState(Location par) {
        return new ShowResultState(choice, category, about, photo, contact, par);
    }

    @Override
    public UserActivity backDialogState() {
        return new LocationState(choice, category, about, photo);
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
