package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.InlineListButton;
import kz.kuanysh.bot.buttons.SendModels;
import kz.kuanysh.bot.commands.Commands;
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
        return List.of(Commands.FIND_JOB.getCommand(),
                Commands.FIND_WORKER.getCommand(),
                Commands.BACK.getCommand());
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
    public void executeMessage(Message message, String text, String command, SendBotMessageServiceImp execute) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = InlineListButton.listButtons(listChoice(), listChoiceCallBack());
        inlineKeyboardMarkup.setKeyboard(keyboard);
        if (command.equals("/back")) {
            execute.sendMessageSerializable(SendModels.sendEdit(message, text, inlineKeyboardMarkup)
            );

        } else {
            execute.sendMessage(SendModels.sendMessage(message.getChatId(), text, inlineKeyboardMarkup));
        }
    }
}


