package kz.kuanysh.bot.service;

import kz.kuanysh.bot.buttons.ButtonController;
import kz.kuanysh.bot.factory.DialogFactory;
import kz.kuanysh.bot.factory.dialogs.Dialog;
import kz.kuanysh.bot.factory.factories.dialog.SorryDialogFactory;
import kz.kuanysh.bot.factory.message.Sender;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

public class MessageHandler {
    public static DialogFactory factoryControl(Update update, UserService userService) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            userService.registerUser(update.getMessage());
            return ButtonController.createDialogFactory(update.getMessage().getText());
        } else if (update.hasCallbackQuery()) {
            return ButtonController.createDialogFactory(update.getCallbackQuery().getData());
        } else {
            return new SorryDialogFactory();
        }
    }

    public static BotApiMethod<Serializable> createSendMessage(DialogFactory dialogFactory, Update update) {
        Sender sender = dialogFactory.createSender();
        Dialog dialog = dialogFactory.createDialog();

        if (update.hasCallbackQuery()) {
            Message callBackMessage = update.getCallbackQuery().getMessage();
            return sender.sendMessage(callBackMessage, dialog.getText(callBackMessage));
        }
        Message message = update.getMessage();
        return sender.sendMessage(message, dialog.getText(message));
    }


}
//        if (message.getChatId().equals(botConfig.getOwnerId())) {
//            if (messageText.contains("/send")){
//                var textToSend = messageText.substring(messageText.indexOf(" "));
//                var users = userService.findAll();
//                for (User user: users){
//                    var response = CreateButton.sendText(user.getChatId(),textToSend);
//                    executeMessage(response);
//                }
//            }
//        }