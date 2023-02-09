package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.chain.DialogStateChain;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.StartUserActivity;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

@Slf4j
public class StartDialogChain extends DialogStateChain {

    public StartDialogChain(DialogStateChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog context, String command, UserService userService, SendBotMessageServiceImp execute) {
        if (command.equals("/start")) {
            var response = context.getKeyBoard();
            execute.sendMessageSerializable(response);

            context.nextDialogState(message.getText());
            userService.saveDialog(message, context);

        } else {
            var response = PatternKeyboard.sendText(message.getChatId(), "Нейзвестная команда");
            execute.sendMessage(response);
        }
    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof StartUserActivity;
    }
}
