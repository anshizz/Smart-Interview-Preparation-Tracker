package com.interviewtracker.model;

import java.sql.Timestamp;

public class Activity {
    private int activityId;
    private int userId;
    private String activityType;
    private Timestamp activityDate;

    public Activity() {}

    public Activity(int userId, String activityType) {
        this.userId = userId;
        this.activityType = activityType;
    }

    // Getters and Setters
    public int getActivityId() { return activityId; }
    public void setActivityId(int activityId) { this.activityId = activityId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getActivityType() { return activityType; }
    public void setActivityType(String activityType) { this.activityType = activityType; }
    public Timestamp getActivityDate() { return activityDate; }
    public void setActivityDate(Timestamp activityDate) { this.activityDate = activityDate; }
}
