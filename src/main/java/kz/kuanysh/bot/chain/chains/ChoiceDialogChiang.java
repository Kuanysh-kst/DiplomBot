package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.chain.DialogStateChain;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.ChoiceUserActivity;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

@Slf4j
public class ChoiceDialogChiang extends DialogStateChain {

    public ChoiceDialogChiang(DialogStateChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog context,String command, UserService userService, SendBotMessageServiceImp executeService) {
        if (command.equals("next")) {
            var response = context.getKeyBoard();
            executeService.sendMessageSerializable(response);

            context.nextDialogState(message.getText());
            userService.saveDialog(message, context);

        } else {
            var response = PatternKeyboard.sendText(message.getChatId(), "Нейзвестная команда");
            executeService.sendMessage(response);
        }
    }


    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof ChoiceUserActivity;
    }


}
