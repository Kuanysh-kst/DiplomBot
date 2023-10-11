package kz.kuanysh.bot.bot.strategy;

import org.telegram.telegrambots.meta.api.objects.Update;

public class UpdateStrategyFactory {
    public static UpdateStrategy createStrategy(Update update) {
        if (update.hasMessage()) {
            return new TextMessageUpdateStrategy();
        } else if (update.hasCallbackQuery()) {
            return new CallbackQueryUpdateStrategy();
        } else {
            return null;
        }
    }
}
