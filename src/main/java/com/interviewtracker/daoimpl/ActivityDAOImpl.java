package com.interviewtracker.daoimpl;

import com.interviewtracker.dao.ActivityDAO;
import com.interviewtracker.model.Activity;
import com.interviewtracker.utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAOImpl implements ActivityDAO {

    @Override
    public boolean addActivity(Activity activity) {
        String query = "INSERT INTO activity_logs (user_id, activity_type) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, activity.getUserId());
            stmt.setString(2, activity.getActivityType());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Activity> getRecentActivitiesByUser(int userId, int limit) {
        List<Activity> activities = new ArrayList<>();
        String query = "SELECT * FROM activity_logs WHERE user_id = ? ORDER BY activity_date DESC LIMIT ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Activity a = new Activity();
                a.setActivityId(rs.getInt("activity_id"));
                a.setUserId(rs.getInt("user_id"));
                a.setActivityType(rs.getString("activity_type"));
                a.setActivityDate(rs.getTimestamp("activity_date"));
                activities.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activities;
    }
}
