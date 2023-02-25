package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.buttons.SendModels;
import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.commands.Commands;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.LocationState;
import org.telegram.telegrambots.meta.api.objects.Message;

public class LocationChain extends DialogChain {

    public LocationChain(DialogChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog state, String command, UserService userService, SendBotMessageServiceImp execute) {
        if (message.hasContact() ) {
            execute.sendBotApiMethod(SendModels.sendMessage(message, state.getText(message), state.getMarkup()));

            state.setContact(message.getContact());

            state.nextDialogState();
            userService.saveDialog(message, state);
        }else if (command.equals(Commands.SKIP.getText())){
            execute.sendBotApiMethod(SendModels.sendMessage(message, state.getText(message), state.getMarkup()));

            state.nextDialogState();
            userService.saveDialog(message, state);
        }else if (command.equals(Commands.BACK.getText())) {
            state.backDialogState();
            userService.saveDialog(message, state);

            state.backDialogState();
            execute.sendBotApiMethod(SendModels.sendMessage(message, state.getText(message), state.getMarkup()));

        } else {
            var response = SendModels.sendText(message.getChatId(), "Я ещё не знаю как ответить на эту команду \uD83D\uDC7E");
            execute.sendBotApiMethod(response);
        }

    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof LocationState;
    }
}
