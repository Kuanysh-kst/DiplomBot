package kz.kuanysh.bot.factory.dialogs;

import kz.kuanysh.bot.factory.Dialog;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ChoiceDialog implements Dialog {
    @Override
    public String getText(Message message) {
        return "Вы хотите найти работу или \n"+
                "вы хотите нанять сотрудника?";
    }
}
