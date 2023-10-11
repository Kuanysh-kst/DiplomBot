package kz.kuanysh.bot.bot.strategy;

import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateStrategy {
    void handleUpdate(Update update, UserService service, SendBotMessageServiceImp messageService, DialogChain chain);
}
