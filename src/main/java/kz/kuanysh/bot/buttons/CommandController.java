package kz.kuanysh.bot.buttons;

import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.*;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
public class CommandController {

//    public static UserActivity createDialogFactory(String text, Long chatId) {
//        log.info("Command:" + text + " , from:" + chatId);
//        Map<String, Supplier<UserActivity>> factories = new HashMap<>();
//        factories.put("/start", StartUserActivity::new);
//        factories.put("next", ChoiceUserActivity::new);
//        factories.put("/findjob", StatusJobUserActivity::new);
//        factories.put("/findworker", () -> new CategoryActivity(status, category));
//        factories.put("/help", HelpActivity::new);
//        factories.put("nextBack", ChoiceEditActivity::new);
//        factories.put("/getResult", ResultActivity::new);
//        factories.put("/constructionWork", ResultActivity::new);
//        factories.put("/workLoader", ResultActivity::new);
//        factories.put("/delivery work", ResultActivity::new);
//        factories.put("/workCafe", ResultActivity::new);
//        factories.put("/cleaningWork", ResultActivity::new);
//        factories.put("/getResult", FinishActivity::new);
//
//        Supplier<UserActivity> supplier = factories.getOrDefault(text, SorryUserActivity::new);
//        return supplier.get();
//    }

}
