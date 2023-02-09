package kz.kuanysh.bot.service.interfaces;

import org.apache.logging.log4j.message.Message;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.io.Serializable;

public interface SendBotMessageService {
    void sendMessageSerializable(BotApiMethod<Serializable> response);
}
