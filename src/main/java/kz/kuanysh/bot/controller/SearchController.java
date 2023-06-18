package kz.kuanysh.bot.controller;

import kz.kuanysh.bot.model.User;
import kz.kuanysh.bot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private final UserService userService;

    public SearchController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        System.out.println("it was worked");
        return userService.findAll();
    }
    @GetMapping("{id}")
    public List<User> getUsersById(@PathVariable("id") User user) {
        System.out.println("it was worked with id");
        return userService.findByChoiceAndCategoryWebApp(user);
    }

}
