package ua.app.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDataBaseConnection {
    private static final String CONNECTION_URL ="jdbc:postgresql://localhost:5432/TheRavenDB?user=postgres&password=postgres";

    public PostgresDataBaseConnection() {
    }

    public Connection getConnection() {

        try {
            return DriverManager.getConnection(CONNECTION_URL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
