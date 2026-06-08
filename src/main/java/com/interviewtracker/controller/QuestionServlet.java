package com.interviewtracker.controller;

import com.google.gson.Gson;
import com.interviewtracker.dao.QuestionDAO;
import com.interviewtracker.daoimpl.QuestionDAOImpl;
import com.interviewtracker.model.Question;
import com.interviewtracker.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.sql.Date;

@WebServlet("/QuestionServlet")
public class QuestionServlet extends HttpServlet {
    private QuestionDAO questionDAO = new QuestionDAOImpl();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) return;
        User user = (User) session.getAttribute("user");

        String action = request.getParameter("action");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        if ("list".equals(action)) {
            List<Question> questions = questionDAO.getAllQuestionsByUser(user.getUserId());
            out.print(gson.toJson(questions));
        } else if ("filter".equals(action)) {
            String topic = request.getParameter("topic");
            String difficulty = request.getParameter("difficulty");
            String status = request.getParameter("status");
            String platform = request.getParameter("platform");
            List<Question> filtered = questionDAO.getQuestionsByFilter(user.getUserId(), topic, difficulty, status, platform);
            out.print(gson.toJson(filtered));
        }
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
            Question q = new Question();
            q.setUserId(user.getUserId());
            q.setTitle(request.getParameter("title"));
            q.setPlatform(request.getParameter("platform"));
            q.setTopic(request.getParameter("topic"));
            q.setDifficulty(request.getParameter("difficulty"));
            q.setStatus("To Do");
            
            boolean success = questionDAO.addQuestion(q);
            out.print("{\"success\":" + success + "}");
        } else if ("markSolved".equals(action)) {
            int qId = Integer.parseInt(request.getParameter("questionId"));
            boolean success = questionDAO.markAsSolved(qId);
            out.print("{\"success\":" + success + "}");
        } else if ("delete".equals(action)) {
            int qId = Integer.parseInt(request.getParameter("questionId"));
            boolean success = questionDAO.deleteQuestion(qId);
            out.print("{\"success\":" + success + "}");
        }
        out.flush();
    }
}
