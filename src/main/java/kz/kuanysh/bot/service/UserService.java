package kz.kuanysh.bot.service;

import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Location;
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

    private User getUserById(Long chatId) {
        Optional<User> userOptional = findById(chatId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            User user = new User();
            user.setChatId(chatId);
            userRepository.save(user);
            log.info("Saved user with only id:" + chatId);
            return user;
        }
    }

    public void saveUserStatus(Message message, String text) {
        var chatId = message.getChatId();
        var user = getUserById(chatId);
        user.setStatus(text);
        userRepository.save(user);
        log.info("User with Id:" + message.getChatId() + " status saved: " + user.getStatus());
    }

    public void saveUserCategory(Message message, String text) {
        var chatId = message.getChatId();
        var user = getUserById(chatId);
        user.setCategory(text);
        userRepository.save(user);
        log.info("User with Id:" + message.getChatId() + " category saved: " + user.getCategory());
    }

    public void saveUserLocation(Message message, Location location) {
        var chatId = message.getChatId();
        var user = getUserById(chatId);
        user.setLocation(location);
        userRepository.save(user);
        log.info("User with Id:" + message.getChatId() + " location saved: " + user.getCategory());
    }

    public Location getFromUserLocation(Long chatId) {
        var user = getUserById(chatId);
        return user.getLocation();
    }
}
