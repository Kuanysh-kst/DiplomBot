package kz.kuanysh.bot.state;



public interface UserActivity<T> {
    UserActivity nextDialogState(T command);
    String getContent();
}
