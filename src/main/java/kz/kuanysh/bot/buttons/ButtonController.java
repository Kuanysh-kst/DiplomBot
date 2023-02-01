package kz.kuanysh.bot.buttons;

import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.factories.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.function.Supplier;

@Slf4j
public class ButtonController {

    public static DialogFactory createDialogFactory(String text) {
        log.info(text);
        Map<String, Supplier<DialogFactory>> factories = Map.of(
                "/start", StartDialogFactory::new,
                "next", ChoiceDialogFactory::new,
                "/findjob", FindJobDialogFactory::new,
                "/findworker", FindWorkerFactory::new,
                "/help", HelpFactory::new
        );
        Supplier<DialogFactory> supplier = factories.getOrDefault(text, SorryDialogFactory::new);
        return supplier.get();
    }
}
