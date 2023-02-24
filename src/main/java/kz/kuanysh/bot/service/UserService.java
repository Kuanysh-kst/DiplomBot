package kz.kuanysh.bot.service;

import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.states.StartState;
import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Dialog findDialog(Message message) {
        Long chatId = message.getChatId();
        if (getUserById(chatId).getDialog() == null) {
            return new Dialog(new StartState());
        } else {
            return getUserById(chatId).getDialog();
        }
    }

    public void saveDialog(Message message, Dialog dialog) {
        var chatId = message.getChatId();
        var user = getUserById(chatId);
        user.setDialog(dialog);
        userRepository.save(user);
    }

    public User getUserById(Long chatId) {
        Optional<User> userOptional = findById(chatId);
        return userOptional.orElseGet(User::new);
    }


    public void saveUserInBase(Update update) {
        if (update.hasMessage() && !userRepository.existsById(update.getMessage().getChatId())){
            Message message = update.getMessage();

            setParameters(message);
        }else if (update.hasCallbackQuery() && !userRepository.existsById(update.getCallbackQuery().getMessage().getChatId())){
            Message message = update.getCallbackQuery().getMessage();

            setParameters(message);
        }
    }

    public void saveUserParameters(Message message, Dialog state) {
        var chatId = message.getChatId();
        var chat = message.getChat();
        User user = getUserById(chatId);
        user.setChatId(chatId);
        user.setFirstName(chat.getFirstName());
        user.setLastName(chat.getLastName());
        user.setUserName(chat.getUserName());
        user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));
        user.setChoice(state.getChoice());
        user.setCategory(state.getCategory());
        user.setAbout(state.getAbout());
        user.setFile(state.getFile());
        user.setContact(state.getContact());
        user.setLocation(state.getLocation());
        user.setDialog(state);
        userRepository.save(user);
        log.info("user saved: " + user);
    }

    public Optional<User> findById(Long chatId) {
        return userRepository.findById(chatId);
    }

    public List<User> findByChoiceAndCategory(Message message) {
        User user = getUserById(message.getChatId());
        String choice = user.getChoice();
        String category = user.getCategory();

        if (choice.equals("/findjob")) {
            return userRepository.findByChoiceAndCategory("/findworker", category);
        } else {
            return userRepository.findByChoiceAndCategory("/findjob", category);
        }
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }

    private void setParameters(Message message){
        User user = new User();
        user.setFile(new File("src/main/resources/Img/default.jpeg"));
        user.setChatId(message.getChatId());
        user.setFirstName(message.getChat().getFirstName());
        user.setLastName(message.getChat().getLastName());
        user.setUserName(message.getChat().getUserName());
        user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
        log.info("user saved: " + user);
    }
}
