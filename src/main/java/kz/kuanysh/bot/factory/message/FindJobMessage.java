package kz.kuanysh.bot.factory.message;

import kz.kuanysh.bot.buttons.CreateButton;
import kz.kuanysh.bot.buttons.InlineListButton;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.Serializable;
import java.util.List;

public class FindJobMessage implements Sender {
    List<String> workNames() {
        return List.of(
                "стройтельные работы",
                "работа грузчиком",
                "работа по доставке",
                "работа в кафе",
                "клининг работы");
    }

    List<String> callBackWorkNames() {
        return List.of(
                "/constructionWork",
                "/workLoader",
                "/delivery work",
                "/workCafe",
                "/cleaningWork"
        );
    }

    @Override
    public BotApiMethod<Serializable> sendMessage(Message message, String content) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard;

        keyboard = InlineListButton.listButtons(workNames(), callBackWorkNames());
        keyboard.add(InlineListButton.backButton("nextBack"));

        inlineKeyboardMarkup.setKeyboard(keyboard);
        return CreateButton.sendEdit(message, content, inlineKeyboardMarkup);
    }
}
