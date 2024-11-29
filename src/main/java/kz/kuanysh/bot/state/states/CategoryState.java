package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.InlineListGenerator;
import kz.kuanysh.bot.commands.Commands;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

import static java.util.List.of;

public class CategoryState implements UserActivity {
    @Override
    public UserActivity nextDialogState() {
        return new AboutState();
    }

    @Override
    public UserActivity backDialogState() {
        return new ChoiceState();
    }

    @Override
    public String getText(Message message) {
        return "Выберите категорию для работы";
    }

    @Override
    public InlineKeyboardMarkup getMarkup() {
        List<String> text = of(
                Commands.CONSTRUCTION_WORK.getText(),
                Commands.WORK_LOADER.getText(),
                Commands.DELIVERY_WORK.getText(),
                Commands.WORK_CAFE.getText(),
                Commands.CLEANING_WORK.getText(),
                Commands.BACK.getText()
        );
        List<String> callback = of(
                Commands.CONSTRUCTION_WORK.getCallback(),
                Commands.WORK_LOADER.getCallback(),
                Commands.DELIVERY_WORK.getCallback(),
                Commands.WORK_CAFE.getCallback(),
                Commands.CLEANING_WORK.getCallback(),
                Commands.BACK.getCallback()
        );
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(InlineListGenerator.listButtons(text,callback));
        return markup;
    }
}
