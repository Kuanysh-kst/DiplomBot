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
}
