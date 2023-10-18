package kz.kuanysh.bot.bot.update;

import kz.kuanysh.bot.bot.strategy.CallbackQueryUpdateStrategy;
import kz.kuanysh.bot.bot.strategy.TextMessageUpdateStrategy;
import kz.kuanysh.bot.bot.strategy.UpdateStrategy;
import kz.kuanysh.bot.exceptions.UpdateIsNullException;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UpdateStrategyFactory {
    public static UpdateStrategy createStrategy(Update update) {
        if (update == null){
            throw new UpdateIsNullException("Error occurred: Update is null");
        }

        if (update.hasCallbackQuery()) {
            return new CallbackQueryUpdateStrategy();
        }
        return new TextMessageUpdateStrategy();
    }
}
