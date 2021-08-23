package ua.kate.KP_Twitter.handler;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ExitHandler extends AbstractHandler {

    private static final Logger log = LogManager.getLogger(ExitHandler.class);

    public ExitHandler(Handler nextHandler) {
        super(Command.EXIT, new ClosingHandler());
    }

    @Override
    protected Response handleCommand() {
        log.info("The application is shutting down.");
        return new Response(true, command, "Shuting down");
    }
}
