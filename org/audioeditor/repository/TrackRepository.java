package org.audioeditor.repository;

import org.audioeditor.database.DatabaseConnection;
import java.sql.*;
import java.util.*;

/**
 * Репозиторій для треків (tracks).
 */
public abstract class TrackRepository extends DatabaseConnection {

    public void addTrack(int audioId, int projectId, int startTime, int endTime) {
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

    public List<String> getAllTracks() {
        List<String> out = new ArrayList<>();
        String sql = "SELECT t.id, a.name AS audio_name, t.start_time, t.end_time FROM tracks t LEFT JOIN audio a ON t.audio_id = a.id ORDER BY t.id";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                out.add(rs.getInt("id") + ":" + rs.getString("audio_name") + "[" + rs.getInt("start_time") + "-" + rs.getInt("end_time") + "]");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return out;
    }
}
