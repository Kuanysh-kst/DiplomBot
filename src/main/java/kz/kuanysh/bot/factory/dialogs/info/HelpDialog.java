package kz.kuanysh.bot.factory.dialogs.info;

import kz.kuanysh.bot.factory.dialogs.Dialog;
import org.telegram.telegrambots.meta.api.objects.Message;

public class HelpDialog implements Dialog {
    @Override
    public String getText(Message message) {
        return message.getChat().getId() + "\uD83E\uDDD1\u200D\uD83C\uDF3E this bot is create to find some job";
    }
}
