package kz.kuanysh.bot.commands;

public enum Commands {
    SKIP("пропустить", "/skip"),
    SET_CONTACT("Зарегистрировать контакт", "/setContact"),
    START("/start", "/start"),
    SET_LOCATION("указать местоположение", "/setLocation"),
    NEXT("Далее", "/next"),
    FIND_WORKER("найти сотрудника", "/findworker"),
    FIND_JOB("найти работу", "/findjob"),
    BACK("назад", "/back"),
    GO_TO_MENU("назад в главное меню", "/goToMenu"),
    GET_RESULT("получить результат", "/result"),
    CONSTRUCTION_WORK("Стройтельные работы", "Стройтельные работы"),
    WORK_LOADER("Работа грузчиком", "Работа грузчиком"),
    DELIVERY_WORK("Работа по доставке", "Работа по доставке"),
    WORK_CAFE("Работа в кафе", "Работа в кафе"),
    CLEANING_WORK("Клининг работы", "Клининг работы"),
    RIGHT("право", "➡"),
    LEFT("влево", "⬅"),
    UNNECESSARY_NUMBER("Этот номер не нужен", "/unnecessary_number"),
    HELP("Помощь", "/help"),
    HELP_TEXT("Этот телеграм бот имеет отличный функционал для подбора людей по их вакансиям," +
            "вакансии в этом боте выбираются на основе выбранной вами категорий, " +
            "также стоит обратить внимание ,что вы можете выбирать, " +
            "будете ли вы искать сотрудника или будете искать работу." +
            "Бот имеет WebApp , покамись в тестовом режиме, " +
            "основной функционал имеется в самом боте.", "/helpText"),
    HMSTR("HMSTR","/hmstr");

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
