package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.SendModels;
import kz.kuanysh.bot.buttons.keyboards.ResultMarkup;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.objects.*;

public class ResultState implements UserActivity {
    @Override
    public UserActivity nextDialogState() {
        return new ShowResultState();
    }

    @Override
    public UserActivity backDialogState() {
        return new LocationState();
    }

    @Override
    public String getText(Message message) {
        return "Результат основан на ваших данных \n " +
                "подробнее о лицах которые вам пришли " +
                "вы можете узнать через кнопку  show \uD83D\uDCE0: ";
    }

    @Override
    public void executeMessage(Message message, String text, String command, SendBotMessageServiceImp execute) {

        execute.sendMessage(SendModels.sendMessage(message.getChatId(), text, ResultMarkup.getMarkup()));
    }
}
