package kz.kuanysh.bot.factory.dialogs;

import kz.kuanysh.bot.factory.Dialog;

public class StartDialog implements Dialog {
    @Override
    public String getText() {
        return  ", это бот для поиска работы и найма сотрудников, \n"+
                "бот может работать со следующими командами \n"+
                "";
    }
}
