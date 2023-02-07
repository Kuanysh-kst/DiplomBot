package kz.kuanysh.bot.service;

import kz.kuanysh.bot.buttons.CommandController;
import kz.kuanysh.bot.config.BotConfig;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.keyboards.Keyboard;
import org.telegram.telegrambots.meta.api.objects.Message;

public class MessageHandler {
    public static DialogFactory factoryControl(String text, Long chatId, BotConfig botConfig, Message message, UserService userService) {
        DialogFactory dialogFactory = CommandController.createDialogFactory(text, chatId, botConfig);
        dialogFactory.doEvent(userService,message,text);
        return dialogFactory;
    }

    public static void executeResponse(DialogFactory dialogFactory, Message message, SendBotMessageServiceImp sendResponse) {
        Keyboard sender = dialogFactory.createKeyBoard();
        Dialog dialog = dialogFactory.createDialog();
        var response = sender.sendMessage(message, dialog.getText(message));
        dialogFactory.execute(response,sendResponse);
    }
}
