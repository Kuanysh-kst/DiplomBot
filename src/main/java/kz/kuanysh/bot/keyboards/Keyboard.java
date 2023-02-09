package kz.kuanysh.bot.keyboards;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

public interface Keyboard {
    <T extends Serializable> BotApiMethod<Serializable> sendMessage(Message message, String content);
}
