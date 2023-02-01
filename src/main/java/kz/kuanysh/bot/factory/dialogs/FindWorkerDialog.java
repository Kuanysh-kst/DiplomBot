package kz.kuanysh.bot.factory.dialogs;

import kz.kuanysh.bot.factory.Dialog;
import org.telegram.telegrambots.meta.api.objects.Message;

public class FindWorkerDialog implements Dialog {
    @Override
    public String getText(Message message) {
        return "Выберите в какой отрасли \n"+
                "вы ищите сотрудника :";
    }
}
