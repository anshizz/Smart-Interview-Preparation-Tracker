package com.interviewtracker.daoimpl;

import com.interviewtracker.dao.RevisionDAO;
import com.interviewtracker.model.Revision;
import com.interviewtracker.utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RevisionDAOImpl implements RevisionDAO {

    @Override
    public boolean addRevision(Revision revision) {
        return false;
    }

    @Override
    public boolean updateRevision(Revision revision) {
        return false;
    }

    @Override
    public boolean deleteRevision(int revisionId) {
        return false;
    }

    @Override
    public List<Revision> getAllRevisionsByUser(int userId) {
        List<Revision> revisions = new ArrayList<>();
        String query = "SELECT * FROM revisions WHERE user_id = ? ORDER BY revision_date ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Revision r = new Revision();
                r.setRevisionId(rs.getInt("revision_id"));
                r.setUserId(rs.getInt("user_id"));
                r.setTopic(rs.getString("topic"));
                r.setRevisionDate(rs.getDate("revision_date"));
                r.setCompleted(rs.getBoolean("completed"));
                revisions.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return revisions;
    }

    @Override
    public List<Revision> getPendingRevisionsByUser(int userId) {
        return new ArrayList<>();
    }

    @Override
    public boolean markAsCompleted(int revisionId) {
        String query = "UPDATE revisions SET completed = TRUE WHERE revision_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, revisionId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
