package org.audioeditor.database;

import org.audioeditor.repository.ProjectRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseConnection extends ProjectRepository {
    private static final String URL = "jdbc:sqlite:audioeditor.db";
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL);
                System.out.println("Підключено до бази даних.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}