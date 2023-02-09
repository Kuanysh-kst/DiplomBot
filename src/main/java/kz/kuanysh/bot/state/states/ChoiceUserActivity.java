package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.InlineListButton;
import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class ChoiceUserActivity implements UserActivity<String> {
    public List<String> listChoice() {
        return List.of("найти работу",
                "найти сотрудника",
                "назад");
    }

    public List<String> listChoiceCallBack() {
        return List.of("/findjob",
                "/findworker",
                "/back");
    }

    @Override
    public UserActivity nextDialogState(String command) {
        return new StatusJobUserActivity(command);
    }

    @Override
    public UserActivity backDialogState() {
        return new StartUserActivity();
    }

    @Override
    public String getText(Message message) {
        return "Вы хотите найти работу или \n" +
                "вы хотите нанять сотрудника?";
    }

    @Override
    public BotApiMethod getKeyBoard(Message message, String text) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = InlineListButton.listButtons(listChoice(),listChoiceCallBack());
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return PatternKeyboard.sendInline(message.getChatId(), text, inlineKeyboardMarkup);

    }


//    @Override
//    public Dialog createDialog() {
//        return new ChoiceDialog();
//    }
//
//    @Override
//    public Keyboard createKeyBoard() {
//        return new ChoiceKeyboard();
//    }
//
//    @Override
//    public void doEvent(UserService userService, Message message , String text) {
//        userService.saveUserInBase(message);
//    }
//
//    @Override
//    public void execute(BotApiMethod<Serializable> response, SendBotMessageServiceImp sendBotMessageServiceImp) {
//        sendBotMessageServiceImp.sendMessageSerializable(response);
//    }
}
