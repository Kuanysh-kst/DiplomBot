package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.InlineListGenerator;
import kz.kuanysh.bot.commands.Commands;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import java.util.List;

public class AboutState implements UserActivity {
    @Override
    public UserActivity nextDialogState() {
        return new PhotoState();
    }

    @Override
    public UserActivity backDialogState() {
        return new CategoryState();
    }

    @Override
    public String getText(Message message) {
        return "Напишите о себе \uD83E\uDD2A, это важно для ознакомления";
    }

    @Override
    public ReplyKeyboard getMarkup() {
        List<String> text = List.of(
                Commands.SKIP.getText(),
                Commands.BACK.getText()
        );
        List<String> callback = List.of(
                Commands.SKIP.getCallback(),
                Commands.BACK.getCallback()
        );
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(InlineListGenerator.listButtons(text,callback));
        return markup;
    }
}
