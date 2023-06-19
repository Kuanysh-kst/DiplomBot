package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.commands.Commands;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

import static java.util.List.of;

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
    public ReplyKeyboard getMarkup() {
        InlineKeyboardButton back = InlineKeyboardButton.builder()
                .text(Commands.GO_TO_MENU.getText())
                .callbackData(Commands.GO_TO_MENU.getText())
                .build();
        InlineKeyboardButton getResult = InlineKeyboardButton.builder()
                .text(Commands.GET_RESULT.getText())
                .callbackData(Commands.GET_RESULT.getCallback())
                .build();
        List<List<InlineKeyboardButton>> keyboard = of(of(getResult), of(back));
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(keyboard);
        return markup;
    }
}
