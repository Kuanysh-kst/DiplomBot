package kz.kuanysh.bot.service;

import kz.kuanysh.bot.buttons.ButtonController;
import kz.kuanysh.bot.config.BotConfig;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.Event.Event;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.factories.dialog.SorryDialogFactory;
import kz.kuanysh.bot.factory.message.Sender;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

public class MessageHandler {
    public static DialogFactory factoryControl(String text, Long chatId, BotConfig botConfig,Message message,UserService userService) {
        DialogFactory dialogFactory =  ButtonController.createDialogFactory(text, chatId, botConfig);
        Event event = dialogFactory.serviceEvent();
        event.createEvent(message,userService, text);
        return dialogFactory;
    }

    public static BotApiMethod<Serializable> createSendMessage(DialogFactory dialogFactory, Message message) {
        Sender sender = dialogFactory.createSender();
        Dialog dialog = dialogFactory.createDialog();

        return sender.sendMessage(message, dialog.getText(message));
    }
}
