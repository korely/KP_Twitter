package ua.kate.KP_Twitter.handler;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ClosingHandler implements Handler {

    private static final Logger log = LogManager.getLogger(ClosingHandler.class);

    @Override
    public Response handle(Command command) {
        log.warn("So sad! Could not handle the request.");
        return new Response(false, command, "Wrong request.");
    }
}
