package com.example.demo.DAO;

import com.example.demo.model.Invoice;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by avakil on 8/24/17.
 */
public class InvoiceDAOTest {

    @Test
    public void insertInvoiceTest() throws SQLException {
        InvoiceDAO invoiceDAO = new InvoiceDAO();
        Invoice invoice = new Invoice();
        String dateString = String.format("%d-%02d-%02d", 2017, 06, 01);
        invoice.setDueDate(java.sql.Date.valueOf(dateString));
        invoiceDAO.insertInvoice(java.sql.Date.valueOf(invoice.getDueDate().toString()), 1);
    }

    @Test
    public void getInvoiceByCustIdTest() throws SQLException {
        InvoiceDAO invoiceDAO = new InvoiceDAO();
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoiceDAO.getInvoiceByCustId(invoice.getInvoiceId());
    }
}
