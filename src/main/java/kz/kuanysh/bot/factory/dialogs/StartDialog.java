package kz.kuanysh.bot.factory.dialogs;

import kz.kuanysh.bot.factory.Dialog;

public class StartDialog implements Dialog {
    @Override
    public String getText() {
        return  "Привет это бот для поиска работы \n"+
                "бот для поиска работы имеет может работать со следующими командами \n"+
                "/second";
    }
}
