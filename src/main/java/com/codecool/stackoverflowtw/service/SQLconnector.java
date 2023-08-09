package com.codecool.stackoverflowtw.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLconnector {

    private final String url = "jdbc:postgresql://localhost:5432/stackoverflow-twproject";
    private final String user = "postgres";
    private final String password = "372969";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

}
