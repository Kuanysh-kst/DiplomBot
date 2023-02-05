package kz.kuanysh.bot.buttons;

import kz.kuanysh.bot.config.BotConfig;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.factories.dialog.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.function.Supplier;

@Slf4j
public class ButtonController {

    public static DialogFactory createDialogFactory(String text, Long chatId, BotConfig botConfig) {

        log.info(text);
        log.info(String.valueOf(chatId));
        Map<String, Supplier<DialogFactory>> factories = Map.of(
                "/start", StartDialogFactory::new,
                "next", ChoiceDialogFactory::new,
                "/findjob", FindJobDialogFactory::new,
                "/findworker", FindWorkerFactory::new,
                "/help", HelpFactory::new,
                "nextBack", ChoiceEditFactory::new
        );
        Supplier<DialogFactory> supplier = factories.getOrDefault(text, SorryDialogFactory::new);
        return supplier.get();
    }
}
