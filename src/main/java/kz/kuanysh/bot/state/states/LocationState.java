package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

public class LocationState implements UserActivity<Location> {
    private final String choice;
    private final String category;
    private final String about;
    private final InputFile photo;
    private final Contact contact;

    public LocationState(String choice, String category, String about, InputFile photo, Contact contact) {
        this.choice = choice;
        this.category = category;
        this.about = about;
        this.photo = photo;
        this.contact = contact;
    }

    @Override
    public UserActivity nextDialogState(Location par) {
        return new ResultState(choice, category, about, photo, contact, par);
    }

    @Override
    public UserActivity backDialogState() {
        return new ContactState(choice, category, about, photo);
    }

    @Override
    public String getText(Message message) {
        return "Вы можето поделится свойм местоположением для других соискателей \uD83D\uDDFA";
    }

    @Override
    public BotApiMethod<Message> getKeyBoard(Message message, String text, String command) {
        ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();
        replyMarkup.setOneTimeKeyboard(true);
        var keyboard = new ArrayList<KeyboardRow>();
        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();
        KeyboardButton contact = KeyboardButton.builder()
                .text("setLocation")
                .requestLocation(true)
                .build();
        KeyboardButton skip = KeyboardButton.builder()
                .text("/skip")
                .build();
        KeyboardButton back = KeyboardButton.builder()
                .text("/back")
                .build();
        firstRow.add(contact);
        secondRow.add(back);
        secondRow.add(skip);

        keyboard.add(firstRow);
        keyboard.add(secondRow);
        replyMarkup.setResizeKeyboard(true);
        replyMarkup.setKeyboard(keyboard);

        return SendMessage.builder()
                .chatId(message.getChatId().toString())
                .replyMarkup(replyMarkup)
                .text(text)
                .build();
    }
}
