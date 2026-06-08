package com.interviewtracker.dao;

import com.interviewtracker.model.Question;
import java.util.List;

public interface QuestionDAO {
    boolean addQuestion(Question question);
    boolean updateQuestion(Question question);
    boolean deleteQuestion(int questionId);
    Question getQuestionById(int questionId);
    List<Question> getAllQuestionsByUser(int userId);
    List<Question> getQuestionsByFilter(int userId, String topic, String difficulty, String status, String platform);
    boolean markAsSolved(int questionId);
}
