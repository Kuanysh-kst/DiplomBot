package kz.kuanysh.bot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);

    private final String botUserName;

    private final String botToken;

    public Bot(String botUserName, String botToken) {
        this.botUserName = botUserName;
        this.botToken = botToken;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()){
            SendMessage response;

            LOGGER.info("Пользователь "+update.getMessage().getChat().getUserName()+" прислал сообщение "+update.getMessage().getText());
            response = new SendMessage(update.getMessage().getChatId().toString(),"Echo: "+update.getMessage().getText());
            try{
                execute(response);
            }catch (TelegramApiException e){
                LOGGER.error("Could not send echo message to the user", e);
            }
        }
    }
}
