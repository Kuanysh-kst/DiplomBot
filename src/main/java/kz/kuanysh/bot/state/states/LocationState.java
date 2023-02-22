package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

public class LocationState implements UserActivity {


    @Override
    public UserActivity nextDialogState() {
        return new ResultState();
    }

    @Override
    public UserActivity backDialogState() {
        return new ContactState();
    }

    @Override
    public String getText(Message message) {
        return "Вы можето поделится свойм местоположением для других соискателей \uD83D\uDDFA";
    }

    @Override
    public void executeMessage(Message message, String text, String command, SendBotMessageServiceImp execute) {
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

        execute.sendMessage(SendMessage.builder()
                .chatId(message.getChatId().toString())
                .replyMarkup(replyMarkup)
                .text(text)
                .build());
    }
}
