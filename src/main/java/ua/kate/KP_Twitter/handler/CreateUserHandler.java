package ua.kate.KP_Twitter.handler;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.kate.KP_Twitter.model.User;
import ua.kate.KP_Twitter.service.UserService;

import java.util.Scanner;

public class CreateUserHandler extends AbstractHandler {

    private final UserService userService;
    private static final Logger log = LogManager.getLogger(CreateUserHandler.class);

    public CreateUserHandler(Handler nextHandler, UserService userService) {
        super(Command.CREATE_USER, nextHandler);
        this.userService = userService;
    }

    @Override
    public Response handleCommand() {
        String login = null;
        String nickName = null;
        Scanner scanner = new Scanner(System.in);
        String input = null;

        log.info("Enter user's login...");
        while(input == null){
            input = scanner.nextLine();
            if (input==null){
                log.warn("Could not parse input: " + input);
                continue;
            }
            login = input;
        }

        log.info("Enter user's nickname...");
        while(nickName == null){
            input = scanner.nextLine();
            if (input==null){
                log.warn("Could not parse input: " + input);
                continue;
            }
            nickName = input;
        }

            User user = userService.createUser(login, nickName);
            return new Response(true, command, "User with nickname [" + user.getNickname() + "] has been created.");
        }

}
