package kz.kuanysh.bot.buttons;

import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.factories.ChoiceDialogFactory;
import kz.kuanysh.bot.factory.factories.FindJobDialogFactory;
import kz.kuanysh.bot.factory.factories.FindWorkerFactory;
import kz.kuanysh.bot.factory.factories.StartDialogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.function.Supplier;

public class ButtonController {
    private static Logger LOGGER = LoggerFactory.getLogger(ButtonController.class);

    public static DialogFactory createDialogFactory(String text) {
        LOGGER.info(text);
        Map<String, Supplier<DialogFactory>> factories = Map.of(
                "next", ChoiceDialogFactory::new,
                "/findJob", FindJobDialogFactory::new,
                "/findWorker", FindWorkerFactory::new
        );
        Supplier<DialogFactory> supplier = factories.getOrDefault(text, StartDialogFactory::new);
        return supplier.get();
    }
}