package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.PhotoState;
import org.telegram.telegrambots.meta.api.objects.Message;

public class PhotoChain extends DialogChain {

    public PhotoChain(DialogChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog state, String command, UserService userService, SendBotMessageServiceImp execute) {
        if (command.equals("/back")) {
            state.backDialogState();
            userService.saveDialog(message, state);

            state.backDialogState();
            state.sendKeyBoard(message, command, execute);


        } else if (command.equals("/skip")) {
            state.sendKeyBoard(message, command, execute);

            state.nextDialogState();
            userService.saveDialog(message, state);

        } else if (message.hasText()) {
            state.sendKeyBoard(message, command, execute);


            state.setAbout(command);

            state.nextDialogState();
            userService.saveDialog(message, state);
        }
    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof PhotoState;
    }
}
