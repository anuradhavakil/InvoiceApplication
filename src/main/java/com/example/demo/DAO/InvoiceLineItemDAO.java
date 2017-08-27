package com.example.demo.DAO;

import com.example.demo.model.InvoiceLineItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by avakil on 8/24/17.
 */
public class InvoiceLineItemDAO {

    public static Logger logger = LoggerFactory.getLogger(InvoiceLineItemDAO.class);

    //   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/financialsDB","root","password123");
    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    Connection connection = null;
    boolean setConnection = false;


    public InvoiceLineItemDAO() throws SQLException {}

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
    public void insertInvoiceLineItem(String itemDescription, double amount,int invoiceId){
        String insertInvoiceLineItemQuery = "insert into InvoiceLineItem(ItemDescription,Amount,InvoiceId) values (?,?,?)";

        try {
            setConnection();
            preparedStatement = connection.prepareStatement(insertInvoiceLineItemQuery);
            preparedStatement.setString(1, itemDescription);
            preparedStatement.setDouble(2, amount);
            preparedStatement.setInt(3,invoiceId);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        logger.info("Invoice Line Item Data inserted");
    }

    //read data by invoiceid
    public InvoiceLineItem getInvoiceLineItem(int invoiceId){
        String selectInvoiceLineItem = "select * from InvoiceLineItem where InvoiceId = (?)";
     //   ArrayList<InvoiceLineItem> invoiceLineItems = new ArrayList<>();
        InvoiceLineItem invoiceLineItem = new InvoiceLineItem();
        try {
            setConnection();
            preparedStatement = connection.prepareStatement(selectInvoiceLineItem);
            preparedStatement.setInt(1,invoiceId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                invoiceLineItem.setInvoiceLineItemId(rs.getInt("InvoiceLineItemId"));
                invoiceLineItem.setInvoiceId(rs.getInt("InvoiceId"));
                invoiceLineItem.setItemDescription(rs.getString("ItemDescription"));
                invoiceLineItem.setAmount(rs.getDouble("Amount"));
              //  invoiceLineItems.add(invoiceLineItem);
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        logger.info("InvoiceLineItem read from InvoiceLineItem Table");

        return invoiceLineItem;
    }

    //get all invoice line items for particular invoice id
    public ArrayList<InvoiceLineItem> getAllInvoiceLineItem(int invoiceId){
        String selectInvoiceLineItem = "select * from InvoiceLineItem where InvoiceId = (?)";
           ArrayList<InvoiceLineItem> invoiceLineItems = new ArrayList<>();
        InvoiceLineItem invoiceLineItem = new InvoiceLineItem();
        try {
            setConnection();
            preparedStatement = connection.prepareStatement(selectInvoiceLineItem);
            preparedStatement.setInt(1,invoiceId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                invoiceLineItem.setInvoiceLineItemId(rs.getInt("InvoiceLineItemId"));
                invoiceLineItem.setInvoiceId(rs.getInt("InvoiceId"));
                invoiceLineItem.setItemDescription(rs.getString("ItemDescription"));
                invoiceLineItem.setAmount(rs.getDouble("Amount"));
                  invoiceLineItems.add(invoiceLineItem);
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        logger.info("InvoiceLineItem read from InvoiceLineItem Table");

        return invoiceLineItems;
    }


}
