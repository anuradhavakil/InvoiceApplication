package com.example.demo;


import com.example.demo.model.InvoiceLineItem;

import java.util.ArrayList;
import java.sql.Date;

/**
 * Created by avakil on 8/23/17.
 */
public class InvoiceObject {
    private String name;
    private String email;
    private Date dueDate;
    private double amount;
    private int invoiceId;
    private int invoiceLineItemId;
    private ArrayList<InvoiceLineItem> invoiceLineItems;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getInvoiceLineItemId() {
        return invoiceLineItemId;
    }

    public void setInvoiceLineItemId(int invoiceLineItemId) {
        this.invoiceLineItemId = invoiceLineItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public ArrayList<InvoiceLineItem> getInvoiceLineItems() {
        return invoiceLineItems;
    }

    public void setInvoiceLineItems(ArrayList<InvoiceLineItem> invoiceLineItems) {
        this.invoiceLineItems = invoiceLineItems;
    }

    public double getTotalAmount() {
        return amount;
    }

    public void setTotalAmount(double totalAmount) {
        this.amount = totalAmount;
    }



}
