package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.buttons.Markup;
import kz.kuanysh.bot.buttons.PatternKeyboard;
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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.io.File;
import java.util.List;

@Slf4j
public class ShowResultChain extends DialogChain {

    private int index;

    public ShowResultChain(DialogChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog state, String command, UserService userService, SendBotMessageServiceImp execute) {
        int val = this.index;

        switch (command) {
            case "/result": {
                this.index = 0;
                userService.saveUserParameters(message, state);
                List<User> userList = userService.findByChoiceAndCategory(message);
                if (userList.isEmpty()) {
                    String notFound = "notFound";
                    File file = new File("src/main/resources/Img/not_found_users.jpg");
                    InputFile inputFile = new InputFile(file);
                    var response = PatternKeyboard.sendPhoto(message, notFound, inputFile, Markup.emptySlide());
                    execute.sendPhoto(response);
                }else {
                    indexHandler(state, userList, message, execute, Markup.rightSlide(), index);
                }

                break;
            }
            case "/next": {
                val++;
                List<User> userList = userService.findByChoiceAndCategory(message);
                if (val == userList.size() - 1) {
                    this.index++;
                    indexHandler(state, userList, message, execute, Markup.leftSlide(), index);
                } else if (val < userList.size() && val >= 0) {
                    this.index++;
                    indexHandler(state, userList, message, execute, Markup.rightLeftSlide(), index);
                }

                break;
            }
            case "/prev": {
                val--;
                List<User> userList = userService.findByChoiceAndCategory(message);
                if (val == 0) {
                    this.index--;
                    indexHandler(state, userList, message, execute, Markup.rightSlide(), index);
                } else if (val < userList.size() && val >= 0) {
                    this.index--;
                    indexHandler(state, userList, message, execute, Markup.rightLeftSlide(), index);
                }

                break;
            }
            case "/back":
                state.backDialogState();
                userService.saveDialog(message, state);
                state.backDialogState();

                state.executeMessage(message, command, execute);
                break;
        }
    }

    private void indexHandler(Dialog state, List<User> userList, Message message, SendBotMessageServiceImp execute, InlineKeyboardMarkup markup, int index) {
        User user = userList.get(index);
        var response = user.getFile();
        InputFile inputFile = new InputFile(response);
        SendPhoto sendPhoto = PatternKeyboard.sendPhoto(message, state.getText(message), inputFile, markup);
        execute.sendPhoto(sendPhoto);
    }

    private void indexHandler(Message message, SendBotMessageServiceImp execute, InlineKeyboardMarkup markup) {

    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof ShowResultState;
    }
}
