package kz.kuanysh.bot.buttons;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public class SendModels {
    public static SendPhoto sendPhoto(Message message, String text, InputFile inputFile, ReplyKeyboardMarkup markup) {
        return SendPhoto.builder()
                .replyMarkup(markup)
                .photo(inputFile)
                .chatId(message.getChatId().toString())
                .caption(text)
                .build();
    }

    public static SendPhoto sendPhoto(Message message, String text, InputFile inputFile) {
        return SendPhoto.builder()
                .photo(inputFile)
                .chatId(message.getChatId().toString())
                .caption(text)
                .build();
    }
    public static SendContact sendContact(Message message, Contact contact){
        return SendContact.builder()
                .chatId(message.getChatId().toString())
                .phoneNumber(contact.getPhoneNumber())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .build();
    }

    public static BotApiMethod<Message> sendText(Long chatId, String text) {
        return SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .build();
    }

    public static BotApiMethod<Message> sendMessage(Long chatId, String text, InlineKeyboardMarkup inlineKeyboardMarkup) {
        return SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .replyMarkup(inlineKeyboardMarkup)
                .build();
    }
    public static BotApiMethod<Message> sendMessage(Long chatId, String text, ReplyKeyboardMarkup markup) {
        return SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .replyMarkup(markup)
                .build();
    }

    public static EditMessageText sendEdit(Message message, String text, InlineKeyboardMarkup inlineKeyboardMarkup) {
        return EditMessageText.builder()
                .chatId(message.getChatId().toString())
                .messageId(message.getMessageId())
                .text(text)
                .replyMarkup(inlineKeyboardMarkup)
                .build();
    }
}
