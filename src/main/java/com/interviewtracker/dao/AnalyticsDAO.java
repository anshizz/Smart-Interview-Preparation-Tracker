package com.interviewtracker.dao;

import com.interviewtracker.model.Analytics;

public interface AnalyticsDAO {
    Analytics getUserAnalytics(int userId);
    int getReadinessScore(int userId);
}
