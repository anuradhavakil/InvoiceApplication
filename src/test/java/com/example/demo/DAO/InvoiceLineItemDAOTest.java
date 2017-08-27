package com.example.demo.DAO;

import com.example.demo.model.InvoiceLineItem;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by avakil on 8/24/17.
 */
public class InvoiceLineItemDAOTest {

    @Test
    public void insertInvoiceTest() throws SQLException {
        InvoiceLineItemDAO invoiceLineItemDAO = new InvoiceLineItemDAO();
        InvoiceLineItem invoiceLineItem  = new InvoiceLineItem();
        invoiceLineItem.setItemDescription("Plastic");
        invoiceLineItem.setAmount(20.0);

        invoiceLineItemDAO.insertInvoiceLineItem(invoiceLineItem.getItemDescription(), invoiceLineItem.getAmount(), invoiceLineItem.getInvoiceId());
    }
}
