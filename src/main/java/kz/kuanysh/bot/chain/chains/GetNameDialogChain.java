package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.buttons.Command;
import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.chain.DialogStateChain;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.CategoryActivity;
import kz.kuanysh.bot.state.states.GetNumActivity;
import org.telegram.telegrambots.meta.api.objects.Message;

public class GetNameDialogChain extends DialogStateChain {


    public GetNameDialogChain(DialogStateChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog state, String command, UserService userService, SendBotMessageServiceImp execute) {

        if (Command.callBackWorkNames(command)) {
            var response = state.getKeyBoard(message,command);
            execute.sendMessageSerializable(response);

            state.nextDialogState(message.getText());
            userService.saveDialog(message, state);

        } else if (command.equals("/back")) {
            state.backDialogState();
            Dialog backState = new Dialog(new CategoryActivity(command));
            state.backDialogState();

            var response = state.getKeyBoard(message,command);

            execute.sendMessageSerializable(response);

            userService.saveDialog(message, backState);
        } else {
            var response = PatternKeyboard.sendText(message.getChatId(), "Нейзвестная команда");
            execute.sendMessage(response);
        }
    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof GetNumActivity;
    }
}
