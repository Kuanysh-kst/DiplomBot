package kz.kuanysh.bot.bot;

import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.chain.DialogStateChain;
import kz.kuanysh.bot.chain.chains.GetNameDialogChain;
import kz.kuanysh.bot.chain.chains.ChoiceDialogChiang;
import kz.kuanysh.bot.chain.chains.StartDialogChain;
import kz.kuanysh.bot.chain.chains.CategoryDialogChain;
import kz.kuanysh.bot.config.BotConfig;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
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

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private final BotConfig botConfig;

    @Autowired
    private AdsRepository adsRepository;
    private final UserService userService;
    private final DialogStateChain dialogChain;

    public Bot(BotConfig botConfig, UserService userService) {

        this.botConfig = botConfig;
        this.userService = userService;

        DialogStateChain getNumChain = new GetNameDialogChain(null);
        DialogStateChain categoryDialogChain = new CategoryDialogChain(getNumChain);
        DialogStateChain choiceChain = new ChoiceDialogChiang(categoryDialogChain);
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
            userService.saveUserInBase(update.getMessage());
            Message message = update.getMessage();
            String command = message.getText();
            Dialog dialog = userService.findDialog(message);
            dialogChain.processState(message, dialog,command, userService, new SendBotMessageServiceImp(this));
        } else if (update.hasCallbackQuery()) {
            Message message = update.getCallbackQuery().getMessage();
            String command = update.getCallbackQuery().getData();
            Dialog dialog = userService.findDialog(message);
            dialogChain.processState(message, dialog,command, userService, new SendBotMessageServiceImp(this));
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
