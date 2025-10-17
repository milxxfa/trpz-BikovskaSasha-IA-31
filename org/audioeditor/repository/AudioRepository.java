package org.audioeditor.repository;

import org.audioeditor.database.DatabaseConnection;
import java.sql.*;
import java.util.*;

/**
 * Репозиторій для таблиці audio (id, name, format, path, duration).
 * Тут використовується простий JDBC.
 */
public abstract class AudioRepository extends DatabaseConnection {

    public void addAudio(String name, String format, String path, double duration) {
        String sql = "INSERT INTO audio(name, format, path, duration) VALUES(?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, format);
            ps.setString(3, path);
            ps.setDouble(4, duration);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<String> getAllAudio() {
        List<String> result = new ArrayList<>();
        String sql = "SELECT id, name, format FROM audio ORDER BY id";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                result.add(rs.getInt("id") + ":" + rs.getString("name") + "." + rs.getString("format"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<String> getAudioByProject(int projectId) {
        List<String> result = new ArrayList<>();
        String sql = "SELECT a.id, a.name, a.format FROM audio a JOIN tracks t ON a.id = t.audio_id WHERE t.project_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, projectId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(rs.getInt("id") + ":" + rs.getString("name") + "." + rs.getString("format"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
