package kz.kuanysh.bot.factory.Event;

import kz.kuanysh.bot.service.UserService;
import org.telegram.telegrambots.meta.api.objects.Message;

public class CategoryEvent implements Event{
    @Override
    public void createEvent(Message message, UserService userService, String text) {
        userService.saveUserCategory(message,text);
    }
}
