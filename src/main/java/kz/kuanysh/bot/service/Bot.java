package kz.kuanysh.bot.service;

import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.chain.DialogStateChain;
import kz.kuanysh.bot.chain.chains.CategoryDialogChain;
import kz.kuanysh.bot.chain.chains.ChoiceDialogChiang;
import kz.kuanysh.bot.chain.chains.StartDialogChain;
import kz.kuanysh.bot.chain.chains.StatusDialogChain;
import kz.kuanysh.bot.config.BotConfig;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.model.Ads;
import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.repository.AdsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private final BotConfig botConfig;

    @Autowired
    private AdsRepository adsRepository;
    private final UserService userService;
    HashMap<Long, Dialog> users = new HashMap<>();
    private final DialogStateChain dialogChain;

    public Bot(BotConfig botConfig, UserService userService) {

        this.botConfig = botConfig;
        this.userService = userService;

        DialogStateChain categoryChain = new CategoryDialogChain(null);
        DialogStateChain statusChain = new StatusDialogChain(categoryChain);
        DialogStateChain choiceChain = new ChoiceDialogChiang(statusChain);
        this.dialogChain = new StartDialogChain(choiceChain);
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
//            userService.saveUserInBase(message);
            Long chatId = message.getChatId();
            Dialog dialog = userService.findDialog(message);
            log.info("from rep: "+ dialog.getState().getClass().getSimpleName());
            messageState(message, dialog);
//            MessageHandler.executeResponse(factory, message, new SendBotMessageServiceImp(this));
        }
//        } else if (update.hasCallbackQuery()) {
//            Message message = update.getCallbackQuery().getMessage();
//            String text = update.getCallbackQuery().getData();
//            Long chatId = message.getChatId();
////            var factory = MessageHandler.factoryControl(text, chatId, message, userService);
//            MessageHandler.executeResponse(factory, message, new SendBotMessageServiceImp(this));
//        }
    }

    private void messageState(Message message, Dialog context) {
        userService.saveDialog(message, context);
        log.info("to save in rep: "+context.getState().getClass().getSimpleName());
        var response = dialogChain.processState(message, context,userService);

        try {
            execute(response);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(cron = "${cron.scheduler}")
    private void sendAds() {
        var ads = adsRepository.findAll();
        var users = userService.findAll();

        for (Ads ad : ads) {
            for (User user : users) {
                try {
                    execute(PatternKeyboard.sendText(user.getChatId(), ad.getAd()));
                } catch (TelegramApiException e) {
                    log.error("Error occurred: " + e.getMessage());
                }
            }
        }
    }

}
