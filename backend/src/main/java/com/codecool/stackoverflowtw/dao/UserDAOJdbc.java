package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewUserDTO;
import com.codecool.stackoverflowtw.dao.model.Question;
import com.codecool.stackoverflowtw.service.SQLconnector;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;
import java.time.LocalDateTime;

public class UserDAOJdbc implements UserDAO {
    private final SQLconnector sqlconnector;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserDAOJdbc(SQLconnector sqlconnector) {
        this.sqlconnector = sqlconnector;
    }


    @Override
    public int addNewUser(NewUserDTO newUser) {
        String SQL = "INSERT INTO users (user_username, user_password, created_at)" +
                "VALUES(?,?,?)";
        int id = 0;

        try (Connection conn = sqlconnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, newUser.username());
            pstmt.setString(2, encoder.encode(newUser.password()));
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
    public int authUser(NewUserDTO user) {
        String SQL = """
                SELECT *
                FROM users WHERE user_username = ?""";
        try (Connection conn = sqlconnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, user.username());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String encodedPassword = rs.getString("user_password");
                    if (encoder.matches(user.password(), encodedPassword)) {
                        return rs.getInt("user_id");
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }
}
