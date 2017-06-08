package SpringBootStarter.userInterfaceComponent;

import SpringBootStarter.Entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Ahmad on 05.06.2017.
 */
@Service
public class Listener {

    @Autowired
    UserInterface userInterface;
    private ObjectMapper objectMapper = new ObjectMapper();

    public Listener() {

    }

    public void startListening(BufferedReader is) {
        //erste Response abfangen
        String line = "";

        new Thread(new Runnable() {
            public void run() {
                String line = "";
                System.out.println("starte Listening");
                try {
                    while ((line = is.readLine()) != null) {
                        System.out.println(line);
                        Response response = objectMapper.readValue(line, Response.class);
                        HeaderResponse headerResponse = response.getHeader();
                        switch (headerResponse.getType()) {
                            case (GCaV.LOG_TYPE):
                                ResponseMe responseMe = objectMapper.readValue(line, ResponseMe.class);
                                userInterface.verwalteResponse(responseMe);
                                break;

                            case (GCaV.JOIN_TYPE):
                                ResponseName responseName = objectMapper.readValue(line, ResponseName.class);
                                userInterface.verwalteResponse(responseName);
                                break;
                            case (GCaV.LEAVE_TYPE):
                                ResponseName responseName2 = objectMapper.readValue(line, ResponseName.class);
                                userInterface.verwalteResponse(responseName2);
                                break;

                            case (GCaV.MY_CHATS_TYPE):
                                ResponseList responseList = objectMapper.readValue(line, ResponseList.class);
                                userInterface.verwalteResponse(responseList);
                                break;
                            case (GCaV.ALL_CHATS_TYPE):
                                ResponseList responseList2 = objectMapper.readValue(line, ResponseList.class);
                                userInterface.verwalteResponse(responseList2);
                                break;

                            case (GCaV.MESSAGE_TYPE):
                                ResponseMessage responseMessage = objectMapper.readValue(line, ResponseMessage.class);
                                userInterface.verwalteResponse(responseMessage);
                                break;


                        }

                    }
                } catch (Exception e) {
                    System.out.println("Listener");
                    System.out.println(e.getMessage());
                } catch (GivenObjectNotValidException e) {
                    System.out.println("Listener");
                    System.out.println(e.getMessage());
                }
            }
        }).start();
    }

}
