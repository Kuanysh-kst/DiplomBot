package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.InlineListButton;
import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.state.UserActivity;
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
    public String getText(Message message, User user) {
        return "Вы хотите найти работу или \n" +
                "вы хотите нанять сотрудника?";
    }

    @Override
    public void executeMessage(Message message, String text, String command, SendBotMessageServiceImp execute) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = InlineListButton.listButtons(listChoice(), listChoiceCallBack());
        inlineKeyboardMarkup.setKeyboard(keyboard);
        if (command.equals("/back")) {
            execute.sendMessageSerializable(PatternKeyboard.sendEdit(message, text, inlineKeyboardMarkup)
            );

        } else {
            execute.sendMessage(PatternKeyboard.sendInline(message.getChatId(), text, inlineKeyboardMarkup));
        }
    }
}


