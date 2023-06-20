package kz.kuanysh.bot.buttons;

import kz.kuanysh.bot.commands.Commands;

import java.util.List;

public class Command {
    public static Boolean callBackWorkNames(String command) {
        return List.of(
                "Стройтельные работы",
                "Работа грузчиком",
                "Работа по доставке",
                "Работа в кафе",
                "Клининг работы"
        ).contains(command);
    }

    public static Boolean listChoiceCallBack(String command) {
        return List.of(Commands.FIND_JOB.getCallback(),
                Commands.FIND_WORKER.getCallback()
               ).contains(command);
    }
}
