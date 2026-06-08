package com.interviewtracker.controller;

import com.interviewtracker.model.Analytics;
import com.interviewtracker.model.User;
import com.interviewtracker.service.AnalyticsService;
import com.interviewtracker.service.BadgeService;
import com.interviewtracker.utility.PdfGenerator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/ExportReportServlet")
public class ExportReportServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ExportReportServlet.class);
    private AnalyticsService analyticsService = new AnalyticsService();
    private BadgeService badgeService = new BadgeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        User user = (User) session.getAttribute("user");
        logger.info("Generating PDF Report for user: {}", user.getEmail());

        Analytics analytics = analyticsService.getDashboardAnalytics(user.getUserId());
        int readinessScore = analyticsService.calculateReadinessScore(user.getUserId());
        Map<String, Integer> weakTopics = analyticsService.getWeakTopics(user.getUserId());
        List<String> badges = badgeService.getUserBadges(user.getUserId());

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"Interview_Tracker_Report_" + user.getName().replaceAll("\\s+", "_") + ".pdf\"");

        try {
            PdfGenerator.generateProgressReport(response.getOutputStream(), user, analytics, readinessScore, weakTopics, badges);
            logger.info("PDF Report generated successfully.");
        } catch (Exception e) {
            logger.error("Error generating PDF", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
