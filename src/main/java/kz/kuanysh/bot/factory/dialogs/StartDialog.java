package kz.kuanysh.bot.factory.dialogs;

import kz.kuanysh.bot.factory.Dialog;
import org.telegram.telegrambots.meta.api.objects.Message;

public class StartDialog implements Dialog {
    @Override
    public String getText(Message message) {
        return "Привет " + message.getChat().getFirstName()
                + ", это бот для поиска работы и найма сотрудников, \n" +
                "бот может работать со следующими командами \n" +
                "";
    }
}
