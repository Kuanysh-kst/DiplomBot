package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ResultState implements UserActivity {
    @Override
    public UserActivity nextDialogState() {
        return new ShowResultState();
    }

    @Override
    public UserActivity backDialogState() {
        return new LocationState();
    }

    @Override
    public String getText(Message message) {
        return "Результат основан на ваших данных \n " +
                "подробнее о лицах которые вам пришли " +
                "вы можете узнать через кнопку  show \uD83D\uDCE0: ";
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
                .text("получить результат")
                .callbackData("/result")
                .build();
        row1.add(button);
        row2.add(skip);
        keyboard.add(row2);
        keyboard.add(row1);

        inlineKeyboardMarkup.setKeyboard(keyboard);
        execute.sendMessage(PatternKeyboard.sendInline(message.getChatId(), text, inlineKeyboardMarkup));
    }
}
