package com.interviewtracker.service;

import com.interviewtracker.dao.QuestionDAO;
import com.interviewtracker.daoimpl.QuestionDAOImpl;
import com.interviewtracker.model.Question;
import java.util.List;
import java.util.ArrayList;

public class BadgeService {
    private QuestionDAO questionDAO = new QuestionDAOImpl();

    public List<String> getUserBadges(int userId) {
        List<String> badges = new ArrayList<>();
        List<Question> questions = questionDAO.getAllQuestionsByUser(userId);
        
        long solvedCount = questions.stream().filter(q -> "Solved".equals(q.getStatus())).count();
        long graphCount = questions.stream().filter(q -> "Solved".equals(q.getStatus()) && "Graphs".equalsIgnoreCase(q.getTopic())).count();
        long dpCount = questions.stream().filter(q -> "Solved".equals(q.getStatus()) && "DP".equalsIgnoreCase(q.getTopic())).count();
        
        if (solvedCount >= 1) badges.add("First Question Solved");
        if (solvedCount >= 25) badges.add("25 Questions Solved");
        if (solvedCount >= 50) badges.add("50 Questions Solved");
        if (solvedCount >= 100) badges.add("100 Questions Solved");
        
        if (graphCount >= 10) badges.add("Graph Expert");
        if (dpCount >= 10) badges.add("DP Master");
        
        // Consistency King requires checking dates, simplified here
        if (solvedCount >= 10) badges.add("Consistency King");
        
        return badges;
    }
}
