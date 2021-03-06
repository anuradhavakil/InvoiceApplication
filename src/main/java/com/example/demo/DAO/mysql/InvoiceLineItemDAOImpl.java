package com.example.demo.dao.mysql;

import com.example.demo.dao.InvoiceLineItemDAO;
import com.example.demo.model.InvoiceLineItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by avakil on 8/24/17.
 * This class has implemented the methods from Interface - InvoiceLineItemDAO.
 * It handles the insert into/ read from db - table: InvoiceLineItem
 */
public class InvoiceLineItemDAOImpl implements InvoiceLineItemDAO {

    public static Logger logger = LoggerFactory.getLogger(InvoiceLineItemDAO.class);

    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    Connection connection = null;


    public InvoiceLineItemDAOImpl()  {
        DBManager dbManager = new DBManager();
        connection = dbManager.setConnection();
    }

    //Insert data
    public void insertInvoiceLineItem(String itemDescription, double amount,int invoiceId){
        String insertInvoiceLineItemQuery = "insert into InvoiceLineItem(ItemDescription,Amount,InvoiceId) values (?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(insertInvoiceLineItemQuery);
            preparedStatement.setString(1, itemDescription);
            preparedStatement.setDouble(2, amount);
            preparedStatement.setInt(3,invoiceId);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException("Exception inserting new invoice line item:"+e.getMessage());

        }
        logger.info("Invoice Line Item Data inserted");
    }

    //read data by invoiceid
    public InvoiceLineItem getInvoiceLineItem(int invoiceId){
        String selectInvoiceLineItem = "select * from InvoiceLineItem where InvoiceId = (?)";
       InvoiceLineItem invoiceLineItem = new InvoiceLineItem();
        try {
            preparedStatement = connection.prepareStatement(selectInvoiceLineItem);
            preparedStatement.setInt(1,invoiceId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                invoiceLineItem.setInvoiceLineItemId(rs.getInt("InvoiceLineItemId"));
                invoiceLineItem.setInvoiceId(rs.getInt("InvoiceId"));
                invoiceLineItem.setItemDescription(rs.getString("ItemDescription"));
                invoiceLineItem.setAmount(rs.getDouble("Amount"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Exception reading invoice line item:"+e);

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
            throw new RuntimeException("Exception reading all invoice line items:"+e);
        }
        logger.info("InvoiceLineItems read from InvoiceLineItem Table");

        return invoiceLineItems;
    }

}
