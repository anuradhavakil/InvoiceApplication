package com.example.demo.DAO;

import com.example.demo.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by avakil on 8/24/17.
 */
public class CustomerDAO {

    public static Logger logger = LoggerFactory.getLogger(CustomerDAO.class);

 //   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/financialsDB","root","password123");
    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    Connection connection = null;
    boolean setConnection = false;
    int CustomerID;


    public CustomerDAO() throws SQLException {
    //    DatabaseManager instance = DatabaseManager.getInstance();
    }
      //making connection
    public void setConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/financialsDB","root","password123") ;
            connection.setAutoCommit(false);
            setConnection = true;
        } catch (SQLException e) {
            e.getMessage();
        }

    }

    //Insert data
    public void insertCustomer(String name, String email){
        String insertCustomerQuery = "insert into Customer(Name,Email) values (?,?)";

        try {
            setConnection();
            preparedStatement = connection.prepareStatement(insertCustomerQuery);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.executeUpdate();
            connection.commit();


        } catch (SQLException e) {
            e.getMessage();
        }
        logger.info("Customer Data inserted");
    }

    //read the data
    public Customer getCustomerByName(String name){
        String selectCustomerByName = "Select * from financialsDB.Customer where Name = (?)";
         int result = 0;
        Customer customer = new Customer();

        if ( setConnection == false){
            setConnection();
        }

        try {
            preparedStatement = connection.prepareStatement(selectCustomerByName);
            preparedStatement.setString(1,name);
          ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                String r = rs.getString(1);
                customer.setCustomerId(rs.getInt(3));
                CustomerID = rs.getInt("CustomerId");
                logger.info("Result of getCustomerByName is " + r);
                customer.setName(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }


    public Customer getCustomerIdByEmail(String email) {

        String selectCustomerByName = "Select * from financialsDB.Customer where Email = (?)";
        int result = 0;
        Customer customer = new Customer();

        if ( setConnection == false){
            setConnection();
        }

        try {
            preparedStatement = connection.prepareStatement(selectCustomerByName);
            preparedStatement.setString(1,email);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {

                customer.setName(rs.getString("Name"));
                customer.setEmail(rs.getString("Email"));
                customer.setCustomerId(rs.getInt("CustomerId"));
                CustomerID = rs.getInt("CustomerId");
                logger.info("Result of getCustomerByEmail is " + customer.getName() +"\t"+ customer.getEmail() +"\t" +customer.getCustomerId());

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return customer;
    }
}
