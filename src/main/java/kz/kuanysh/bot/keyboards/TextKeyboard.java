package kz.kuanysh.bot.keyboards;

import kz.kuanysh.bot.buttons.PatternKeyboard;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

public class TextKeyboard implements Keyboard {
    @Override
    public  BotApiMethod<Message> sendMessage(Message message, String content) {
        return PatternKeyboard.sendText(message,content);
    }
}
