package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.state.StateDialog;
import kz.kuanysh.bot.state.UserActivity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StartState implements UserActivity<String> {


    @Override
    public UserActivity nextDialogState(String command) {
        log.info("it's StartState");
        return new UrlState(command);
    }

    @Override
    public String getContent() {
        return "если вы отправилил команду /send то \n"+
                "введите url картинки";

    }
}
