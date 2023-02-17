package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.photo.PhotoHandler;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.ContactState;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ContactChain extends DialogChain {

    public ContactChain(DialogChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog state, String command, UserService userService, SendBotMessageServiceImp execute) {
        if (message.hasPhoto()) {
            state.executeMessage(message, command, execute);

            PhotoHandler.savePhoto(execute,message,state);
            state.nextDialogState();
            userService.saveDialog(message, state);
        } else if (command.equals("/skip")) {
            state.executeMessage(message, command, execute);

            state.nextDialogState();
            userService.saveDialog(message, state);
        } else if (command.equals("/back")) {
            state.backDialogState();
            userService.saveDialog(message, state);

            state.backDialogState();
            state.executeMessage(message, command, execute);

        } else {
            var response = PatternKeyboard.sendText(message.getChatId(), "Я ещё не знаю как ответить на эту команду \uD83D\uDC7E");
            execute.sendMessage(response);
        }

    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof ContactState;
    }
}
