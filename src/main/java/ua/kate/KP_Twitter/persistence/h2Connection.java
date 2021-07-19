package ua.kate.KP_Twitter.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class h2Connection implements dbConnection {

    private Connection connection = null;
    String dbUrl = "jdbc:h2:file:C:/Users/korel/IdeaProjects/KP_Twitter/database/db";
    String driver = "org.h2.Driver";

    @Override
    public Connection getConnection() {
        try {
            if (connection == null) {
                Class.forName(driver);
                connection = DriverManager.getConnection(dbUrl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}

