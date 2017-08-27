package com.example.demo.DAO;

import com.example.demo.model.Invoice;
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
public class InvoiceDAO {

    public static Logger logger = LoggerFactory.getLogger(InvoiceDAO.class);

    //   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/financialsDB","root","password123");
    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    Connection connection = null;
    boolean setConnection = false;


    public InvoiceDAO() throws SQLException {
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
    public void insertInvoice(java.sql.Date dueDate, int customerId){
        String insertCustomerQuery = "insert into Invoice(DueDate,CustomerId) values (?,?)";

        try {
            setConnection();
            preparedStatement = connection.prepareStatement(insertCustomerQuery);
            preparedStatement.setDate(1, dueDate);
            preparedStatement.setInt(2,customerId);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.getMessage();
        }
        logger.info("Customer Data inserted");
    }

    //read the data
    public Invoice getInvoiceByCustId(int id){
        String selectInvoicebycustid = "Select * from financialsDB.Invoice where CustomerId = (?)";
        int result = 0;
        Invoice invoice = new Invoice();

        if ( setConnection == false){
            setConnection();
        }

        try {
            preparedStatement = connection.prepareStatement(selectInvoicebycustid);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                invoice.setInvoiceId(rs.getInt("InvoiceId"));
                invoice.setDueDate(rs.getDate("DueDate"));
                invoice.setCustomerId(rs.getInt("CustomerId"));
                logger.info("Result of getInvoiceByCustId is: InvoiceId - " + invoice.getInvoiceId());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return invoice;
    }

    public Invoice getInvoiceByInvoiceId(int id){
        String selectInvoicebycustid = "Select * from financialsDB.Invoice where InvoiceId = (?)";
        int result = 0;
        Invoice invoice = new Invoice();

        if ( setConnection == false){
            setConnection();
        }

        try {
            preparedStatement = connection.prepareStatement(selectInvoicebycustid);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                invoice.setInvoiceId(rs.getInt("InvoiceId"));
                invoice.setDueDate(rs.getDate("DueDate"));
                invoice.setCustomerId(rs.getInt("CustomerId"));
                logger.info("Result of getInvoiceByCustId is: InvoiceId - " + invoice.getInvoiceId());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return invoice;
    }
}