package org.audioeditor.repository;

import org.audioeditor.database.DatabaseConnection;
import java.sql.*;
import java.util.*;

/**
 * Репозиторій для таблиці projects (id, name, created_at, user_id).
 */
public abstract class ProjectRepository implements IRepository {

    public int addProject(String name, Integer userId) {
        String sql = "INSERT INTO projects(name, created_at, user_id) VALUES(?, datetime('now'), ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            if (userId != null) ps.setInt(2, userId); else ps.setNull(2, Types.INTEGER);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public List<String> getAllProjects() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT id, name FROM projects ORDER BY id";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(rs.getInt("id") + ":" + rs.getString("name"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void addAudioToProject(int projectId, int audioId, int startTime, int endTime) {
        String sql = "INSERT INTO tracks(audio_id, project_id, start_time, end_time) VALUES(?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, audioId);
            ps.setInt(2, projectId);
            ps.setInt(3, startTime);
            ps.setInt(4, endTime);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
