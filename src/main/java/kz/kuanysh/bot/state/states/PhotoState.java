package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.SendModels;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class PhotoState implements UserActivity {


    @Override
    public UserActivity nextDialogState() {
        return new ContactState();
    }


    @Override
    public UserActivity backDialogState() {
        return new AboutState();
    }

    @Override
    public String getText(Message message) {
        return "Поделитесь фото для просмотра профиля \uD83C\uDFC4\u200D♂️";
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
        execute.sendMessage(SendModels.sendMessage(message.getChatId(), text, inlineKeyboardMarkup));
    }
}
