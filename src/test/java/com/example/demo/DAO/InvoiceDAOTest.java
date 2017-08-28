package com.example.demo.dao;

import com.example.demo.dao.mysql.InvoiceDAOImpl;
import com.example.demo.model.Invoice;

import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by avakil on 8/24/17.
 */
public class InvoiceDAOTest {

  //  @Test
    public void insertInvoiceTest() throws SQLException {
        InvoiceDAOImpl invoiceDAO = new InvoiceDAOImpl();
        Invoice invoice = new Invoice();
        String dateString = String.format("%d-%02d-%02d", 2017, 06, 01);
        Date date = new Date(20,170,601);
        invoice.setDueDate(date);
        invoiceDAO.insertInvoice(java.sql.Date.valueOf(invoice.getDueDate().toString()), 13);
    }

}
