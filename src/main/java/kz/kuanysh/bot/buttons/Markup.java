package kz.kuanysh.bot.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class Markup {
    public static InlineKeyboardMarkup rightSlide() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        InlineKeyboardButton next = InlineKeyboardButton.builder()
                .text(">>")
                .callbackData("/next")
                .build();
        InlineKeyboardButton getContact = InlineKeyboardButton.builder()
                .text("Получить контакт")
                .callbackData("/getContact")
                .build();
        InlineKeyboardButton toStart = InlineKeyboardButton.builder()
                .text("назад в меню")
                .callbackData("/goToMenu")
                .build();
        markup.setKeyboard(List.of(List.of(next), List.of(getContact), List.of(toStart)));
        return markup;
    }

    public static InlineKeyboardMarkup rightLeftSlide() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        InlineKeyboardButton prev = InlineKeyboardButton.builder()
                .text("<<")
                .callbackData("/prev")
                .build();
        InlineKeyboardButton next = InlineKeyboardButton.builder()
                .text(">>")
                .callbackData("/next")
                .build();
        InlineKeyboardButton getContact = InlineKeyboardButton.builder()
                .text("Получить контакт")
                .callbackData("/getContact")
                .build();
        InlineKeyboardButton toStart = InlineKeyboardButton.builder()
                .text("назад в меню")
                .callbackData("/goToMenu")
                .build();
        markup.setKeyboard(List.of(List.of(prev, next), List.of(getContact), List.of(toStart)));
        return markup;
    }

    public static InlineKeyboardMarkup leftSlide() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        InlineKeyboardButton prev = InlineKeyboardButton.builder()
                .text("<<")
                .callbackData("/prev")
                .build();
        InlineKeyboardButton getContact = InlineKeyboardButton.builder()
                .text("Получить контакт")
                .callbackData("/getContact")
                .build();
        InlineKeyboardButton toStart = InlineKeyboardButton.builder()
                .text("назад в меню")
                .callbackData("/goToMenu")
                .build();
        markup.setKeyboard(List.of(List.of(prev), List.of(getContact), List.of(toStart)));
        return markup;
    }

    public static InlineKeyboardMarkup emptySlide() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        InlineKeyboardButton toStart = InlineKeyboardButton.builder()
                .text("назад в меню")
                .callbackData("/goToMenu")
                .build();
        markup.setKeyboard(List.of(List.of(toStart)));
        return markup;
    }

    public static InlineKeyboardMarkup oneSlide() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        InlineKeyboardButton getContact = InlineKeyboardButton.builder()
                .text("Получить контакт")
                .callbackData("/getContact")
                .build();
        InlineKeyboardButton toStart = InlineKeyboardButton.builder()
                .text("назад в меню")
                .callbackData("/goToMenu")
                .build();
        markup.setKeyboard(List.of(List.of(getContact), List.of(toStart)));
        return markup;
    }
}
