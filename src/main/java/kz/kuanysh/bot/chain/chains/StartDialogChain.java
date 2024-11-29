package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.buttons.SendModels;
import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.commands.Commands;
import kz.kuanysh.bot.parsing.HMSTRPriceParser;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.StartState;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
public class StartDialogChain extends DialogChain {

    public StartDialogChain(DialogChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog state, String command, UserService userService, SendBotMessageServiceImp execute) {
        if (command.equals(Commands.START.getText())) {
            execute.sendBotApiMethod(SendModels.sendMessage(message, state.getText(message), state.getMarkup()));

            state.nextDialogState();
            userService.saveDialog(message, state);

        } else if( command.equals(Commands.HELP.getCallback())) {
            execute.sendBotApiMethod(SendModels.sendText(message.getChatId(),"\uD83E\uDD14"));
            execute.sendBotApiMethod(SendModels.sendText(message.getChatId(),Commands.HELP_TEXT.getText()));
        } else if (command.equals(Commands.HMSTR.getCallback())){
            execute.sendBotApiMethod(SendModels.sendText(message.getChatId(), HMSTRPriceParser.getHMSTRPrice()));
        } else {
            var response = SendModels.sendText(message.getChatId(), UNKNOWN_MESSAGE);
            execute.sendBotApiMethod(response);
        }
    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof StartState;
    }
}
