package SpringBootStarter.userInterfaceComponent;

import SpringBootStarter.Entities.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Ahmad on 05.06.2017.
 */
@Service
public class UserInterface {

    private Socket socket;
    private User user = new User();
    @Autowired
    private Listener listener;
    private DataOutputStream os;
    private ObjectMapper mapper = new ObjectMapper();

    Set<Request> requests = Collections.synchronizedSet(new HashSet<Request>());

    public UserInterface() {

    }

    public void loggeEin(String ipAdress, String userName) throws GivenObjectNotValidException, IOException {
        if (!GCaV.isValidName(userName)) {
            throw new GivenObjectNotValidException("scheiß username: " + userName);
        }
        System.out.println("starte Socket");
        socket = new Socket(ipAdress, GCaV.SERVERPORT);

        user = new User(userName);
        listener.startListening(new BufferedReader(new InputStreamReader(socket.getInputStream())));
        os = new DataOutputStream(socket.getOutputStream());
        Request request = new RequestPost(new HeadAnmeldung(GCaV.LOG_URL, "POST"), new BodyPost(userName));
        String requestString;
        requestString = mapper.writeValueAsString(request);
        System.out.println(requestString);
        requests.add(request);
        os.writeBytes(requestString + "\n");
        os.flush();
    }

    public void joinRoom(String roomName) throws GivenObjectNotValidException, IOException {
        if (!GCaV.isValidName(roomName)) {
            throw new GivenObjectNotValidException("scheiß roomname: " + roomName);
        }
        Request request = new Request(new HeadPost(GCaV.JOIN_URL_PRE + roomName + GCaV.JOIN_URL_POST, "GET", user.getUserId()));
        String requestString;
        requests.add(request);
        requestString = mapper.writeValueAsString(request);
        System.out.println(requestString);
        os.writeBytes(requestString + "\n");
        os.flush();
    }

    public void sendMessage(String roomName, String message) throws GivenObjectNotValidException, IOException {
        if (!GCaV.isValidName(roomName)) {
            System.out.println("userInterface isValidName");
            throw new GivenObjectNotValidException("scheiß roomname: " + roomName);
        }
        if (message == null) {
            System.out.println("userInterface messageNull");
            throw new GivenObjectNotValidException("Schreib mal was in die Nachricht!");
        }
        Optional<String> opt = user.getRooms().stream().filter(t -> t.equals(roomName)).findFirst();
        if (!opt.isPresent()) {
            throw new GivenObjectNotValidException("Keen son Raum " + roomName);
        }
        Request request = new RequestPost(new HeadPost(GCaV.SEND_URL_PRE + roomName + GCaV.SEND_URL_POST, "POST", user.getUserId()), new BodyPost(message));
        String requestString;
        requests.add(request);
        requestString = mapper.writeValueAsString(request);
        System.out.println(requestString);
        os.writeBytes(requestString + "\n");
        os.flush();
    }

    public void leaveRoom(String roomName) throws Exception {
        if (!GCaV.isValidName(roomName)) {
            throw new Exception("scheiß roomname: " + roomName);
        }
        Request request = new Request(new HeadPost(GCaV.LEAVE_URL_PRE + roomName + GCaV.LEAVE_URL_POST, "GET", user.getUserId()));
        String requestString;
        requests.add(request);
        requestString = mapper.writeValueAsString(request);
        os.writeBytes(requestString + "\n");
        os.flush();
    }

    public void getRoomsServer() throws IOException {
        Request request = new Request(new HeadPost(GCaV.ALL_ROOMS_URL, "GET", user.getUserId()));
        String requestString;
        requestString = mapper.writeValueAsString(request);
        requests.add(request);
        os.writeBytes(requestString + "\n");
        os.flush();
    }

    public void getRoomsLocal() throws IOException {
        Request request = new Request(new HeadPost(GCaV.LOCAL_ROOMS_URL, "GET", user.getUserId()));
        String requestString;
        requestString = mapper.writeValueAsString(request);
        requests.add(request);
        os.writeBytes(requestString + "\n");
        os.flush();
    }

    protected void verwalteResponse(ResponseMessage response) throws GivenObjectNotValidException {
        HeaderResponse header = response.getHeader();

        BodyResponseMessage bodyMessage = response.getBody();
        if (header.getCode() == 200) {
            if(bodyMessage.getSender().equals(user.getUserName())){
                System.out.println("Die Message: /" + bodyMessage.getChatName() + ": " + bodyMessage.getContent() + " wurde gesendet");
            }
            else{
                System.out.println(bodyMessage.getChatName()+"/"+bodyMessage.getSender()+": "+bodyMessage.getContent());
            }
        } else {
            throw new GivenObjectNotValidException("Die Message ist kacke\n" + header.getDescription());
        }

    }

    public boolean isUserValid() {
        return user.isValid();
    }


    public void verwalteResponse(ResponseMe responseMe) throws GivenObjectNotValidException {
        HeaderResponse header = responseMe.getHeader();
        BodyResponseMe body = responseMe.getBody();
        if (header.getCode() == 200) {
            user.setUserId(body.getContent());
            System.out.println("Der User wurde eingeloggt");
        } else {
            throw new GivenObjectNotValidException("Der User konnte nicht angemeldet werden. Versuche es erneut!!\n" + header.getDescription());
        }
    }

    public void verwalteResponse(ResponseName responseName) {
        HeaderResponse header = responseName.getHeader();
        BodyResponseName body = responseName.getBody();
        if (header.getCode() == 200) {
            String result = "Der User hat den Raum " + body.getName();
            if (header.getType().equals(GCaV.JOIN_TYPE)) {
                System.out.println(result += " betreten");
                user.addRoom(body.getName());
            } else {
                System.out.println(result += " verlassen");
                user.removeRoom(body.getName());
            }
        } else if (header.getCode() == 201) {
            System.out.println("Der User hat den Raum " + body.getName() + " erstellt und ist ihm beigetreten.");
        } else {
            if (header.getType().equals(GCaV.JOIN_TYPE)) {
                System.out.println("Das Betreten des Raumes " + body.getName() + "ist fehlgeschlagen\n" + header.getDescription());
            } else {
                System.out.println("Das Verlassen des Raumes " + body.getName() + "ist fehlgeschlagen\n" + header.getDescription());
            }
        }

    }

    public void verwalteResponse(ResponseList responseList) {
        HeaderResponse header = responseList.getHeader();
        if (header.getCode() == 200) {
            String result = "Die Liste ";
            if (header.getType().equals(GCaV.ALL_CHATS_TYPE)) {
                result += "aller Chats ist: ";
            } else {
                result += "der betretenen Chats ist: ";
            }
            result += responseList.getBody().getChats();
            System.out.println(result);
        } else {
            System.out.println("Die Liste ist kacke\n" + header.getDescription());
        }
    }
}
