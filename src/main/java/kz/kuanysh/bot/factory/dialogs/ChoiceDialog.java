package kz.kuanysh.bot.factory.dialogs;

import kz.kuanysh.bot.factory.Dialog;

public class ChoiceDialog implements Dialog {
    @Override
    public String getText() {
        return "Вы хотите найти работу или \n"+
                "вы хотите нанять сотрудника?";
    }
}
