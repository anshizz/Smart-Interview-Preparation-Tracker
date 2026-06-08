package com.interviewtracker.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Question {
    private int questionId;
    private int userId;
    private String title;
    private String platform;
    private String topic;
    private String difficulty;
    private String status;
    private String notes;
    private Date dateSolved;
    private Timestamp createdAt;

    // Getters and Setters
    public int getQuestionId() { return questionId; }
    public void setQuestionId(int questionId) { this.questionId = questionId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Date getDateSolved() { return dateSolved; }
    public void setDateSolved(Date dateSolved) { this.dateSolved = dateSolved; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
