package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
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
    public List<Question> getAll() {
        String SQL = "SELECT * FROM questions";
        List<Question> questions = new ArrayList<>();
        try (Connection conn = sqlConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                questions.add(new Question(rs.getInt("question_id"), rs.getString("question_title"),
                        rs.getString("question_text"),
                        rs.getTimestamp("created_at").toLocalDateTime()));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return questions;
    }

    @Override
    public Question getQuestionById(int id) {
        String SQL = "SELECT * FROM questions WHERE question_id = ?";
        Question question = null;
        try (Connection conn = sqlConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    question = new Question(rs.getInt("question_id"), rs.getString("question_title"),
                            rs.getString("question_text"),
                            rs.getTimestamp("created_at").toLocalDateTime());
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return question;
    }


    @Override
    public int addNewQuestion(NewQuestionDTO newQuestion) {
        String SQL = "INSERT INTO questions (question_title, question_text, created_at)" +
                "VALUES(?,?,?)";
        int id = 0;

        try (Connection conn = sqlConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, newQuestion.title());
            pstmt.setString(2, newQuestion.text());
            pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));

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
    public boolean deleteQuestionByID(int id) {
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


}

