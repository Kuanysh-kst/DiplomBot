package kz.kuanysh.bot.service;

import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void saveUserInBase(Message message) {
        if (message.getText().equals("/start") && userRepository.findById(message.getChatId()).isEmpty()) {
            var chatId = message.getChatId();
            var chat = message.getChat();

            User user = new User();
            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setUserName(chat.getUserName());
            user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));

            userRepository.save(user);
            log.info("user saved: " + user);
        }
    }

    public Optional<User> findById(Long chatId) {
        return userRepository.findById(chatId);
    }

    public void saveUserStatus(Message message,String text) {
        var chatId = message.getChatId();
        Optional<User> userOptional = findById(chatId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatus(text);
            userRepository.save(user);
            log.info("User status saved: " + user.getStatus());
        }
    }
}
