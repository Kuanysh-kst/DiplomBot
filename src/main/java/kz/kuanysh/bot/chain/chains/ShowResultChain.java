package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.ShowResultState;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;
import java.util.Optional;

public class ShowResultChain extends DialogChain {

    public ShowResultChain(DialogChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog state, String command, UserService userService, SendBotMessageServiceImp execute) {
        if (command.equals("/result")) {
            userService.saveUserParameters(message, state);
            List<User> list = userService.findByChoiceAndCategory(message);
            Optional<User> userOp = list.stream().findFirst();
            if (userOp.isPresent()){
                User user = userOp.get();
                var response = user.getContact();
                SendContact sendContact = SendContact.builder()
                        .chatId(message.getChatId().toString())
                        .phoneNumber(response.getPhoneNumber())
                        .lastName(response.getLastName())
                        .firstName(response.getFirstName())
                        .build();
                execute.sendMessage(sendContact);
            }


        } else if (command.equals("/back")) {
            state.backDialogState();
            userService.saveDialog(message, state);
            state.backDialogState();

            var response = state.getKeyBoard(message, command);

            execute.sendMessageSerializable(response);
        }
    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof ShowResultState;
    }
}
