package com.example.demo.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by avakil on 8/24/17.
 */
public class DatabaseManager {
    private static final String DB_CONFIG_FILE = "localDb.properties";
    private static final String DB_NAME = "financialsDB";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    // create query for OS table
  //  private static final String CREATE_TABLE_CUSTOMER = "CREATE TABLE OperatingSystemDataTable ( operating_system_id VARCHAR (64),operating_system VARCHAR(64),PRIMARY KEY (operating_system_id))";

    private static String hostname;
    private static String dbAddress;
    private static String userPass;
    private static String userName;
    private static String password;
    private PreparedStatement preStatement;
    private static Statement statement;
    private ResultSet result;


    public static void main(String[] args) {
        DatabaseManager instance = DatabaseManager.getInstance();
    }


    private DatabaseManager() {
        initConfig();
        statement = setConnection();
    }

    private void initConfig(){
        Properties dbProp = new Properties();
        try {
            dbProp.load(getClass().getClassLoader().getResourceAsStream(DB_CONFIG_FILE));
        } catch (IOException e) {
            throw new RuntimeException("Error:Unable to load the db config"+e.getMessage());
        }
        hostname = dbProp.getProperty("hostname");
        userName = dbProp.getProperty("userName");
        password = dbProp.getProperty("password");
        dbAddress = "jdbc:mysql://"+hostname+"/";
        userPass = "?user=root&password=";
    }

    private Statement setConnection(){
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error initializing JDBC driver");
        }
        try {
            Connection connection = DriverManager.getConnection(dbAddress + DB_NAME, userName, password);
            connection.setAutoCommit(true);
            return connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating DB connection"+ e.getMessage());
        }
    }

    private static class DBManagerInstanceHolder {
        private final static DatabaseManager singletonInstance = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return DBManagerInstanceHolder.singletonInstance;
    }





}
