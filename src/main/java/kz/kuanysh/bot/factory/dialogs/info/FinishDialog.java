package kz.kuanysh.bot.factory.dialogs.info;

import kz.kuanysh.bot.factory.dialogs.Dialog;
import org.telegram.telegrambots.meta.api.objects.Message;

public class FinishDialog implements Dialog {
    @Override
    public String getText(Message message) {
        return "it's finish bro";
    }
}
