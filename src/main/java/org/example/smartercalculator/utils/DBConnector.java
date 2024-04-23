package org.example.smartercalculator.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DBConnector {
    private static DBConnector instance = null;
    private Connection connection;

    public static DBConnector getInstance() {
        if (instance == null) instance = new DBConnector();
        return instance;
    }

    public Connection connectToDatabase(String url, String user, String password)
            throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }
}
