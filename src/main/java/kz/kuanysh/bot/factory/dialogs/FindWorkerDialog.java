package kz.kuanysh.bot.factory.dialogs;

import kz.kuanysh.bot.factory.Dialog;

public class FindWorkerDialog implements Dialog {
    @Override
    public String getText() {
        return "Выберите в какой отрасли \n"+
                "вы ищите сотрудника :";
    }
}
