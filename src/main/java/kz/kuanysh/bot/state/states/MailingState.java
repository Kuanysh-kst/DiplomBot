package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.state.UserActivity;

public class MailingState implements UserActivity<String> {

    private final String url;
    private final String text;

    public MailingState(String url, String text) {
        this.url = url;
        this.text = text;
    }

    @Override
    public UserActivity nextDialogState(String command) {
        return this;
    }

    @Override
    public String getContent() {
        return null;
    }
}
