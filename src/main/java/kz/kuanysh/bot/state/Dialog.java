package kz.kuanysh.bot.state;

import kz.kuanysh.bot.state.states.ShowResultState;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.persistence.Id;
import java.io.Serializable;

@Slf4j
@Data
public class Dialog<T> implements Serializable {

    private ShowResultState showResultState;

    @Id
    private UserActivity state;


    public Dialog(UserActivity state) {
        this.state = state;
    }

    public void nextDialogState(T par) {
        state = state.nextDialogState(par);
        log.info("Current state:{}",state.getClass().getSimpleName());
    }

    public BotApiMethod getKeyBoard(Message message,String command) {
        return state.getKeyBoard(message, state.getText(message),command);
    }

    public void backDialogState() {
        state = state.backDialogState();
    }

    public UserActivity getState() {
        return state;
    }

    public void setResultDialog(ShowResultState showResultState){
        this.showResultState = showResultState;
    }

    public ShowResultState getResultDialog(){
        return this.showResultState;
    }
}
