package kz.kuanysh.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Service
public class SendBotMessageServiceImp implements SendBotMessageService {

    @Autowired
    private final Bot bot;

    public SendBotMessageServiceImp(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(message)
                .build();
        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e);
        }
    }
}
