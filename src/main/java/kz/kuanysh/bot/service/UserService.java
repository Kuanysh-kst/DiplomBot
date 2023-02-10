package kz.kuanysh.bot.service;

import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.states.StartState;
import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;

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
        }
        {
            return getUserById(chatId).getDialog();
        }
    }

    public void saveDialog(Message message, Dialog dialog) {
        var chatId = message.getChatId();
        var user = getUserById(chatId);
        user.setDialog(dialog);
        userRepository.save(user);
//        log.info("User with Id: {}  dialogState saved:{} ",
//                message.getChatId(),
//                user.getDialog().getState().getClass().getSimpleName());
    }

    public User getUserById(Long chatId) {
        Optional<User> userOptional = findById(chatId);
        return userOptional.orElseGet(User::new);
    }


    public void saveUserInBase(Message message) {
        if (userRepository.findById(message.getChatId()).isEmpty()) {
            var chatId = message.getChatId();
            var chat = message.getChat();
            Contact contact = message.getContact();

            User user = new User();
            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setUserName(chat.getUserName());
            user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));
            user.setContact(contact);
            userRepository.save(user);
            log.info("user saved: " + user);
        }
    }

    public Optional<User> findById(Long chatId) {
        return userRepository.findById(chatId);
    }

    public void saveUserStatus(Message message, String text) {
        var chatId = message.getChatId();
        var user = getUserById(chatId);
        user.setStatus(text);
        userRepository.save(user);
        log.info("User with Id:" + message.getChatId() + " status saved: " + user.getStatus());
    }

    public void saveUserContact(Message message) {
        var chatId = message.getChatId();
        var user = getUserById(chatId);
        user.setContact(message.getContact());
        userRepository.save(user);
        log.info("User with Id:" + message.getChatId() + " contact saved: " + user.getContact());
    }

    public void saveUserCategory(Message message, String text) {
        var chatId = message.getChatId();
        var user = getUserById(chatId);
        user.setCategory(text);
        userRepository.save(user);
        log.info("User with Id:" + message.getChatId() + " category saved: " + user.getCategory());
    }


    public List<User> findByStatusAndCategory(String status, String category) {
        return userRepository.findByStatusAndCategory(status, category);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
