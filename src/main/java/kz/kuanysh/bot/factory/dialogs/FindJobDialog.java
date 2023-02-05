package kz.kuanysh.bot.factory.dialogs;

import org.telegram.telegrambots.meta.api.objects.Message;

public class FindJobDialog implements Dialog {
    @Override
    public String getText(Message message) {
        return "Выберите в какая работа вам нужна : ";
    }
}
