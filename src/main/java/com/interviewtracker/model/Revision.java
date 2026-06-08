package com.interviewtracker.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Revision {
    private int revisionId;
    private int userId;
    private String topic;
    private Date revisionDate;
    private boolean completed;
    private Timestamp createdAt;

    // Getters and Setters
    public int getRevisionId() { return revisionId; }
    public void setRevisionId(int revisionId) { this.revisionId = revisionId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public Date getRevisionDate() { return revisionDate; }
    public void setRevisionDate(Date revisionDate) { this.revisionDate = revisionDate; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
