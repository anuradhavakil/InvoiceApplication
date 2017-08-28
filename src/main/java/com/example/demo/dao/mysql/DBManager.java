package com.example.demo.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by avakil on 8/27/17.
 */
public class DBManager {

    private String user = "root";
    private String password = "password123";
    private String dburl = "jdbc:mysql://localhost:3306/financialsDB";
    Connection connection = null;
    public static boolean setConnection = false;

    public Connection setConnection() {
        try {
            connection = DriverManager.getConnection(dburl, user, password);
            connection.setAutoCommit(false);
            setConnection = true;
        } catch (SQLException e) {
            throw new RuntimeException("Exception initializing DB connection:" + e);
        }
        return connection;
    }

    public boolean isSetConnection(){
        return setConnection;
    }

}
