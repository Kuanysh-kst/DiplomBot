package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.commands.Commands;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class PhotoState implements UserActivity {


    @Override
    public UserActivity nextDialogState() {
        return new ContactState();
    }


    @Override
    public UserActivity backDialogState() {
        return new AboutState();
    }

    @Override
    public String getText(Message message) {
        return "Поделитесь фото для просмотра профиля \uD83C\uDFC4\u200D♂️";
    }

    @Override
    public InlineKeyboardMarkup getMarkup() {
        InlineKeyboardButton skip = InlineKeyboardButton.builder()
                .text(Commands.SKIP.getText())
                .callbackData(Commands.SKIP.getCallback())
                .build();
        InlineKeyboardButton back = InlineKeyboardButton.builder()
                .text(Commands.BACK.getText())
                .callbackData(Commands.BACK.getCallback())
                .build();
        List<List<InlineKeyboardButton>> keyboard = List.of(List.of(skip), List.of(back));
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(keyboard);
        return markup;
    }

}
