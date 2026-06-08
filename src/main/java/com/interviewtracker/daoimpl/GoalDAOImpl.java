package com.interviewtracker.daoimpl;

import com.interviewtracker.dao.GoalDAO;
import com.interviewtracker.model.Goal;
import com.interviewtracker.utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GoalDAOImpl implements GoalDAO {

    @Override
    public boolean addGoal(Goal goal) {
        String query = "INSERT INTO goals (user_id, goal_title, target_count, deadline) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, goal.getUserId());
            stmt.setString(2, goal.getGoalTitle());
            stmt.setInt(3, goal.getTargetCount());
            stmt.setDate(4, goal.getDeadline());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateGoal(Goal goal) {
        return false;
    }

    @Override
    public boolean deleteGoal(int goalId) {
        return false;
    }

    @Override
    public Goal getGoalById(int goalId) {
        return null;
    }

    @Override
    public List<Goal> getAllGoalsByUser(int userId) {
        List<Goal> goals = new ArrayList<>();
        String query = "SELECT * FROM goals WHERE user_id = ? ORDER BY deadline ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Goal g = new Goal();
                g.setGoalId(rs.getInt("goal_id"));
                g.setUserId(rs.getInt("user_id"));
                g.setGoalTitle(rs.getString("goal_title"));
                g.setTargetCount(rs.getInt("target_count"));
                g.setCurrentCount(rs.getInt("current_count"));
                g.setDeadline(rs.getDate("deadline"));
                g.setStatus(rs.getString("status"));
                goals.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goals;
    }

    @Override
    public List<Goal> getActiveGoalsByUser(int userId) {
        return new ArrayList<>();
    }

    @Override
    public boolean updateGoalProgress(int goalId, int currentCount) {
        String query = "UPDATE goals SET current_count = ? WHERE goal_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, currentCount);
            stmt.setInt(2, goalId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
