package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;

import java.util.ArrayList;

public class StartState implements UserActivity<String> {
    @Override
    public UserActivity nextDialogState(String par) {
        return new ChoiceState();
    }

    @Override
    public UserActivity backDialogState() {
        return null;
    }

    @Override
    public String getText(Message message) {
        return  "Привет " + message.getChat().getFirstName()
                + " \uD83E\uDD20, это бот для поиска работы и найма сотрудников, \n" +
                "бот может работать со следующими командами \n" +
                "/help : для ознакомления";
    }

    @Override
    public BotApiMethod<Message> getKeyBoard(Message message ,String text,String command) {
                WebAppInfo webAppInfo = new WebAppInfo();
        webAppInfo.setUrl("https://hh.kz/");
        ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup();
        replyMarkup.setOneTimeKeyboard(true);
        var keyboard = new ArrayList<KeyboardRow>();
        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();
        firstRow.add("next");
        KeyboardButton about = KeyboardButton.builder()
                .text("setContact")
                .requestContact(true)
                .build();
        secondRow.add(about);
        secondRow.add("Реклама \uD83E\uDD41");
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        replyMarkup.setResizeKeyboard(true);
        replyMarkup.setKeyboard(keyboard);
        return SendMessage.builder()
                .chatId(message.getChatId().toString())
                .replyMarkup(replyMarkup)
                .text(text)
                .build();
    }
//
//    @Override
//    public Dialog createDialog() {
//        return new StartDialog();
//    }
//
//    @Override
//    public Keyboard createKeyBoard() {
//        return new StartKeyboard();
//    }
//
//    @Override
//    public void doEvent(UserService userService, Message message, String text) {
//        userService.saveUserInBase(message);
//    }
//
//    @Override
//    public void execute(BotApiMethod<Serializable> response, SendBotMessageServiceImp sendBotMessageServiceImp) {
//        sendBotMessageServiceImp.sendMessageSerializable(response);
//    }
}

