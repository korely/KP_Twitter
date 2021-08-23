package ua.kate.KP_Twitter.app;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.kate.KP_Twitter.handler.*;
import ua.kate.KP_Twitter.storage.Storage;

import java.util.Scanner;

import static ua.kate.KP_Twitter.config.Configurator.initApp;


public class App {

    private static final Logger log = LogManager.getLogger(App.class);

    private final Handler defaultHandler;

    private App() {
        this.defaultHandler = new DefaultHandler();
    }


    public static void main(String[] args) {
        //parse initial arguments

        initApp(args);
        //run app

        App app = new App();
        app.run();

    }

    private void run() {

        Response response;
        do {
           // System.out.println(Storage.getUsers());
            response = startChOR();
        } while (response.getCommand() != Command.EXIT && response.isSuccess());
        System.exit(0);
    }

    private Response startChOR() {
        Command command = null;
        Scanner scanner = new Scanner(System.in);
        //wait for input
        while (command == null) {
            System.out.println("Please enter the command or write 'help'...");
            String input = scanner.nextLine();
            command = parseInput(input);
            if (command == null) {
                log.warn("Could not parse input: [" + input + "].");
            }
        }
       // scanner.close();
        //execute command
        Response response = defaultHandler.handle(command);
        log.info(response.toString());
        //render results
        return response;
    }

    private static Command parseInput(String input) {
        Command[] commands = Command.values();
        for (Command command : commands) {
            if (command.getValue().equalsIgnoreCase(input)) {
                return command;
            }
        }
        return null;
    }

}
