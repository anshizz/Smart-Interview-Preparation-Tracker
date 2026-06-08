package com.interviewtracker.model;

public class ReadinessScore {
    private int score; // 0 to 100
    private String category; // Beginner, Intermediate, Placement Ready, Interview Ready

    public ReadinessScore(int score) {
        this.score = score;
        if (score <= 40) {
            this.category = "Beginner";
        } else if (score <= 70) {
            this.category = "Intermediate";
        } else if (score <= 90) {
            this.category = "Placement Ready";
        } else {
            this.category = "Interview Ready";
        }
    }

    // Getters and Setters
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
