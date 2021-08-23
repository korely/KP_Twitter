package ua.kate.KP_Twitter.handler;

public enum Command {
    EXIT("exit"), CREATE_USER("create user"), HELP("help");

    private String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
