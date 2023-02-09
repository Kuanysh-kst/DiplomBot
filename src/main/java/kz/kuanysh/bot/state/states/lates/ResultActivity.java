//package kz.kuanysh.bot.state.states.lates;
//
//import kz.kuanysh.bot.state.UserActivity;
//import kz.kuanysh.bot.state.dialogs.Dialog;
//import kz.kuanysh.bot.state.dialogs.ResultDialog;
//import kz.kuanysh.bot.state.keyboards.Keyboard;
//import kz.kuanysh.bot.state.keyboards.ResultKeyBoard;
//import kz.kuanysh.bot.service.SendBotMessageServiceImp;
//import kz.kuanysh.bot.service.UserService;
//import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
//import org.telegram.telegrambots.meta.api.objects.Message;
//
//import java.io.Serializable;
//
//public class ResultActivity implements UserActivity {
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
////        return new ResultDialog();
////    }
////
////    @Override
////    public Keyboard createKeyBoard() {
////        return new ResultKeyBoard();
////    }
////
////    @Override
////    public void doEvent(UserService userService, Message message, String text) {
////        userService.saveUserCategory(message,text);
////        userService.saveUserContact(message);
////    }
////
////    @Override
////    public void execute(BotApiMethod<Serializable> response, SendBotMessageServiceImp sendBotMessageServiceImp) {
////        sendBotMessageServiceImp.sendMessageSerializable(response);
////    }
//}
