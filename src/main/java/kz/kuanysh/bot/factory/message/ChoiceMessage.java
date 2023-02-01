package kz.kuanysh.bot.factory.message;

import kz.kuanysh.bot.buttons.CreateButton;
import kz.kuanysh.bot.factory.Sender;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;

import java.util.ArrayList;
import java.util.List;

public class ChoiceMessage implements Sender {
    @Override
    public BotApiMethod<Message> sendMessage(Message message, String content) {
        InlineKeyboardMarkup inlineKeyboardMarkup   = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        InlineKeyboardButton firstButton = InlineKeyboardButton.builder()
                .text("найти работу ")
                .callbackData("/findjob")
                .build();

        InlineKeyboardButton secondButton = InlineKeyboardButton.builder()
                .text("найти сотрудника")
                .callbackData("/findworker")
                .build();

        firstRow.add(firstButton);
        secondRow.add(secondButton);
        keyboard.add(firstRow);
        keyboard.add(secondRow);

        inlineKeyboardMarkup.setKeyboard(keyboard);

        return CreateButton.sendInline(message.getChatId(),content, inlineKeyboardMarkup);

    }
}
