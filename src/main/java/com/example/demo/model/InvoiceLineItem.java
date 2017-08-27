package com.example.demo.model;

/**
 * Created by avakil on 8/24/17.
 */
public class InvoiceLineItem {

    private String itemDescription;
    private double amount;
    private int invoiceLineItemId;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    private int invoiceId;

    public int getInvoiceLineItemId() {
        return invoiceLineItemId;
    }

    public void setInvoiceLineItemId(int invoiceLineItemId) {
        this.invoiceLineItemId = invoiceLineItemId;
    }

    public void setItemDescription(String description){
        itemDescription = description;
    }

    public String getItemDescription(){
        return itemDescription;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public double getAmount(){
        return amount;
    }
}
