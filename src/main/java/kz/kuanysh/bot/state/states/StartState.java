package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;

import java.util.ArrayList;

public class StartState implements UserActivity {
    @Override
    public UserActivity nextDialogState() {
        return new ChoiceState();
    }


    @Override
    public UserActivity backDialogState() {
        return null;
    }

    @Override
    public String getText(Message message) {
        return "Привет " + message.getChat().getFirstName()
                + " \uD83E\uDD20, это бот для поиска работы и найма сотрудников, \n" +
                "бот может работать со следующими командами \n" +
                "/help : для ознакомления";
    }

    @Override
    public void executeMessage(Message message, String text, String command, SendBotMessageServiceImp execute) {
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
        execute.sendMessage(SendMessage.builder()
                .chatId(message.getChatId().toString())
                .replyMarkup(replyMarkup)
                .text(text)
                .build());

    }
}

