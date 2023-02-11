package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AboutState implements UserActivity<String> {

    private final String category;
    private final String choice;

    public AboutState(String choice, String category) {
        this.choice = choice;
        this.category = category;
    }

    @Override
    public UserActivity nextDialogState(String par) {
        return new PhotoState(choice, category, par);
    }

    @Override
    public UserActivity backDialogState() {
        return new CategoryState(choice);
    }

    @Override
    public String getText(Message message) {
        return "Напишите о себе \uD83E\uDD2A, это важно для ознакомления";
    }

    @Override
    public BotApiMethod<Serializable> getKeyBoard(Message message, String text, String command) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton button = InlineKeyboardButton.builder()
                .text("назад")
                .callbackData("/back")
                .build();
        InlineKeyboardButton skip = InlineKeyboardButton.builder()
                .text("пропустить")
                .callbackData("/skip")
                .build();
        row1.add(button);
        row2.add(skip);
        keyboard.add(row2);
        keyboard.add(row1);

        inlineKeyboardMarkup.setKeyboard(keyboard);
        return PatternKeyboard.sendEdit(message, text, inlineKeyboardMarkup);
    }
}
