package kz.kuanysh.bot.chain.chains;

import kz.kuanysh.bot.buttons.PatternKeyboard;
import kz.kuanysh.bot.chain.DialogChain;
import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import kz.kuanysh.bot.state.UserActivity;
import kz.kuanysh.bot.state.states.ContactState;
import kz.kuanysh.bot.state.states.PhotoState;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

import java.util.Comparator;

public class ContactChain extends DialogChain {

    public ContactChain(DialogChain nextChain) {
        super(nextChain);
    }

    @Override
    protected void doProcess(Message message, Dialog state, String command, UserService userService, SendBotMessageServiceImp execute) {
        if (message.hasPhoto()) {
            var response = state.getKeyBoard(message, command);
            execute.sendMessageSerializable(response);
//            PhotoSize photoSize = message.getPhoto().stream()
//                    .max(Comparator.comparing(PhotoSize::getFileSize))
//                    .orElse(null);
//            String field = photoSize.getFileId();
//            File file = new GetFile().setFileId(field);
//            state.setFile(message.getPhoto());

            state.nextDialogState();
            userService.saveDialog(message, state);
        } else if (command.equals("/skip")) {
            var response = state.getKeyBoard(message, command);
            execute.sendMessageSerializable(response);

            state.nextDialogState();
            userService.saveDialog(message, state);
        } else if (command.equals("/back")) {
            state.backDialogState();
            userService.saveDialog(message, state);

            state.backDialogState();

            var response = state.getKeyBoard(message, command);

            execute.sendMessageSerializable(response);

        } else {
            var response = PatternKeyboard.sendText(message.getChatId(), "Я ещё не знаю как ответить на эту команду \uD83D\uDC7E");
            execute.sendMessage(response);
        }

    }

    @Override
    protected boolean shouldProcessState(UserActivity userActivity) {
        return userActivity instanceof ContactState;
    }
}
