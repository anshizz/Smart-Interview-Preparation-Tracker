package com.interviewtracker.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Goal {
    private int goalId;
    private int userId;
    private String goalTitle;
    private int targetCount;
    private int currentCount;
    private Date deadline;
    private String status;
    private Timestamp createdAt;

    // Getters and Setters
    public int getGoalId() { return goalId; }
    public void setGoalId(int goalId) { this.goalId = goalId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getGoalTitle() { return goalTitle; }
    public void setGoalTitle(String goalTitle) { this.goalTitle = goalTitle; }
    public int getTargetCount() { return targetCount; }
    public void setTargetCount(int targetCount) { this.targetCount = targetCount; }
    public int getCurrentCount() { return currentCount; }
    public void setCurrentCount(int currentCount) { this.currentCount = currentCount; }
    public Date getDeadline() { return deadline; }
    public void setDeadline(Date deadline) { this.deadline = deadline; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    
    // Helper method
    public int getProgressPercentage() {
        if (targetCount == 0) return 0;
        return (int) (((double) currentCount / targetCount) * 100);
    }
}
