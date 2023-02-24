package kz.kuanysh.bot.commands;

public enum Commands {
    START("/start",false),
    NEXT("Далее",false),
    SET_CONTACT("Установить контакт", true),
    INTEGRATION("Реклама \uD83E\uDD41",false),
    FIND_WORKER("/findworker",false),
    FIND_JOB("/findjob",false),
    BACK("/back",false),
    ;

    private final String command;

    private final boolean request;

    public String getCommand() {
        return command;
    }

    public boolean getRequest() {
        return request;
    }

    Commands(String command, boolean request) {
        this.command = command;
        this.request = request;
    }
}
