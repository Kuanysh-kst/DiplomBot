package kz.kuanysh.bot.service;

import kz.kuanysh.bot.buttons.CommandController;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.keyboards.Keyboard;
import org.telegram.telegrambots.meta.api.objects.Message;

public class MessageHandler {
    public static DialogFactory factoryControl(String text, Long chatId, Message message, UserService userService) {
        DialogFactory dialogFactory = CommandController.createDialogFactory(text, chatId);
        dialogFactory.doEvent(userService,message,text);
        return dialogFactory;
    }

    public static void executeResponse(DialogFactory dialogFactory, Message message, SendBotMessageServiceImp sendResponse) {
        Keyboard keyBoard = dialogFactory.createKeyBoard();
        Dialog dialog = dialogFactory.createDialog();
        var response = keyBoard.sendMessage(message, dialog.getText(message));
        dialogFactory.execute(response,sendResponse);
    }
}
