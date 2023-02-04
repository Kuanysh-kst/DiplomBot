package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.state.UserActivity;

public class UrlState implements UserActivity<String> {
    private final String url;

    public UrlState(String url) {
        this.url = url;
    }

    @Override
    public UserActivity nextDialogState(String command) {
        return new MailingState(url,command);
    }

    @Override
    public String getContent() {
        return "Введите текст рассылки: ";
    }
}
