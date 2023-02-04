package kz.kuanysh.bot.factory.message;

import kz.kuanysh.bot.buttons.CreateButton;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

public class TextMessage implements Sender {
    @Override
    public  BotApiMethod<Message> sendMessage(Message message, String content) {
        return CreateButton.sendText(message,content);
    }
}
