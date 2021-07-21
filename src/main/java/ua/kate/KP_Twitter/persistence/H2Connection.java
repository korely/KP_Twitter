package ua.kate.KP_Twitter.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {

    private static H2Connection instance;
    private Connection connection;
    private static String url = "jdbc:h2:file:C:/Users/korel/IdeaProjects/KP_Twitter/database/db";
    private static String driver = "org.h2.Driver";

    private H2Connection() throws SQLException {

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.connection = DriverManager.getConnection(url);
    }

    public Connection getConnection() {
        return connection;
    }

    public static H2Connection getInstance() throws SQLException {
        if (instance == null) {
            instance = new H2Connection();
        } else if (instance.getConnection().isClosed()) {
            instance = new H2Connection();
        }
        return instance;
    }
}
