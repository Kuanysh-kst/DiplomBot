package kz.kuanysh.bot.buttons;

import kz.kuanysh.bot.config.BotConfig;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.factories.admin.SendFactory;
import kz.kuanysh.bot.factory.factories.dialog.*;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

@Slf4j
public class ButtonController {

    public static DialogFactory createDialogFactory(String text,Long chatId, BotConfig botConfig) {

        if (chatId.equals(botConfig.getOwnerId())) {
            log.info(text);
            Map<String, Supplier<DialogFactory>> factories = Map.of(
                    "/start", StartDialogFactory::new,
                    "next", ChoiceDialogFactory::new,
                    "/findjob", FindJobDialogFactory::new,
                    "/findworker", FindWorkerFactory::new,
                    "/help", HelpFactory::new,
                    "nextBack", ChoiceEditFactory::new,
                    "/send", SendFactory::new
            );
            Supplier<DialogFactory> supplier = factories.getOrDefault(text, SorryDialogFactory::new);
            return supplier.get();
        } else {
            log.info(text);
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
}
