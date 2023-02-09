package kz.kuanysh.bot.keyboards;

import kz.kuanysh.bot.buttons.InlineListButton;
import kz.kuanysh.bot.buttons.PatternKeyboard;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.Serializable;
import java.util.List;

public class ResultKeyBoard implements Keyboard{
      public List<String> listChoice() {
        return List.of("получить результат",
                "вернутся к началу");
    }

    public List<String> listChoiceCallBack() {
        return List.of("/getResult",
                "nextBack");
    }
    @Override
    public  BotApiMethod<Serializable> sendMessage(Message message, String content) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = InlineListButton.listButtons(listChoice(),listChoiceCallBack());
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return PatternKeyboard.sendEdit(message, content, inlineKeyboardMarkup);
    }
}
