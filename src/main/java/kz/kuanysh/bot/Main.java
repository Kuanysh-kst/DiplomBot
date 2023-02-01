package kz.kuanysh.bot;

import kz.kuanysh.bot.service.Bot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args)  throws TelegramApiException {
        var botUserName = System.getenv("BOT_NAME");
        var botToken = System.getenv("BOT_TOKEN");

        var botsApi = new TelegramBotsApi(DefaultBotSession.class);

        botsApi.registerBot(new Bot(botUserName,botToken));
        LOGGER.info("Diplom bot app had started");
    }
}
