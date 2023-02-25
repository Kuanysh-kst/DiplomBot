package kz.kuanysh.bot.chain;

import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
public abstract class DialogChain {

    private final DialogChain nextChain;

    protected static final String UNKNOWN_MESSAGE = "Я ещё не знаю как ответить на эту команду \uD83D\uDC7E";
    protected static final String NOT_FOUND = "Упс , по вашему запросу нет результата \uD83E\uDEE4," +
            " вы можете ожидать отклика или удалить настройки своего профиля";

    protected DialogChain(DialogChain nextChain) {
        this.nextChain = nextChain;
    }

    public void processState(Message message, Dialog state,String command, UserService userService, SendBotMessageServiceImp execute) {
        if (shouldProcessState(state.getState())) {
            log.info("shouldProcessState in factory {} in {}", state.getClass().getSimpleName(), state.getState().getClass().getSimpleName());
             doProcess(message, state,command, userService,execute);
        } else if (nextChain != null) {
           nextChain.processState(message, state,command, userService, execute);
        } else {
            throw new IllegalStateException(state.getState().getClass().getSimpleName());
        }
    }

    protected abstract void doProcess(Message message, Dialog context,String command, UserService userService,SendBotMessageServiceImp executeService);

    protected abstract boolean shouldProcessState(UserActivity userActivity);
}
