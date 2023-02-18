package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.ShowResultState;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;
import java.util.Optional;

@Slf4j
public class ShowResultChain extends DialogChain {

    private int index;

    public ShowResultChain(DialogChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog state, String command, UserService userService, SendBotMessageServiceImp execute) {
        int val = this.index;

        if (command.equals("/result")) {
            this.index = 0;
            userService.saveUserParameters(message, state);
            List<User> list = userService.findByChoiceAndCategory(message);
            Optional<User> userOp = list.stream().findFirst();

            if (userOp.isPresent()) {
                User user = userOp.get();

                var response = user.getFile();
                InputFile inputFile = new InputFile(response);
                SendPhoto sendPhoto = SendPhoto.builder()
                        .chatId(message.getChatId().toString())
                        .photo(inputFile)
                        .caption(user.getFirstName())
                        .build();
                execute.sendPhoto(sendPhoto);
            }

        } else if (command.equals("/next") ) {
            val++;
            List<User> userList = userService.findByChoiceAndCategory(message);
            if (val < userList.size() && val >= 0) {
                this.index++;
                state.setUserIndex(+1);

                User user = userList.get(index);
                var response = user.getFile();
                InputFile inputFile = new InputFile(response);
                SendPhoto sendPhoto = SendPhoto.builder()
                        .chatId(message.getChatId().toString())
                        .photo(inputFile)
                        .caption(user.getFirstName())
                        .build();
                execute.sendPhoto(sendPhoto);
            }

        } else if (command.equals("/prev")) {
            List<User> userList = userService.findByChoiceAndCategory(message);
            val--;
            if (val < userList.size() && val >= 0) {
                --this.index;
                User user = userList.get(index);
                var response = user.getFile();
                InputFile inputFile = new InputFile(response);
                SendPhoto sendPhoto = SendPhoto.builder()
                        .chatId(message.getChatId().toString())
                        .photo(inputFile)
                        .caption(user.getFirstName())
                        .build();
                execute.sendPhoto(sendPhoto);
            }
        } else if (command.equals("/back")) {
            state.backDialogState();
            userService.saveDialog(message, state);
            state.backDialogState();

            state.executeMessage(message, command, execute);
        }
    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof ShowResultState;
    }
}
