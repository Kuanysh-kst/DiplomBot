package kz.kuanysh.bot.factory;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

public interface Sender {
    <T extends Serializable> BotApiMethod<T> sendMessage(Message message, String content);
}
