package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.dao.model.Question;
import com.codecool.stackoverflowtw.service.SQLconnector;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoJdbc implements QuestionsDAO {
    private final SQLconnector sqlConnector;

    public QuestionsDaoJdbc(SQLconnector sqlConnector) {
        this.sqlConnector = sqlConnector;
    }

    @Override
    public List<Question> getAll(String sortBy, String direction) {
        String orderSQL = sortBy + " " + direction + ";";
        String SQL = "SELECT q.*, COUNT(a.answer_id) AS answer_count, user_username " +
                "FROM questions q " +
                "LEFT JOIN public.answers a ON q.question_id = a.question_id " +
                "LEFT JOIN public.users u on u.user_id = q.user_id " +
                "GROUP BY q.question_id, user_username" + orderSQL;

        List<Question> questions = new ArrayList<>();
        try (Connection conn = sqlConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                questions.add(new Question(rs.getInt("question_id"), rs.getString("question_title"),
                        rs.getString("question_text"),
                        rs.getTimestamp("created_at").toLocalDateTime(), rs.getInt("answer_count"), rs.getString("user_username")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return questions;
    }

    @Override
    public Question getQuestionById(int id) {
        String SQL = """
                SELECT q.*, COUNT(a.answer_id) AS answer_count, user_username
                FROM questions q
                    LEFT JOIN public.answers a ON q.question_id = a.question_id
                    LEFT JOIN users u ON q.user_id = u.user_id
                WHERE q.question_id = ?
                GROUP BY q.question_id, user_username
                ORDER BY question_id;""";
        Question question = null;
        try (Connection conn = sqlConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    question = new Question(rs.getInt("question_id"), rs.getString("question_title"),
                            rs.getString("question_text"),
                            rs.getTimestamp("created_at").toLocalDateTime(), rs.getInt("answer_count"), rs.getString("user_username"));
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return question;
    }

    @Override
    public List<Answer> getQAnswersByQuestionId(int id) {
        String SQL = """
                SELECT a.*, user_username FROM answers a LEFT JOIN public.users u on u.user_id = a.user_id WHERE question_id = ? ORDER BY created_at ASC
                ;""";
        List<Answer> answers = new ArrayList<>();
        try (Connection conn = sqlConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    answers.add(new Answer(rs.getInt("answer_id"), rs.getString("answer_text"), rs.getTimestamp("created_at").toLocalDateTime(), rs.getString("user_username")));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return answers;
    }


    @Override
    public int addNewQuestion(NewQuestionDTO newQuestion) {
        String SQL = "INSERT INTO questions (question_title, question_text, created_at, user_id)" +
                "VALUES(?,?,?,?)";
        int id = 0;

        try (Connection conn = sqlConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, newQuestion.title());
            pstmt.setString(2, newQuestion.text());
            pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(4, Integer.parseInt(newQuestion.userID()));

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    @Override
    public int addNewAnswer(NewAnswerDTO newAnswer, int id) {
        String SQL = "INSERT INTO answers (answer_text, created_at, question_id, user_id)" +
                "VALUES(?,?,?,?)";
        int respId = 0;

        try (Connection conn = sqlConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, newAnswer.text());
            pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(3, id);
            pstmt.setInt(4, Integer.parseInt(newAnswer.userID()));

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        respId = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return respId;
    }

    @Override
    public boolean deleteQuestionById(int id) {
        String SQL = "DELETE FROM answers WHERE question_id = ?;"
                + "DELETE FROM questions WHERE question_id = ?";

        try (Connection conn = sqlConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, id);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAnswerById(int questionId, int answerId) {
        String SQL = "DELETE FROM answers WHERE answer_id = ? AND question_id = ?";

        try (Connection conn = sqlConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, answerId);
            pstmt.setInt(2, questionId);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }


}

