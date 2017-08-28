package com.example.demo.dao;

import com.example.demo.model.Invoice;

import java.sql.Date;
import java.util.List;

/**
 * Created by avakil on 8/27/17.
 */
public interface InvoiceDAO {
 //   public void setConnection();
    public void insertInvoice(Date dueDate, int CustomerId);
    public Invoice getInvoiceByCustId(int id);
    public Invoice getInvoiceByInvoiceId(int id);
    public List<Invoice> getAllInvoices();
}
