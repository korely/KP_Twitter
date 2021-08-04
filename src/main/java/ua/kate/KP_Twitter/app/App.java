package ua.kate.KP_Twitter.app;

import static ua.kate.KP_Twitter.config.Configurator.initApp;

public class App {

    private App() {

    }

    public static void main(String[] args) {
        //parse initial arguments and configure the ua.kate.KP_Twitter.app

        initApp(args);
        //run
        run();

    }

    private static void run() {
        System.out.println("Hello world!");
        System.exit(0);
    }

}
