package com.interviewtracker.dao;

import com.interviewtracker.model.Goal;
import java.util.List;

public interface GoalDAO {
    boolean addGoal(Goal goal);
    boolean updateGoal(Goal goal);
    boolean deleteGoal(int goalId);
    Goal getGoalById(int goalId);
    List<Goal> getAllGoalsByUser(int userId);
    List<Goal> getActiveGoalsByUser(int userId);
    boolean updateGoalProgress(int goalId, int currentCount);
}
