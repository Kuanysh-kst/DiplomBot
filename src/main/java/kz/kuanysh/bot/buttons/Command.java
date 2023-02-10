package kz.kuanysh.bot.buttons;

import java.util.List;

public class Command {
    public static Boolean callBackWorkNames(String command) {
        return List.of(
                "/constructionWork",
                "/workLoader",
                "/delivery work",
                "/workCafe",
                "/cleaningWork"
        ).contains(command);
    }

    public static Boolean listChoiceCallBack(String command) {
        return List.of("/findjob",
                "/findworker"
               ).contains(command);
    }
}
