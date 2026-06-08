package com.interviewtracker.daoimpl;

import com.interviewtracker.dao.QuestionDAO;
import com.interviewtracker.model.Question;
import com.interviewtracker.utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAOImpl implements QuestionDAO {

    @Override
    public boolean addQuestion(Question question) {
        String query = "INSERT INTO questions (user_id, title, platform, topic, difficulty, status, notes, date_solved) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            bindQuestion(stmt, question);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateQuestion(Question question) {
        String query = "UPDATE questions SET title = ?, platform = ?, topic = ?, difficulty = ?, status = ?, notes = ?, " +
                "date_solved = ? WHERE question_id = ? AND user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, question.getTitle());
            stmt.setString(2, question.getPlatform());
            stmt.setString(3, question.getTopic());
            stmt.setString(4, question.getDifficulty());
            stmt.setString(5, question.getStatus());
            stmt.setString(6, question.getNotes());
            if (question.getDateSolved() == null) {
                stmt.setNull(7, Types.DATE);
            } else {
                stmt.setDate(7, question.getDateSolved());
            }
            stmt.setInt(8, question.getQuestionId());
            stmt.setInt(9, question.getUserId());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteQuestion(int questionId) {
        String query = "DELETE FROM questions WHERE question_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, questionId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Question getQuestionById(int questionId) {
        String query = "SELECT * FROM questions WHERE question_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, questionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapQuestion(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Question> getAllQuestionsByUser(int userId) {
        String query = "SELECT * FROM questions WHERE user_id = ? ORDER BY created_at DESC";
        List<Question> questions = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    questions.add(mapQuestion(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }

    @Override
    public List<Question> getQuestionsByFilter(int userId, String topic, String difficulty, String status, String platform) {
        StringBuilder query = new StringBuilder("SELECT * FROM questions WHERE user_id = ?");
        List<String> params = new ArrayList<>();

        addFilter(query, params, "topic", topic);
        addFilter(query, params, "difficulty", difficulty);
        addFilter(query, params, "status", status);
        addFilter(query, params, "platform", platform);
        query.append(" ORDER BY created_at DESC");

        List<Question> questions = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {
            stmt.setInt(1, userId);
            for (int i = 0; i < params.size(); i++) {
                stmt.setString(i + 2, params.get(i));
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    questions.add(mapQuestion(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }

    @Override
    public boolean markAsSolved(int questionId) {
        String query = "UPDATE questions SET status = 'Solved', date_solved = CURRENT_DATE WHERE question_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, questionId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void bindQuestion(PreparedStatement stmt, Question question) throws Exception {
        stmt.setInt(1, question.getUserId());
        stmt.setString(2, question.getTitle());
        stmt.setString(3, question.getPlatform());
        stmt.setString(4, question.getTopic());
        stmt.setString(5, question.getDifficulty());
        stmt.setString(6, question.getStatus());
        stmt.setString(7, question.getNotes());
        if (question.getDateSolved() == null) {
            stmt.setNull(8, Types.DATE);
        } else {
            stmt.setDate(8, question.getDateSolved());
        }
    }

    private void addFilter(StringBuilder query, List<String> params, String column, String value) {
        if (value != null && !value.isBlank() && !"all".equalsIgnoreCase(value)) {
            query.append(" AND ").append(column).append(" = ?");
            params.add(value);
        }
    }

    private Question mapQuestion(ResultSet rs) throws Exception {
        Question question = new Question();
        question.setQuestionId(rs.getInt("question_id"));
        question.setUserId(rs.getInt("user_id"));
        question.setTitle(rs.getString("title"));
        question.setPlatform(rs.getString("platform"));
        question.setTopic(rs.getString("topic"));
        question.setDifficulty(rs.getString("difficulty"));
        question.setStatus(rs.getString("status"));
        question.setNotes(rs.getString("notes"));
        question.setDateSolved(rs.getDate("date_solved"));
        question.setCreatedAt(rs.getTimestamp("created_at"));
        return question;
    }
}
