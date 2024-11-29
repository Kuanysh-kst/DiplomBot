package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.buttons.Command;
import kz.kuanysh.bot.buttons.SendModels;
import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.commands.Commands;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.AboutState;
import org.telegram.telegrambots.meta.api.objects.Message;

public class AboutChain extends DialogChain {
    public AboutChain(DialogChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog state, String command, UserService userService, SendBotMessageServiceImp execute) {

        if (Command.callBackWorkNames(command)) {
            execute.sendBotApiMethod(SendModels.sendMessage(message, state.getText(message), state.getMarkup()));

            state.nextDialogState();

            state.setCategory(command);
            userService.saveDialog(message, state);
        } else if (command.equals(Commands.BACK.getCallback())) {
            state.backDialogState();
            userService.saveDialog(message, state);

            state.backDialogState();
            execute.sendBotApiMethod(SendModels.sendMessage(message,state.getText(message), state.getMarkup()));

        } else {
            var response = SendModels.sendText(message.getChatId(), UNKNOWN_MESSAGE);
            execute.sendBotApiMethod(response);
        }
    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof AboutState;
    }
}
