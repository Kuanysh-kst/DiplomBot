package kz.kuanysh.bot.factory.keyboards;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

public interface SenderKeyboard {
    <T extends Serializable> BotApiMethod<Serializable> sendMessage(Message message, String content);
}
