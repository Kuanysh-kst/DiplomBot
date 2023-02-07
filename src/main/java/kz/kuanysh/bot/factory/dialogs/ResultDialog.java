package kz.kuanysh.bot.factory.dialogs;

import org.telegram.telegrambots.meta.api.objects.Message;

public class ResultDialog implements Dialog {
    @Override
    public String getText(Message message) {
        return "На основе вашего выбора \n" +
                "вы получите результат \uD83D\uDCE0";
    }
}
