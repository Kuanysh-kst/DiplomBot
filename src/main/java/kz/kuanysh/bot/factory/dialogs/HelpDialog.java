package kz.kuanysh.bot.factory.dialogs;

import kz.kuanysh.bot.factory.Dialog;
import org.telegram.telegrambots.meta.api.objects.Message;

public class HelpDialog implements Dialog {
    @Override
    public String getText(Message message) {
        return message.getChat().getId() + " this bot is create to find some job";
    }
}
