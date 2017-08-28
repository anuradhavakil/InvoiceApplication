package com.example.demo.dao;

import com.example.demo.dao.mysql.InvoiceLineItemDAOImpl;
import com.example.demo.model.InvoiceLineItem;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by avakil on 8/24/17.
 */
public class InvoiceLineItemDAOTest {

    @Test
    public void insertInvoiceTest() throws SQLException {
        InvoiceLineItemDAO invoiceLineItemDAO = new InvoiceLineItemDAOImpl();
        InvoiceLineItem invoiceLineItem  = new InvoiceLineItem();
        invoiceLineItem.setItemDescription("Plastic");
        invoiceLineItem.setAmount(20.0);
        invoiceLineItemDAO.insertInvoiceLineItem(invoiceLineItem.getItemDescription(), invoiceLineItem.getAmount(), 74);
        InvoiceLineItem invoiceLineItem1 = invoiceLineItemDAO.getInvoiceLineItem(74);
        Assert.assertEquals(invoiceLineItem.getAmount(),invoiceLineItem1.getAmount(),0);
    }
}
