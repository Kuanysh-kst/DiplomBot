package kz.kuanysh.bot.factory.dialogs;

import kz.kuanysh.bot.factory.Dialog;
import org.telegram.telegrambots.meta.api.objects.Message;

public class SorryDialog implements Dialog {
    @Override
    public String getText(Message message) {
        return "Я ещё не знаю как ответить на эту команду 👾";
    }
}