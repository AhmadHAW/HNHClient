package SpringBootStarter.userInterfaceComponent;

/**
 * Created by Ahmad on 05.06.2017.
 */
public class GCaV {
    public static final int USERPORT = 8888;
    public static final int SERVERPORT = 4444;
    public static final String LOG_URL = "/me";
    public static final String LOG_TYPE = "me";
    public static final String SEND_URL_PRE = "/chat/";
    public static final String SEND_URL_POST = "/send";
    public static final String ALL_ROOMS_URL = "/chat";
    public static final String LOCAL_ROOMS_URL = "/me/chat";
    public static final String JOIN_URL_PRE = "/chat/";
    public static final String JOIN_URL_POST = "/join";
    public static final String LEAVE_URL_PRE = "/chat/";
    public static final String LEAVE_URL_POST = "/leave";
    public static final String MESSAGE_TYPE = "message";
    public static final String NAME_REGEX= "[0-9a-z]+";
    public static final String JOIN_TYPE = "join";
    public static final String LEAVE_TYPE = "leave";
    public static final String MY_CHATS_TYPE = "myChats";
    public static final String ALL_CHATS_TYPE = "allChats";


    public static boolean isValidName(String name) {
        return name != null && name.matches("[a-z0-9]+");
    }


}
