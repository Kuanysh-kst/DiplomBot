package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ContactState implements UserActivity<List<PhotoSize>> {
    private final String choice;
    private final String category;
    private final String about;

    public ContactState(String choice, String category, String about) {
        this.choice = choice;
        this.category = category;
        this.about = about;
    }

    @Override
    public UserActivity nextDialogState(List<PhotoSize> par) {
        return new LocationState(choice, category, about,  par);
    }

    @Override
    public UserActivity backDialogState() {
        return new PhotoState(choice, category);
    }

    @Override
    public String getText(Message message) {
        return "Для дальнейшего пойска нужен ваш контак \uD83D\uDCDE";
    }

    @Override
    public BotApiMethod<Message> getKeyBoard(Message message, String text, String command) {

        ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();
        replyMarkup.setOneTimeKeyboard(true);
        var keyboard = new ArrayList<KeyboardRow>();
        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();
        KeyboardButton contact = KeyboardButton.builder()
                .text("setContact")
                .requestContact(true)
                .build();
        KeyboardButton back = KeyboardButton.builder()
                .text("/back")
                .build();
        firstRow.add(contact);
        secondRow.add(back);

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
