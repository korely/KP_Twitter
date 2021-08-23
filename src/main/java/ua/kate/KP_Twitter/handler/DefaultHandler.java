package ua.kate.KP_Twitter.handler;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static ua.kate.KP_Twitter.config.Configurator.*;

public class DefaultHandler implements Handler {

    private static final Logger log = LogManager.getLogger(DefaultHandler.class);

    private final Handler handler;

    public DefaultHandler() {
        this.handler = new CreateUserHandler(helpHandler, userService);
    }

    @Override
    public Response handle(Command command) {
        return handler.handle(command);
    }
}
