package kz.kuanysh.bot.factory.message;

import kz.kuanysh.bot.buttons.CreateButton;
import kz.kuanysh.bot.buttons.InlineListButton;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class ChoiceMessage implements Sender {

    public List<String> listChoice() {
        return List.of("найти работу",
                "найти сотрудника");
    }

    public List<String> listChoiceCallBack() {
        return List.of("/findjob",
                "/findworker");
    }

    @Override
    public BotApiMethod<Message> sendMessage(Message message, String content) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = InlineListButton.listButtons(listChoice(),listChoiceCallBack());
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return CreateButton.sendInline(message.getChatId(), content, inlineKeyboardMarkup);

    }
}
