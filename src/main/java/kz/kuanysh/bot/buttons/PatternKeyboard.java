package kz.kuanysh.bot.buttons;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public class PatternKeyboard {
    public static SendPhoto sendPhoto(Message message, String text, InputFile inputFile, InlineKeyboardMarkup markup) {
        return SendPhoto.builder()
                .replyMarkup(markup)
                .photo(inputFile)
                .chatId(message.getChatId().toString())
                .caption(text)
                .build();
    }


    public static SendPhoto sendEditPhoto(Message message, String text, InputFile inputFile, InlineKeyboardMarkup markup) {
        return SendPhoto.builder()
                .replyMarkup(markup)
                .photo(inputFile)
                .chatId(message.getChatId().toString())
                .caption(text)
                .build();
    }

    public static BotApiMethod<Message> sendNotCorrect(Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId().toString())
                .text("Please enter the correct value!")
                .build();
    }

    public static BotApiMethod<Message> sendText(Message message, String text) {
        return SendMessage.builder()
                .chatId(message.getChatId().toString())
                .text(text)
                .build();
    }

    public static BotApiMethod<Message> sendText(Long chatId, String text) {
        return SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .build();
    }

    public static BotApiMethod<Message> sendInline(Long chatId, String text, InlineKeyboardMarkup inlineKeyboardMarkup) {
        return SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .replyMarkup(inlineKeyboardMarkup)
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

    public static BotApiMethod<Message> sendReply(Long chatId, String text, ReplyKeyboardMarkup replyKeyboardMarkup) {
        return SendMessage.builder()
                .chatId(chatId.toString())
                .replyMarkup(replyKeyboardMarkup)
                .text(text)
                .build();
    }
}
