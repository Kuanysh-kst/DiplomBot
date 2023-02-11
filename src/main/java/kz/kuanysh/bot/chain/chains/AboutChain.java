package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.buttons.Command;
import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.CategoryState;
import kz.kuanysh.bot.state.states.AboutState;
import org.telegram.telegrambots.meta.api.objects.Message;

public class AboutChain extends DialogChain {


    public AboutChain(DialogChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog state, String command, UserService userService, SendBotMessageServiceImp execute) {

        if (Command.callBackWorkNames(command)) {
            var response = state.getKeyBoard(message,command);
            execute.sendMessageSerializable(response);

            state.nextDialogState();
            userService.saveDialog(message, state);

        } else if (command.equals("/back")) {
            state.backDialogState();
            Dialog backState = new Dialog(state.getState());
            state.backDialogState();

            var response = state.getKeyBoard(message,command);

            execute.sendMessageSerializable(response);

            userService.saveDialog(message, backState);
        } else {
            var response = PatternKeyboard.sendText(message.getChatId(), "Я ещё не знаю как ответить на эту команду \uD83D\uDC7E");
            execute.sendMessage(response);
        }
    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof AboutState;
    }
}
