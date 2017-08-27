package com.example.demo.service;

import com.example.demo.DAO.CustomerDAO;
import com.example.demo.DAO.InvoiceDAO;
import com.example.demo.DAO.InvoiceLineItemDAO;
import com.example.demo.InvoiceObject;
import com.example.demo.model.Customer;
import com.example.demo.model.Invoice;
import com.example.demo.model.InvoiceLineItem;

import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by avakil on 8/25/17.
 *
 * This class serves the purpose of creating(creating data in db) new invoice along with
 * invoice line items and returning invoice id and invoice line item ids to the controller
 */
public class InvoiceService {
    public static ArrayList<InvoiceLineItem> updatedLineItems = new ArrayList<>();
    public static InvoiceObject updatedInvoiceObject = new InvoiceObject();
    public static double totalAmount = 0.0d;

    public InvoiceService(){}

    public boolean createInvoice( InvoiceObject invoiceObject){

        String name = invoiceObject.getName();
        String email = invoiceObject.getEmail();
        Date dueDate = invoiceObject.getDueDate();
        List<InvoiceLineItem> invoiceLineItem = invoiceObject.getInvoiceLineItems();
        Customer customer = null;
        Invoice invoice = null;

        InvoiceLineItem lineItem = null;

        try {
            CustomerDAO customerDAO = new CustomerDAO();
            customer = customerDAO.getCustomerIdByEmail(email);

        //return false if customer does not exists
        if( customer.getName() == null) {
            return false;
        }
            //get invoice for this particular request
            invoice = getInvoice(dueDate, customer);

            //get all line items for this invoice id
            Iterator<InvoiceLineItem> iterator = invoiceLineItem.iterator();
            while (iterator.hasNext()){
                InvoiceLineItem lineItemNext = iterator.next();
                lineItem = getInvoiceLineItem(lineItemNext, invoice);
                totalAmount = totalAmount + lineItem.getAmount();
                updatedLineItems.add(lineItem);
            }

        } catch (SQLException e) {
                throw new RuntimeException(e.getMessage()+e);
            }
        updatedInvoiceObject.setTotalAmount(totalAmount);
        updatedInvoiceObject.setName(customer.getName());
        updatedInvoiceObject.setEmail(customer.getEmail());
        updatedInvoiceObject.setDueDate(invoice.getDueDate());
        updatedInvoiceObject.setInvoiceLineItems(updatedLineItems);
        updatedInvoiceObject.setInvoiceId(invoice.getInvoiceId());

        return true;

    }

    //return complete record of invoiceLineItem with InvoiceLineItemID and InvoiceId
    private InvoiceLineItem getInvoiceLineItem(InvoiceLineItem lineItem, Invoice invoice) {
        InvoiceLineItem invoiceLineItem = new InvoiceLineItem();

        try {
            InvoiceLineItemDAO invoiceLineItemDAO = new InvoiceLineItemDAO();
            invoiceLineItemDAO.insertInvoiceLineItem(lineItem.getItemDescription(),lineItem.getAmount(),invoice.getInvoiceId());
            invoiceLineItem = invoiceLineItemDAO.getInvoiceLineItem(invoice.getInvoiceId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invoiceLineItem;
    }

    //return complete record of invoice - with invoiceId and customerId
    private Invoice getInvoice(Date dueDate, Customer customer) {
        Invoice invoice = null;
        try {
            InvoiceDAO invoiceDAO = new InvoiceDAO();
            invoiceDAO.insertInvoice(dueDate,customer.getCustomerId());
            invoice = invoiceDAO.getInvoiceByCustId(customer.getCustomerId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }


    //get invoice object
    public InvoiceObject getInvoiceObject(){
        return updatedInvoiceObject;
    }






}
