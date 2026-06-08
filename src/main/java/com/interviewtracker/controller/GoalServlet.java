package com.interviewtracker.controller;

import com.google.gson.Gson;
import com.interviewtracker.dao.GoalDAO;
import com.interviewtracker.daoimpl.GoalDAOImpl;
import com.interviewtracker.model.Goal;
import com.interviewtracker.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

@WebServlet("/GoalServlet")
public class GoalServlet extends HttpServlet {
    private GoalDAO goalDAO = new GoalDAOImpl();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) return;
        User user = (User) session.getAttribute("user");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        List<Goal> goals = goalDAO.getAllGoalsByUser(user.getUserId());
        out.print(gson.toJson(goals));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) return;
        User user = (User) session.getAttribute("user");

        String action = request.getParameter("action");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        if ("add".equals(action)) {
            Goal g = new Goal();
            g.setUserId(user.getUserId());
            g.setGoalTitle(request.getParameter("goalTitle"));
            g.setTargetCount(Integer.parseInt(request.getParameter("targetCount")));
            g.setDeadline(Date.valueOf(request.getParameter("deadline")));
            
            boolean success = goalDAO.addGoal(g);
            out.print("{\"success\":" + success + "}");
        }
        out.flush();
    }
}
