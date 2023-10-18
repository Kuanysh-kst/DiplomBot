package kz.kuanysh.bot.bot;

import kz.kuanysh.bot.bot.strategy.UpdateStrategy;
import kz.kuanysh.bot.bot.update.UpdateStrategyFactory;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private final BotConfig botConfig;

    @Autowired
    private final UserService userService;
    private final DialogChain dialogChain;


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
        updateStrategy.handleUpdate(update, userService, new SendBotMessageServiceImp(this), dialogChain);
    }

}
