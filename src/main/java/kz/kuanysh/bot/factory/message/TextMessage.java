package kz.kuanysh.bot.factory.message;

import kz.kuanysh.bot.buttons.CreateButton;
import kz.kuanysh.bot.factory.Sender;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

public class TextMessage implements Sender {
    @Override
    public  BotApiMethod<Message> sendMessage(Message message, String content) {
        return CreateButton.sendText(message,content);
    }
}
