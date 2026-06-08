package com.interviewtracker.model;

import java.util.Map;

public class Analytics {
    private int totalQuestionsSolved;
    private int easyCount;
    private int mediumCount;
    private int hardCount;
    private Map<String, Integer> topicDistribution;
    private Map<String, Integer> weeklyProgress;

    // Getters and Setters
    public int getTotalQuestionsSolved() { return totalQuestionsSolved; }
    public void setTotalQuestionsSolved(int totalQuestionsSolved) { this.totalQuestionsSolved = totalQuestionsSolved; }
    public int getEasyCount() { return easyCount; }
    public void setEasyCount(int easyCount) { this.easyCount = easyCount; }
    public int getMediumCount() { return mediumCount; }
    public void setMediumCount(int mediumCount) { this.mediumCount = mediumCount; }
    public int getHardCount() { return hardCount; }
    public void setHardCount(int hardCount) { this.hardCount = hardCount; }
    public Map<String, Integer> getTopicDistribution() { return topicDistribution; }
    public void setTopicDistribution(Map<String, Integer> topicDistribution) { this.topicDistribution = topicDistribution; }
    public Map<String, Integer> getWeeklyProgress() { return weeklyProgress; }
    public void setWeeklyProgress(Map<String, Integer> weeklyProgress) { this.weeklyProgress = weeklyProgress; }
}
