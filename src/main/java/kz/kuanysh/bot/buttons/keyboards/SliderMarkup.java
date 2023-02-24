package kz.kuanysh.bot.buttons.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import java.util.List;

import static kz.kuanysh.bot.buttons.ReplyMarkupGenerator.createKeyboard;

public class SliderMarkup {


    public static ReplyKeyboardMarkup rightLeftSlide() {
        List<List<String>> list = List.of(List.of("<<", ">>"), List.of("получить контакт"), List.of("вернуться к выбору"));
        return createKeyboard(list,false);
    }
    public static ReplyKeyboardMarkup oneSlide() {
        List<List<String>> list = List.of(List.of("получить контакт"), List.of("вернуться к выбору"));
        return createKeyboard(list,false);
    }

    public static ReplyKeyboardMarkup emptySlide() {
        List<List<String>> list = List.of(List.of("вернуться к выбору"));
        return createKeyboard(list,true);
    }

}
