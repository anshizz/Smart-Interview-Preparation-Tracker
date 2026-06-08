package com.interviewtracker.model;

import java.sql.Timestamp;

public class Badge {
    private int badgeId;
    private int userId;
    private String badgeName;
    private Timestamp awardedDate;

    // Getters and Setters
    public int getBadgeId() { return badgeId; }
    public void setBadgeId(int badgeId) { this.badgeId = badgeId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getBadgeName() { return badgeName; }
    public void setBadgeName(String badgeName) { this.badgeName = badgeName; }
    public Timestamp getAwardedDate() { return awardedDate; }
    public void setAwardedDate(Timestamp awardedDate) { this.awardedDate = awardedDate; }
}
