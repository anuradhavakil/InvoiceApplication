package com.example.demo.dao.mysql;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by avakil on 8/24/17.
 *
 * This class has implemented the methods from Interface - CustomerDAO.
 * It handles the insert into/ read from db - table: Customer
 */
public class CustomerDAOImpl implements CustomerDAO {

    public static Logger logger = LoggerFactory.getLogger(CustomerDAOImpl.class);

    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    Connection connection = null;
    int CustomerID;
    DBManager dbManager;


    public CustomerDAOImpl() throws SQLException {
       dbManager = new DBManager();
        connection = dbManager.setConnection();
    }

    //Insert data
    public void insertCustomer(String name, String email){
        String insertCustomerQuery = "insert into Customer(Name,Email) values (?,?)";

        try {
            preparedStatement = connection.prepareStatement(insertCustomerQuery);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
           throw new RuntimeException("Exception inserting Customer"+e);
        }
        logger.info("Customer Data inserted");
    }

    //read the data
    public Customer getCustomerByName(String name){
        String selectCustomerByName = "Select * from financialsDB.Customer where Name = (?)";
        Customer customer = new Customer();
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
            throw new RuntimeException("Exception fetching customer by name"+ e);
        }
        return customer;
    }


    public Customer getCustomerIdByEmail(String email) {

        String selectCustomerByName = "Select * from financialsDB.Customer where Email = (?)";
        Customer customer = new Customer();

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
            throw new RuntimeException("Exception fetching customer by email"+ e);
        }
        return customer;
    }
}
