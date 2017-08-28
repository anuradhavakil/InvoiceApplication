package com.example.demo.dao;

import com.example.demo.model.InvoiceLineItem;

import java.util.ArrayList;

/**
 * Created by avakil on 8/27/17.
 */
public interface InvoiceLineItemDAO {

    public void insertInvoiceLineItem(String itemDescription, double amount,int invoiceId);
    public InvoiceLineItem getInvoiceLineItem(int invoiceId);
    public ArrayList<InvoiceLineItem> getAllInvoiceLineItem(int invoiceId);
}
