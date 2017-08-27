package com.example.demo.model;

import java.sql.Date;

/**
 * Created by avakil on 8/24/17.
 */
public class Invoice {

    private Date dueDate;
    private int invoiceId;
    private int customerId;



    public void setDueDate(Date dueDate){
        this.dueDate = dueDate;
    }

    public Date getDueDate(){
        return dueDate;
    }

    public void setInvoiceId(int id){
        this.invoiceId = id;
    }

    public int getInvoiceId(){
        return invoiceId;
    }


    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
