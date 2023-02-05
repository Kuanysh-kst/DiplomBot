package kz.kuanysh.bot.factory.Event;

import kz.kuanysh.bot.service.UserService;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface Event {
    void createEvent(Message message, UserService userService);
}
