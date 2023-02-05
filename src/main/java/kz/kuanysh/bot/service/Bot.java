package kz.kuanysh.bot.service;

import kz.kuanysh.bot.buttons.CreateButton;
import kz.kuanysh.bot.config.BotConfig;
import kz.kuanysh.bot.model.Ads;
import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.repository.AdsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Autowired
    private AdsRepository adsRepository;
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
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String text = message.getText();
            Long chatId = message.getChatId();

            var factory = MessageHandler.factoryControl(text, chatId, botConfig);
            var response = MessageHandler.createSendMessage(factory, message,userService);

            executeMessage(response);
        } else if (update.hasCallbackQuery()) {
            Message message = update.getCallbackQuery().getMessage();
            String text = update.getCallbackQuery().getData();
            Long chatId = message.getChatId();

            var factory = MessageHandler.factoryControl(text, chatId, botConfig);
            var response = MessageHandler.createSendMessage(factory, message,userService);

            executeMessage(response);
        }
    }

    private void executeMessage(BotApiMethod<Serializable> response) {

        try {
            execute(response);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    @Scheduled(cron = "${cron.scheduler}")
    private void sendAds() {
        var ads = adsRepository.findAll();
        var users = userService.findAll();

        for (Ads ad : ads) {
            for (User user : users) {
                try {
                    execute(CreateButton.sendText(user.getChatId(), ad.getAd()));
                } catch (TelegramApiException e) {
                    log.error("Error occurred: " + e.getMessage());
                }
            }
        }
    }

}
