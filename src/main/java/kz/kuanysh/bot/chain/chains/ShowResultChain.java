package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.buttons.Markup;
import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.ChoiceState;
import kz.kuanysh.bot.state.states.ShowResultState;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.io.File;
import java.util.List;

@Slf4j
public class ShowResultChain extends DialogChain {

    private int index;
    private List<User> currentUsersList;


    public ShowResultChain(DialogChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog state, String command, UserService userService, SendBotMessageServiceImp execute) {
        int val = this.index;

        switch (command) {
            case "/goToMenu": {
                state.setState(new ChoiceState());
                state.executeMessage(message, command, execute);

                state.nextDialogState();
                userService.saveDialog(message, state);
                break;
            }
            case "/getContact": {
                List<User> userList = userService.findByChoiceAndCategory(message);
                Contact currentContact = userList.get(index).getContact();
                if (currentContact == null) {
                    var response = PatternKeyboard.sendText(message.getChatId(), "Упс контакт не существует \uD83D\uDC7E");
                    execute.sendMessage(response);
                } else {
                    var response = SendContact.builder()
                            .chatId(message.getChatId().toString())
                            .phoneNumber(currentContact.getPhoneNumber())
                            .firstName(currentContact.getFirstName())
                            .lastName(currentContact.getLastName())
                            .build();
                    execute.sendMessage(response);
                }

                break;
            }
            case "/result": {
                this.index = 0;
                userService.saveUserParameters(message, state);
                this.currentUsersList = userService.findByChoiceAndCategory(message);
                if (currentUsersList.isEmpty()) {
                    String notFound = "Упс , по вашему запросу нет результата \uD83E\uDEE4, вы можете ожидать отклика или удалить настройки своего профиля";
                    File file = new File("src/main/resources/Img/not_found_users.jpeg");
                    InputFile inputFile = new InputFile(file);
                    var response = PatternKeyboard.sendPhoto(message, notFound, inputFile, Markup.emptySlide());
                    execute.sendPhoto(response);
                } else if (currentUsersList.size() == 1) {
                    sendMedia(state, currentUsersList, message, execute, Markup.oneSlide(), index);
                } else {
                    sendMedia(state, currentUsersList, message, execute, Markup.rightSlide(), index);
                }

                break;
            }
            case "/next": {
                val++;
                if (val == currentUsersList.size() - 1) {
                    this.index++;

                    sendMedia(state, currentUsersList, message, execute, Markup.leftSlide(), index);
                } else if (val < currentUsersList.size() && val >= 0) {
                    this.index++;

                    sendMedia(state, currentUsersList, message, execute, Markup.rightLeftSlide(), index);
                }

                break;
            }
            case "/prev": {
                val--;
                if (val == 0) {
                    this.index--;

                    sendMedia(state, currentUsersList, message, execute, Markup.rightSlide(), index);
                } else if (val < currentUsersList.size() && val >= 0) {
                    this.index--;

                    sendMedia(state, currentUsersList, message, execute, Markup.rightLeftSlide(), index);
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

    private void sendMedia(Dialog state, List<User> userList, Message message, SendBotMessageServiceImp execute, InlineKeyboardMarkup markup, int index) {
        User user = userList.get(index);
        var response = user.getFile();
        InputFile inputFile = new InputFile(response);
        SendPhoto sendPhoto = PatternKeyboard.sendPhoto(message, state.getText(message), inputFile, markup);
        execute.sendPhoto(sendPhoto);
    }


    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof ShowResultState;
    }
}
