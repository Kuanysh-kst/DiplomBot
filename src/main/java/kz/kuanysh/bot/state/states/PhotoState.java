package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class PhotoState implements UserActivity<InputFile> {
    private final String choice;
    private final String category;
    private final String about;

    public PhotoState(String choice, String category, String about) {
        this.choice = choice;
        this.category = category;
        this.about = about;
    }

    @Override
    public UserActivity nextDialogState(InputFile par) {
        return new ContactState(choice, category, about, par);
    }

    @Override
    public UserActivity backDialogState() {
        return new AboutState(choice,category);
    }

    @Override
    public String getText(Message message) {
        return "Поделитесь фото для просмотра профиля \uD83C\uDFC4\u200D♂️";
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
                .text("пропустить")
                .callbackData("/skip")
                .build();
        row1.add(button);
        row2.add(skip);
        keyboard.add(row2);
        keyboard.add(row1);

        inlineKeyboardMarkup.setKeyboard(keyboard);
        return PatternKeyboard.sendInline(message.getChatId(), text, inlineKeyboardMarkup);
    }
}
