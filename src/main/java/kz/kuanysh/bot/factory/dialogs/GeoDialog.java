package kz.kuanysh.bot.factory.dialogs;

import org.telegram.telegrambots.meta.api.objects.Message;

public class GeoDialog implements Dialog {
    @Override
    public String getText(Message message) {
        return "Для продолжения поиска нужны геоданные,\n" +
                "поделитесь данными нажав кнопку: goedate";
    }
}
