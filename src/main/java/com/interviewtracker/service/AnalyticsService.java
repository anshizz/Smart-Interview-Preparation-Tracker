package com.interviewtracker.service;

import com.interviewtracker.dao.AnalyticsDAO;
import com.interviewtracker.daoimpl.AnalyticsDAOImpl;
import com.interviewtracker.model.Analytics;
import java.util.HashMap;
import java.util.Map;

public class AnalyticsService {
    private AnalyticsDAO analyticsDAO = new AnalyticsDAOImpl();

    public Analytics getDashboardAnalytics(int userId) {
        return analyticsDAO.getUserAnalytics(userId);
    }
    
    public int calculateReadinessScore(int userId) {
        return analyticsDAO.getReadinessScore(userId);
    }

    public Map<String, Integer> getWeakTopics(int userId) {
        // Mock implementation. In reality, aggregate by topic and return bottom 5
        Map<String, Integer> weak = new HashMap<>();
        weak.put("Dynamic Programming", 2);
        weak.put("Graphs", 3);
        weak.put("Tries", 1);
        weak.put("Heaps", 2);
        weak.put("Backtracking", 0);
        return weak;
    }
}
