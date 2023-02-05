package kz.kuanysh.bot.buttons;

import kz.kuanysh.bot.config.BotConfig;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.factories.dialog.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
public class CommandController {

    public static DialogFactory createDialogFactory(String text, Long chatId, BotConfig botConfig) {

        log.info("Command:" + text + " , from:" + chatId);
        Map<String, Supplier<DialogFactory>> factories = new HashMap<>();
        factories.put("/start", StartDialogFactory::new);
        factories.put("next", ChoiceDialogFactory::new);
        factories.put("/findjob", FindJobDialogFactory::new);
        factories.put("/findworker", FindWorkerFactory::new);
        factories.put("/help", HelpFactory::new);
        factories.put("nextBack", ChoiceEditFactory::new);
        factories.put("/constructionWork", GeoFactory::new);
        factories.put("/workLoader", GeoFactory::new);
        factories.put("/delivery work", GeoFactory::new);
        factories.put("/workCafe", GeoFactory::new);
        factories.put("/cleaningWork", GeoFactory::new);
        Supplier<DialogFactory> supplier = factories.getOrDefault(text, SorryDialogFactory::new);
        return supplier.get();
    }
}
