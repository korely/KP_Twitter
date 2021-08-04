package ua.kate.KP_Twitter.persistence.dao;

public enum DaoType {

    IN_MEM("inMem"), JDBC("jdbc");

    private final String value;

    DaoType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
