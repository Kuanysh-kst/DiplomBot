//package kz.kuanysh.bot.state.states.lates;
//
//import kz.kuanysh.bot.state.UserActivity;
//import kz.kuanysh.bot.state.dialogs.Dialog;
//import kz.kuanysh.bot.state.dialogs.info.FinishDialog;
//import kz.kuanysh.bot.state.keyboards.Keyboard;
//import kz.kuanysh.bot.state.keyboards.StartKeyboard;
//import kz.kuanysh.bot.model.User;
//import kz.kuanysh.bot.service.SendBotMessageServiceImp;
//import kz.kuanysh.bot.service.UserService;
//import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
//import org.telegram.telegrambots.meta.api.methods.send.SendContact;
//import org.telegram.telegrambots.meta.api.objects.Message;
//
//import java.io.Serializable;
//import java.util.List;
//
//public class FinishActivity implements UserActivity {
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
////
////    private List<User> users;
////
////    private Message message;
////
////    public Message getMessage() {
////        return message;
////    }
////
////    public void setMessage(Message message) {
////        this.message = message;
////    }
////
////    @Override
////    public Dialog createDialog() {
////        return new FinishDialog();
////    }
////
////    @Override
////    public Keyboard createKeyBoard() {
////        return new StartKeyboard();
////    }
////
////    @Override
////    public void doEvent(UserService userService, Message message, String text) {
////        User user = userService.getUserById(message.getChatId());
////        List<User> users = userService.findByStatusAndCategory(user.getStatus(),user.getCategory());
////        setUsers(users);
////        setMessage(message);
////    }
////
////    @Override
////    public void execute(BotApiMethod<Serializable> response, SendBotMessageServiceImp sendBotMessageServiceImp) {
////        Message message1 = getMessage();
////        message1.getContact();
////        List<User> sortedUsers = getUsers();
////        for (User user: users){
////
////            SendContact sendContact = SendContact.builder()
////                    .firstName(user.getFirstName())
////                    .lastName(user.getLastName())
////                    .phoneNumber(user.getContact().getPhoneNumber())
////                    .build();
////            BotApiMethod contact = sendContact;
////            sendBotMessageServiceImp.sendMessageSerializable(contact);
////        }
////    }
////
////    private void setUsers(List<User> users){
////        this.users = users;
////    }
////
////    public List<User> getUsers(){
////        return users;
////    }
//}
