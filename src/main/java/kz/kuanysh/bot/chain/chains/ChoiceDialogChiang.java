package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.ChoiceState;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
public class ChoiceDialogChiang extends DialogChain {

    public ChoiceDialogChiang(DialogChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog context,String command, UserService userService, SendBotMessageServiceImp executeService) {
        if (command.equals("next")) {
            var response = context.getKeyBoard(message,command);
            executeService.sendMessageSerializable(response);

            context.nextDialogState(command);
            userService.saveDialog(message, context);

        } else {
            var response = PatternKeyboard.sendText(message.getChatId(), "Я ещё не знаю как ответить на эту команду \uD83D\uDC7E");
            executeService.sendMessage(response);
        }
    }


    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof ChoiceState;
    }


}
