package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.buttons.keyboards.SliderMarkup;
import kz.kuanysh.bot.buttons.SendModels;
import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.chain.interfaces.CreateAboutText;
import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.ShowResultState;
import kz.kuanysh.bot.state.states.StartState;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.io.File;
import java.util.List;

@Slf4j
public class ShowResultChain extends DialogChain {

    private final static String END_OF_LIST = "Вы дошли до финиша \uD83C\uDFC1, пользователи на данный момент закончелись \uD83D\uDE48";
    private final static String START_OF_LIST = "Листай дальше чтобы найти пользователей \uD83E\uDDD1\u200D\uD83C\uDF3E";
    private int index;
    private List<User> currentUsersList;
    CreateAboutText text = (user) -> "Пользоваетель под именем " + user.getFirstName() + ", написал о себе следуещее: \n" + user.getAbout();

    public ShowResultChain(DialogChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog state, String command, UserService userService, SendBotMessageServiceImp execute) {
        int val = this.index;

        switch (command) {
            case "назад в главное меню": {
                state.setState(new StartState());
                execute.sendBotApiMethod(SendModels.sendMessage(message, state.getText(message), state.getMarkup()));
                state.nextDialogState();
                userService.saveDialog(message, state);
                break;
            }
            case "получить контакт": {
                Contact currentContact = currentUsersList.get(index).getContact();
                if (currentContact == null) {
                    var response = SendModels.sendText(message.getChatId(), "Упс контакт не существует \uD83D\uDC7E");
                    execute.sendBotApiMethod(response);
                } else {
                    execute.sendBotApiMethod(SendModels.sendContact(message, currentContact));
                }
                break;
            }
            case "/result": {
                this.index = 0;
                userService.saveUserParameters(message, state);
                this.currentUsersList = userService.findByChoiceAndCategory(message);

                if (currentUsersList.isEmpty()) {
                    InputFile inputFile = new InputFile(new File("src/main/resources/Img/not_found_users.jpeg"));
                    var response = SendModels.sendPhoto(message, NOT_FOUND, inputFile, SliderMarkup.emptySlide());
                    execute.sendPhoto(response);
                } else {
                    var markupType = currentUsersList.size() == 1 ? SliderMarkup.oneSlide() : SliderMarkup.rightLeftSlide();
                    var aboutText = currentUsersList.size() == 1 ? text.getAboutText(currentUsersList.get(0)) : text.getAboutText(currentUsersList.get(index));
                    var photoFile = new InputFile(currentUsersList.get(index).getFile());
                    sendMedia(message, execute, aboutText, photoFile, markupType);
                }
                break;
            }
            case "➡": {
                if (++val < currentUsersList.size()) {
                    sendMedia(message, execute, ++index);
                } else {
                    execute.sendBotApiMethod(SendModels.sendText(message.getChatId(), END_OF_LIST));
                }
                break;
            }
            case "⬅": {
                if (--val >= 0) {
                    sendMedia(message, execute, --index);
                } else {
                    execute.sendBotApiMethod(SendModels.sendText(message.getChatId(), START_OF_LIST));
                }
                break;
            }
            case "/back":
                state.backDialogState();
                userService.saveDialog(message, state);
                state.backDialogState();
                execute.sendBotApiMethod(SendModels.sendMessage(message, state.getText(message), state.getMarkup()));
                break;
        }
    }

    private void sendMedia(Message message, SendBotMessageServiceImp execute, String about, InputFile photoFile, ReplyKeyboardMarkup markup) {
        var response = SendModels.sendPhoto(message, about, photoFile, markup);
        execute.sendPhoto(response);
    }

    private void sendMedia(Message message, SendBotMessageServiceImp execute, int index) {
        User user = currentUsersList.get(index);
        var inputFile = new InputFile(user.getFile());
        var response = SendModels.sendPhoto(message, text.getAboutText(user), inputFile);
        execute.sendPhoto(response);
    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof ShowResultState;
    }
}
