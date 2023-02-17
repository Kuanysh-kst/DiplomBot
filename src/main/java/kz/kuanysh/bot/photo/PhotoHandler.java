package kz.kuanysh.bot.photo;

import kz.kuanysh.bot.service.SendBotMessageServiceImp;
import kz.kuanysh.bot.service.UserService;
import kz.kuanysh.bot.state.Dialog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
@Slf4j
public class PhotoHandler {

    public static void savePhoto(SendBotMessageServiceImp execute, Message message, Dialog state) {

        List<PhotoSize> photos = message.getPhoto();
        PhotoSize photo = photos.stream()
                .max(Comparator.comparing(PhotoSize::getFileSize))
                .orElse(null);
        if (photo != null) {
            try {
                String fileUrl = execute.getPhotoUrl(photo);
                URL url  = new URL(fileUrl);
                File file = new File("photo_"+message.getChatId()+".jpg");
                FileUtils.copyURLToFile(url,file);
                state.setFile(file);
                log.info("Photo is saved");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
