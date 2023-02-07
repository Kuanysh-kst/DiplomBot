package kz.kuanysh.bot.service;

import kz.kuanysh.bot.buttons.CommandController;
import kz.kuanysh.bot.config.BotConfig;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.Event.Event;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.keyboards.SenderKeyboard;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

public class MessageHandler {
    private static void executeMessage(SendBotMessageServiceImp sendResponse, BotApiMethod<Serializable> response) {
        sendResponse.sendMessageSerializable(response);
    }

    public static DialogFactory factoryControl(String text, Long chatId, BotConfig botConfig, Message message, UserService userService) {
        DialogFactory dialogFactory = CommandController.createDialogFactory(text, chatId, botConfig);
        Event event = dialogFactory.serviceEvent();
        event.createEvent(message, userService, text);
        return dialogFactory;
    }

    public static void executeResponse(DialogFactory dialogFactory, Message message, SendBotMessageServiceImp sendResponse) {
        SenderKeyboard sender = dialogFactory.createSender();
        Dialog dialog = dialogFactory.createDialog();
        var response = sender.sendMessage(message, dialog.getText(message));
        executeMessage(sendResponse, response);
    }
}
