package com.interviewtracker.controller;

import com.google.gson.Gson;
import com.interviewtracker.model.Analytics;
import com.interviewtracker.service.AnalyticsService;
import com.interviewtracker.service.BadgeService;
import com.interviewtracker.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@WebServlet("/DashboardDataServlet")
public class DashboardDataServlet extends HttpServlet {
    private AnalyticsService analyticsService = new AnalyticsService();
    private BadgeService badgeService = new BadgeService();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) return;
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        Analytics analytics = analyticsService.getDashboardAnalytics(userId);
        int readinessScore = analyticsService.calculateReadinessScore(userId);
        Map<String, Integer> weakTopics = analyticsService.getWeakTopics(userId);
        List<String> badges = badgeService.getUserBadges(userId);

        Map<String, Object> data = new HashMap<>();
        data.put("analytics", analytics);
        data.put("readinessScore", readinessScore);
        data.put("weakTopics", weakTopics);
        data.put("badges", badges);

        out.print(gson.toJson(data));
        out.flush();
    }
}
