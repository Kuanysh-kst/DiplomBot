package kz.kuanysh.bot.state;

public class StateDialog {
    private UserActivity state;

    public StateDialog(UserActivity state) {
        this.state = state;
    }

    public void nextDialogState() {
        state.nextDialogState(this);
    }

    public String getContent() {
        return state.getContent();
    }
}
