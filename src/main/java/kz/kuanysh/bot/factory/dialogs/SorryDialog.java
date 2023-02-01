package kz.kuanysh.bot.factory.dialogs;

import kz.kuanysh.bot.factory.Dialog;

public class SorryDialog implements Dialog {
    @Override
    public String getText() {
        return "Я ещё не знаю как ответить на эту команду 👾";
    }
}
