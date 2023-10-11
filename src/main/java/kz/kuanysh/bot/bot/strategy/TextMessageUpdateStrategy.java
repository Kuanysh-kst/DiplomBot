package kz.kuanysh.bot.bot.strategy;

import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TextMessageUpdateStrategy implements UpdateStrategy{

    @Override
    public void handleUpdate(Update update, UserService service, SendBotMessageServiceImp messageService, DialogChain chain) {
            Message message = update.getMessage();
            String command = message.getText();
            Dialog dialog = service.findDialog(message);
            chain.processState(message, dialog, command, service, messageService);
    }
}
