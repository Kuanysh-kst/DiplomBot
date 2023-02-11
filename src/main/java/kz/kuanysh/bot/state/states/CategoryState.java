package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.InlineListButton;
import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.Serializable;
import java.util.List;

public class CategoryState implements UserActivity<String> {

    private final String choice;

    public CategoryState(String choice) {
        this.choice = choice;
    }


    List<String> workNames() {
        return List.of(
                "стройтельные работы",
                "работа грузчиком",
                "работа по доставке",
                "работа в кафе",
                "клининг работы",
                "назад");
    }

    List<String> callBackWorkNames() {
        return List.of(
                "/constructionWork",
                "/workLoader",
                "/delivery work",
                "/workCafe",
                "/cleaningWork",
                "/back"
        );
    }

    @Override
    public UserActivity nextDialogState(String par) {
        return new AboutState(choice, par);
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
    public <T extends Serializable> BotApiMethod getKeyBoard(Message message, String text, String command) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = InlineListButton.listButtons(workNames(), callBackWorkNames());
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return PatternKeyboard.sendEdit(message, text, inlineKeyboardMarkup);
    }

}
