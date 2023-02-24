package kz.kuanysh.bot.state.states;

import kz.kuanysh.bot.buttons.SendModels;
import kz.kuanysh.bot.buttons.keyboards.LocationMarkup;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.state.UserActivity;
import org.telegram.telegrambots.meta.api.objects.Message;

public class LocationState implements UserActivity {


    @Override
    public UserActivity nextDialogState() {
        return new ResultState();
    }

    @Override
    public UserActivity backDialogState() {
        return new ContactState();
    }

    @Override
    public String getText(Message message) {
        return "Вы можето поделится свойм местоположением для других соискателей \uD83D\uDDFA";
    }

    @Override
    public void executeMessage(Message message, String text, String command, SendBotMessageServiceImp execute) {
        execute.sendMessage(SendModels.sendMessage(message.getChatId(),text, LocationMarkup.ResultMarkup()));
    }
}
