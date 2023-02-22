package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.InlineListButton;
import kz.kuanysh.bot.buttons.SendModels;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class CategoryState implements UserActivity {

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
    public void executeMessage(Message message, String text, String command, SendBotMessageServiceImp execute) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = InlineListButton.listButtons(workNames(), callBackWorkNames());
        inlineKeyboardMarkup.setKeyboard(keyboard);

        execute.sendMessageSerializable(SendModels.sendEdit(message, text, inlineKeyboardMarkup));
    }
}
