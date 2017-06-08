package SpringBootStarter.userInterfaceComponent;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ahmad on 05.06.2017.
 */
@Controller
public class Terminal implements CommandLineRunner {

    @Autowired
    UserInterface userInterface;
    private static Scanner scanner = new Scanner(System.in);
    private static final String GETROOMSLOCALCOMMAND = "getRoomsLocal";
    private static final String GETROOMSSERVERCOMMAND = "getRoomsServer";
    private static final String JOINROOMCOMMAND = "joinRoom";
    private static final String LEAVEROOMCOMMAND = "leaveRoom";
    private static final String SENDMESSAGECOMMAND = "sendMessage";
    private static final String HELPCOMMAND = "help";
    private static final String COMMANDREGEX = "(" + GETROOMSLOCALCOMMAND + "|" + GETROOMSSERVERCOMMAND + "|" + JOINROOMCOMMAND
            + "|" + LEAVEROOMCOMMAND + "|" + SENDMESSAGECOMMAND + "|" + HELPCOMMAND + ")( (\\w+)( (.+))?)?";
    private static final Pattern COMMANDPATTERN = Pattern.compile(COMMANDREGEX);


    private ObjectMapper mapper = new ObjectMapper();

    @Async
    public void run(String[] args) {
        System.out.println("dfs");
        kommSchon();

    }

    public void kommSchon() {
        System.out.println("\n\n");
        System.out.println("Willkommen im Chatbot");
        System.out.println("Melden Sie sich beim Server an:");
        meldeUserAn();
        System.out.println("Sie können nun: ");
        printHelpCommands();
        String command;
        Matcher matcher;
        while (!Thread.currentThread().isInterrupted()) {
            command = scanner.nextLine();
            matcher = COMMANDPATTERN.matcher(command);
            if (matcher.find()) {
                String argument1 = matcher.group(3);
                switch (matcher.group(1)) {
                    case (GETROOMSLOCALCOMMAND):
                        System.out.println("Eine Liste aller Räume in denen man sich gerade befindet.");
                        try {
                            userInterface.getRoomsLocal();
                        } catch (Exception e) {
                            System.out.println("GetRoomLocalTerminal");

                            System.out.println(e.getMessage());
                        }
                        break;
                    case (GETROOMSSERVERCOMMAND):
                        System.out.println("Eine Liste aller Räume des Servers:");
                        try {
                            userInterface.getRoomsServer();
                        } catch (Exception e) {
                            System.out.println("GetRoomServerTerminal");
                            System.out.println(e.getMessage());
                        }
                        break;

                    case (JOINROOMCOMMAND):
                        if (argument1 == null) {
                            System.out.println("Der Befehl: " + JOINROOMCOMMAND
                                    + " benötigt als Argument einen gültigen Raumnamen. \nNutzen sie den Command: "
                                    + HELPCOMMAND + " um gültige Befehle zu finden");
                        } else {
                            System.out.println("Der User tritt dem Raum bei: ");
                            try {
                                userInterface.joinRoom(argument1);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            } catch (GivenObjectNotValidException e) {
                                System.out.println("joinRoomTerminal:");
                                System.out.println(e.getMessage());
                            }
                        }
                        break;

                    case (LEAVEROOMCOMMAND):
                        if (argument1 == null) {
                            System.out.println("Der Befehl: " + LEAVEROOMCOMMAND
                                    + " benötigt als Argument einen gültigen Raumnamen. \nNutzen sie den Command: "
                                    + HELPCOMMAND + " um gültige Befehle zu finden");
                        } else {
                            try {
                                userInterface.leaveRoom(argument1);
                            } catch (Exception e) {
                                System.out.println("LeaveRoomTerminal");
                                System.out.println(e.getMessage());
                            }
                        }
                        break;

                    case (SENDMESSAGECOMMAND):
                        String message = matcher.group(5);
                        if (argument1 == null) {
                            System.out.println("Der Befehl: " + SENDMESSAGECOMMAND
                                    + " benötigt als Argument einen gültigen Raumnamen. \nNutzen sie den Command: "
                                    + HELPCOMMAND + " um gültige Befehle zu finden");
                        } else if (message == null) {
                            System.out.println("Der Befehl: " + SENDMESSAGECOMMAND
                                    + " benötigt als Argument eine gültige Message. \nNutzen sie den Command: "
                                    + HELPCOMMAND + " um gültige Befehle zu finden");
                        } else {

                            System.out.println("Die Message wird versucht zu senden.");
                            try {
                                System.out.println(argument1+" "+message);
                                userInterface.sendMessage(argument1, message);
                            }catch (GivenObjectNotValidException e) {
                                System.out.println("sendMessageTerminal");
                                System.out.println(e.getMessage());
                            } catch (IOException e) {
                                System.out.println("sendMessageTerminalIoException");
                                System.out.println(e.getMessage());
                            }
                        }
                        break;


                    case (HELPCOMMAND):

                        System.out.println("Gültige Befehle sind: ");
                        printHelpCommands();
                        break;
                }

            }
        }
    }


    private static void printHelpCommands() {
        System.out.println("	-eine Liste aller Räume in der man sich befindet abrufen: " + GETROOMSLOCALCOMMAND);
        System.out.println("	-eine Liste aller Räume die der Server anbietet: " + GETROOMSSERVERCOMMAND);
        System.out.println("	-einen Raum beitreten: " + JOINROOMCOMMAND + " <roomName>");
        System.out.println("	-einen Raum verlassen: " + LEAVEROOMCOMMAND + " <roomName>");
        System.out
                .println("	-eine Nachricht in einem Raum zu senden: " + SENDMESSAGECOMMAND + " <roomName> <message>");
        System.out.println("	-alle erlaubten Befehle ausgeben: " + HELPCOMMAND);
    }

    private void meldeUserAn() {
        while (!userInterface.isUserValid()) {
            String userName = null;
            /**
             * Solange der Username nicht korrekt eingegeben ist frage nach
             * einem Usernamen
             */
            while (userName == null) {
                System.out.println("Geben Sie ihren Usernamen ein: ");
                userName = scanner.next();
                if (userName != null) {
                    if (!userName.matches(GCaV.NAME_REGEX)) {
                        System.out.println("Der Username: " + userName + " entspricht nicht der Regex: "
                                + GCaV.NAME_REGEX);
                        userName = null;
                    }
                }
            }


            /**
             * Versuche die aktuelle IpAdresse des Servers zu erhalten.
             */
            String serverIpAdress = null;
            while (serverIpAdress == null) {
                System.out.println("Geben Sie die IpAdresse des Servers an: ");
                serverIpAdress = (scanner.next());
            }

            /**
             * Versuche beim Server anzumelden.
             */
            try {

                userInterface.loggeEin(serverIpAdress, userName);
                int counter = 0;
                while (counter < 1000 && !userInterface.isUserValid()) {
                    System.out.println("warte beim Einloggen");
                    Thread.sleep(1000);
                }

            } catch (Exception e) {
                System.out.println("LoggeEinTerminal");
                System.out.println(e.getMessage());
            } catch (GivenObjectNotValidException e) {
                System.out.println("LoggeEinTerminal");
                System.out.println(e.getMessage());
            }

        }
    }
}
