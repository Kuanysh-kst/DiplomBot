package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.InlineListButton;
import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class ChoiceState implements UserActivity {
    public List<String> listChoice() {
        return List.of("найти работу",
                "найти сотрудника",
                "назад в главное меню");
    }

    public List<String> listChoiceCallBack() {
        return List.of("/findjob",
                "/findworker",
                "/back");
    }

    @Override
    public UserActivity nextDialogState() {
        return new CategoryState();
    }


    @Override
    public UserActivity backDialogState() {
        return new StartState();
    }

    @Override
    public String getText(Message message) {
        return "Вы хотите найти работу или \n" +
                "вы хотите нанять сотрудника?";
    }

    @Override
    public BotApiMethod getKeyBoard(Message message, String text, String command) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = InlineListButton.listButtons(listChoice(), listChoiceCallBack());
        inlineKeyboardMarkup.setKeyboard(keyboard);
        if (command.equals("/back")) {
            return PatternKeyboard.sendEdit(message, text, inlineKeyboardMarkup);
        } else {
            return PatternKeyboard.sendInline(message.getChatId(), text, inlineKeyboardMarkup);
        }
    }
}
