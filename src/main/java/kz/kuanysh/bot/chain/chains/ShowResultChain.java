package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.buttons.SliderMarkup;
import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.ShowResultState;
import kz.kuanysh.bot.state.states.StartState;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

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
            case "вернуться к выбору": {
                state.setState(new StartState());
                state.executeMessage(message, command, execute);

                state.nextDialogState();
                userService.saveDialog(message, state);
                break;
            }
            case "получить контакт": {
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
                    InputFile inputFile = new InputFile(new File("src/main/resources/Img/not_found_users.jpeg"));
                    var response = PatternKeyboard.sendPhoto(message, notFound, inputFile, SliderMarkup.emptySlide());

                    execute.sendPhoto(response);
                } else if (currentUsersList.size() == 1) {
                    var oneFoundFile = new InputFile(currentUsersList.get(index).getFile());
                    var response = PatternKeyboard.sendPhoto(message, state.getText(message), oneFoundFile, SliderMarkup.oneSlide());

                    execute.sendPhoto(response);
                } else {
                    var foundFile = new InputFile(currentUsersList.get(index).getFile());
                    var response = PatternKeyboard.sendPhoto(message, state.getText(message), foundFile, SliderMarkup.rightLeftSlide());
                    execute.sendPhoto(response);
                }
                break;
            }
            case ">>": {
                val++;
                if (val < currentUsersList.size()) {
                    this.index++;

                    sendMedia(state, currentUsersList, message, execute, index);
                } else {
                    execute.sendMessage(PatternKeyboard.sendText(message.getChatId(), "Это конец списка"));
                }
                break;
            }
            case "<<": {
                val--;
                if (val >= 0) {
                    this.index--;

                    sendMedia(state, currentUsersList, message, execute, index);
                } else {
                    execute.sendMessage(PatternKeyboard.sendText(message.getChatId(), "Это начало списка"));
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

    private void sendMedia(Dialog state, List<User> userList, Message message, SendBotMessageServiceImp execute, int index) {
        User user = userList.get(index);
        InputFile inputFile = new InputFile(user.getFile());
        SendPhoto sendPhoto = PatternKeyboard.sendPhoto(message, state.getText(message), inputFile);
        execute.sendPhoto(sendPhoto);
    }


    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof ShowResultState;
    }
}
