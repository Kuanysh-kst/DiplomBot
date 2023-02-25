package kz.kuanysh.bot.commands;

public enum Commands {
    SKIP("пропустить","/skip"),
    SET_CONTACT("Установить контакт", "/setContact"),
    START("/start","/start"),
    SET_LOCATION("указать местоположение","/setLocation"),
    NEXT("Далее","/next"),
    INTEGRATION("Реклама \uD83E\uDD41","/integration"),
    FIND_WORKER("найти сотрудника","/findworker"),
    FIND_JOB("найти работу","/findjob"),
    BACK("назад","/back"),
    GO_TO_MENU("назад в главное меню","/goToMenu"),
    GET_RESULT("получить результат","/result"),
    CONSTRUCTION_WORK("стройтельные работы","/constructionWork"),
    WORK_LOADER("работа грузчиком","/workLoader"),
    DELIVERY_WORK( "работа по доставке","/delivery work"),
    WORK_CAFE("работа в кафе","/workCafe"),
    CLEANING_WORK("клининг работы","/cleaningWork"),
    ;
    private final String text;

    private final String callBackText;
    public String getText() {
        return text;
    }

    public String getCallback() {
        return this.callBackText;
    }

    Commands(String text, String callBackText) {
        this.text = text;
        this.callBackText = callBackText;
    }
}
