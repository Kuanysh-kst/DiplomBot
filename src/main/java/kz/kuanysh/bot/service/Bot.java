package kz.kuanysh.bot.service;

import kz.kuanysh.bot.config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;


@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private final BotConfig botConfig;

    private final UserService userService;

    public Bot(BotConfig botConfig, UserService userService) {
        this.botConfig = botConfig;
        this.userService = userService;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotUserName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        var factory = MessageHandler.factoryControl(update,userService);
        var response = MessageHandler.createSendMessage(factory, update);
        executeMessage(response);
    }

    private void executeMessage(BotApiMethod<Serializable> response) {

        try {
            execute(response);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

}
