package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.buttons.SendModels;
import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.commands.Commands;
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
        if (command.equals(Commands.BACK.getCallback())) {
            state.backDialogState();
            userService.saveDialog(message, state);

            state.backDialogState();
            execute.sendBotApiMethod(SendModels.sendMessage(message,state.getText(message), state.getMarkup()));

        } else if (command.equals(Commands.SKIP.getCallback())) {
            execute.sendBotApiMethod(SendModels.sendMessage(message, state.getText(message), state.getMarkup()));

            state.nextDialogState();
            userService.saveDialog(message, state);

        } else if (message.hasText()) {
            execute.sendBotApiMethod(SendModels.sendMessage(message, state.getText(message), state.getMarkup()));

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
