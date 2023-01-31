package kz.kuanysh.bot.factory.dialogs;

import kz.kuanysh.bot.factory.Dialog;

public class FindJobDialog implements Dialog {
    @Override
    public String getText() {
        return "Выберите в какая работа вам нужна : ";
    }
}
