package kz.kuanysh.bot.factory;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface Dialog {
    public String getText(Message message);
}
