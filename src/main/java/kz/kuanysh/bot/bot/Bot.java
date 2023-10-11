package kz.kuanysh.bot.bot;

import kz.kuanysh.bot.bot.strategy.CallbackQueryUpdateStrategy;
import kz.kuanysh.bot.bot.strategy.TextMessageUpdateStrategy;
import kz.kuanysh.bot.bot.strategy.UpdateStrategy;
import kz.kuanysh.bot.bot.strategy.UpdateStrategyFactory;
import kz.kuanysh.bot.buttons.SendModels;
import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.chain.chains.AboutChain;
import kz.kuanysh.bot.chain.chains.CategoryDialogChain;
import kz.kuanysh.bot.chain.chains.ChoiceDialogChiang;
import kz.kuanysh.bot.chain.chains.ContactChain;
import kz.kuanysh.bot.chain.chains.FinishChain;
import kz.kuanysh.bot.chain.chains.LocationChain;
import kz.kuanysh.bot.chain.chains.PhotoChain;
import kz.kuanysh.bot.chain.chains.ResultChain;
import kz.kuanysh.bot.chain.chains.ShowResultChain;
import kz.kuanysh.bot.chain.chains.StartDialogChain;
import kz.kuanysh.bot.config.BotConfig;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.model.Ads;
import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.repository.AdsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private final BotConfig botConfig;

    @Autowired
    private AdsRepository adsRepository;
    private final UserService userService;
    private final DialogChain dialogChain;
    private final Map<Class<?>, UpdateStrategy> strategyMap = new HashMap<>();


    public Bot(BotConfig botConfig, UserService userService) {

        this.botConfig = botConfig;
        this.userService = userService;

        DialogChain finish = new FinishChain(null);
        DialogChain showResult = new ShowResultChain(finish);
        DialogChain result = new ResultChain(showResult);
        DialogChain location = new LocationChain(result);
        DialogChain contact = new ContactChain(location);
        DialogChain photo = new PhotoChain(contact);
        DialogChain getNumChain = new AboutChain(photo);
        DialogChain categoryDialogChain = new CategoryDialogChain(getNumChain);
        DialogChain choiceChain = new ChoiceDialogChiang(categoryDialogChain);
        this.dialogChain = new StartDialogChain(choiceChain);

        strategyMap.put(Message.class, new TextMessageUpdateStrategy());
        strategyMap.put(CallbackQuery.class, new CallbackQueryUpdateStrategy());
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
        userService.saveUserInBase(update);

        UpdateStrategy updateStrategy = UpdateStrategyFactory.createStrategy(update);
        assert updateStrategy != null;
        updateStrategy.handleUpdate(update, userService, new SendBotMessageServiceImp(this), dialogChain);
    }


    @Scheduled(cron = "${cron.scheduler}")
    private void sendAds() {
        var ads = adsRepository.findAll();
        var users = userService.findAll();

        for (Ads ad : ads) {
            for (User user : users) {
                try {
                    execute(SendModels.sendText(user.getChatId(), ad.getAd()));
                } catch (TelegramApiException e) {
                    log.error("Error occurred: " + e.getMessage());
                }
            }
        }
    }

}
