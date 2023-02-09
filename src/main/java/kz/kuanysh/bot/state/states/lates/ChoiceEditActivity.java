//package kz.kuanysh.bot.state.states.lates;
//
//import kz.kuanysh.bot.state.UserActivity;
//import kz.kuanysh.bot.state.dialogs.ChoiceDialog;
//import kz.kuanysh.bot.state.dialogs.Dialog;
//import kz.kuanysh.bot.state.keyboards.ChoiceEditKeyboard;
//import kz.kuanysh.bot.state.keyboards.Keyboard;
//import kz.kuanysh.bot.service.SendBotMessageServiceImp;
//import kz.kuanysh.bot.service.UserService;
//import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
//import org.telegram.telegrambots.meta.api.objects.Message;
//
//import java.io.Serializable;
//
//public class ChoiceEditActivity implements UserActivity {
//    @Override
//    public UserActivity nextDialogState(Object command) {
//        return null;
//    }
//
//    @Override
//    public UserActivity backDialogState() {
//        return null;
//    }
//
//    @Override
//    public String getContent(Message message) {
//        return null;
//    }
////    @Override
////    public Dialog createDialog() {
////        return new ChoiceDialog();
////    }
////
////    @Override
////    public Keyboard createKeyBoard() {
////        return new ChoiceEditKeyboard();
////    }
////
////    @Override
////    public void doEvent(UserService userService, Message message, String text) {
////        userService.saveUserInBase(message);
////    }
////
////    @Override
////    public void execute(BotApiMethod<Serializable> response, SendBotMessageServiceImp sendBotMessageServiceImp) {
////        sendBotMessageServiceImp.sendMessageSerializable(response);
////    }
//}
