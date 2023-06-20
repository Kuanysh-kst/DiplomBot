package kz.kuanysh.bot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class MainController {
    @GetMapping
    public String helloWorld() {
        return "Hello, World!";
    }
}
