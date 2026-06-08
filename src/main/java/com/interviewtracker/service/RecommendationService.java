package com.interviewtracker.service;

import java.util.ArrayList;
import java.util.List;

public class RecommendationService {
    public List<String> getSmartInsights(int userId) {
        List<String> insights = new ArrayList<>();
        // Mock insights based on the requirement
        insights.add("Your strongest topic is Arrays.");
        insights.add("Graphs need more practice.");
        insights.add("You have not revised DBMS recently.");
        insights.add("You are 82% interview ready.");
        return insights;
    }
}
