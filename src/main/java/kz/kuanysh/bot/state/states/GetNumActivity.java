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

public class GetNumActivity implements UserActivity {

    private final String status;
    private final String category;


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
    public GetNumActivity(String status, String category) {
        this.status = status;
        this.category = category;
    }

    @Override
    public UserActivity nextDialogState(Object command) {
        return null;
    }

    @Override
    public UserActivity backDialogState() {
        return new CategoryActivity(status);
    }

    @Override
    public String getText(Message message) {
        return "Выберите категорию ";
    }

    @Override
    public BotApiMethod<Serializable> getKeyBoard(Message message,String text,String command) {
                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard;

        keyboard = InlineListButton.listButtons(workNames(), callBackWorkNames());
        keyboard.add(InlineListButton.backButton("/back"));

        inlineKeyboardMarkup.setKeyboard(keyboard);
        return PatternKeyboard.sendEdit(message, text, inlineKeyboardMarkup);
    }
}
