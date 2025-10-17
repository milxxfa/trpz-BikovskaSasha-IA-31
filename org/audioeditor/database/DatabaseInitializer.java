package org.audioeditor.database;

import org.audioeditor.repository.IRepository;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Ініціалізатор бази даних: створює потрібні таблиці, якщо їх ще немає.
 * Працює з DatabaseConnection (наприклад, sqlite jdbc).
 */
public abstract class DatabaseInitializer implements IRepository {

    public void initializeDatabase() {
        String sqlUsers = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL UNIQUE," +
                "password TEXT NOT NULL," +
                "created_at TEXT" +
                ");";

        String sqlProjects = "CREATE TABLE IF NOT EXISTS projects (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "created_at TEXT," +
                "user_id INTEGER," +
                "FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE SET NULL" +
                ");";

        String sqlAudio = "CREATE TABLE IF NOT EXISTS audio (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "format TEXT," +
                "path TEXT," +
                "duration REAL" +
                ");";

        String sqlTracks = "CREATE TABLE IF NOT EXISTS tracks (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "audio_id INTEGER NOT NULL," +
                "project_id INTEGER," +
                "start_time INTEGER," +
                "end_time INTEGER," +
                "FOREIGN KEY(audio_id) REFERENCES audio(id) ON DELETE CASCADE," +
                "FOREIGN KEY(project_id) REFERENCES projects(id) ON DELETE CASCADE" +
                ");";

        String sqlRoles = "CREATE TABLE IF NOT EXISTS roles (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL UNIQUE" +
                ");";

        String sqlUserRoles = "CREATE TABLE IF NOT EXISTS user_roles (" +
                "user_id INTEGER," +
                "role_id INTEGER," +
                "PRIMARY KEY(user_id, role_id)," +
                "FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE," +
                "FOREIGN KEY(role_id) REFERENCES roles(id) ON DELETE CASCADE" +
                ");";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sqlUsers);
            stmt.execute(sqlRoles);
            stmt.execute(sqlUserRoles);
            stmt.execute(sqlProjects);
            stmt.execute(sqlAudio);
            stmt.execute(sqlTracks);

            System.out.println("Database initialized (tables created if absent).");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
