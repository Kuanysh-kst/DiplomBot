package kz.kuanysh.bot.service;

import kz.kuanysh.bot.bot.Bot;
import kz.kuanysh.bot.service.interfaces.SendBotMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

@Slf4j
@Service
public class SendBotMessageServiceImp implements SendBotMessageService {

    @Autowired
    private final Bot bot;

    public SendBotMessageServiceImp(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void sendMessageSerializable(BotApiMethod<Serializable> response) {
        try {
            bot.execute(response);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e);
        }
    }

    public void sendMessage(BotApiMethod<Message> response) {
        try {
            bot.execute(response);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e);
        }
    }


}
