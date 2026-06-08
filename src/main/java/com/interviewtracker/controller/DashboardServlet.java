package com.interviewtracker.controller;

import com.interviewtracker.model.Analytics;
import com.interviewtracker.service.AnalyticsService;
import com.interviewtracker.service.RecommendationService;
import com.interviewtracker.dao.ActivityDAO;
import com.interviewtracker.daoimpl.ActivityDAOImpl;
import com.interviewtracker.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/dashboard/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    private AnalyticsService analyticsService = new AnalyticsService();
    private RecommendationService recommendationService = new RecommendationService();
    private ActivityDAO activityDAO = new ActivityDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        Analytics analytics = analyticsService.getDashboardAnalytics(userId);
        int readinessScore = analyticsService.calculateReadinessScore(userId);
        
        request.setAttribute("analytics", analytics);
        request.setAttribute("readinessScore", readinessScore);
        request.setAttribute("insights", recommendationService.getSmartInsights(userId));
        request.setAttribute("activities", activityDAO.getRecentActivitiesByUser(userId, 5));
        
        request.getRequestDispatcher("/dashboard/dashboard.jsp").forward(request, response);
    }
}
