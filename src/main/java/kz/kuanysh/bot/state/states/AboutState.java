package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.List;

public class AboutState implements UserActivity {


    @Override
    public UserActivity nextDialogState() {
        return new PhotoState();
    }

    @Override
    public UserActivity backDialogState() {
        return new CategoryState();
    }

    @Override
    public String getText(Message message, User user) {
        return "Напишите о себе \uD83E\uDD2A, это важно для ознакомления";
    }

    @Override
    public void executeMessage(Message message, String text, String command, SendBotMessageServiceImp execute) {
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
        execute.sendMessageSerializable(PatternKeyboard.sendEdit(message, text, inlineKeyboardMarkup));
    }
}
