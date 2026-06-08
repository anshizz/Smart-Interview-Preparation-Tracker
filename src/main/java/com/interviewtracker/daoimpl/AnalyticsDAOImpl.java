package com.interviewtracker.daoimpl;

import com.interviewtracker.dao.AnalyticsDAO;
import com.interviewtracker.model.Analytics;
import com.interviewtracker.utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AnalyticsDAOImpl implements AnalyticsDAO {

    @Override
    public Analytics getUserAnalytics(int userId) {
        Analytics analytics = new Analytics();
        String query = "SELECT " +
                       "COUNT(*) AS total, " +
                       "SUM(CASE WHEN difficulty = 'Easy' THEN 1 ELSE 0 END) AS easy_count, " +
                       "SUM(CASE WHEN difficulty = 'Medium' THEN 1 ELSE 0 END) AS medium_count, " +
                       "SUM(CASE WHEN difficulty = 'Hard' THEN 1 ELSE 0 END) AS hard_count " +
                       "FROM questions WHERE user_id = ? AND status = 'Solved'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                analytics.setTotalQuestionsSolved(rs.getInt("total"));
                analytics.setEasyCount(rs.getInt("easy_count"));
                analytics.setMediumCount(rs.getInt("medium_count"));
                analytics.setHardCount(rs.getInt("hard_count"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return analytics;
    }

    @Override
    public int getReadinessScore(int userId) {
        // Simple logic for readiness score
        Analytics a = getUserAnalytics(userId);
        int score = a.getTotalQuestionsSolved() / 2; // e.g. 200 questions = 100 score
        if (score > 100) score = 100;
        return score;
    }
}
