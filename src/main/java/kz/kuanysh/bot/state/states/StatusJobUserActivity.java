package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.InlineListButton;
import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.Serializable;
import java.util.List;

public class StatusJobUserActivity implements UserActivity<String> {

    private final String status;

    public StatusJobUserActivity(String status) {
        this.status = status;
    }

    public List<String> listChoice() {
        return List.of("NotStatus",
                "Status");
    }

    public List<String> listChoiceCallBack() {
        return List.of("/findNotStatus",
                "/findStatus");
    }

    @Override
    public UserActivity nextDialogState(String command) {
        return new CategoryActivity(status, command);
    }

    @Override
    public UserActivity backDialogState() {
        return new ChoiceUserActivity();
    }

    @Override
    public String getContent(Message message) {
        return "Выберите свой статус ";
    }

    @Override
    public <T extends Serializable> BotApiMethod getKeyBoard(Message message, String text) {
       InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = InlineListButton.listButtons(listChoice(),listChoiceCallBack());
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return PatternKeyboard.sendInline(message.getChatId(), text, inlineKeyboardMarkup);
    }


//    @Override
//    public Dialog createDialog() {
//        return new FindJobDialog();
//    }
//
//    @Override
//    public Keyboard createKeyBoard() {
//        return new FindJobKeyboard();
//    }
//
//    @Override
//    public void doEvent(UserService userService, Message message , String text) {
//        userService.saveUserStatus(message , text);
//    }
//
//    @Override
//    public void execute(BotApiMethod<Serializable> response, SendBotMessageServiceImp sendBotMessageServiceImp) {
//        sendBotMessageServiceImp.sendMessageSerializable(response);
//    }
}
