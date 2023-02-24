package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.commands.Commands;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

public class ContactState implements UserActivity {

    @Override
    public UserActivity nextDialogState() {
        return new LocationState();
    }

    @Override
    public UserActivity backDialogState() {
        return new PhotoState();
    }

    @Override
    public String getText(Message message) {
        return "Для дальнейшего пойска нужен ваш контак \uD83D\uDCDE";
    }

    @Override
    public void executeMessage(Message message, String text, String command, SendBotMessageServiceImp execute) {

        ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();
        replyMarkup.setOneTimeKeyboard(true);
        var keyboard = new ArrayList<KeyboardRow>();
        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();
        KeyboardButton contact = KeyboardButton.builder()
                .text(Commands.SET_CONTACT.getText())
                .requestContact(true)
                .build();
        KeyboardButton back = KeyboardButton.builder()
                .text(Commands.BACK.getText())
                .build();
        firstRow.add(contact);
        secondRow.add(back);

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
