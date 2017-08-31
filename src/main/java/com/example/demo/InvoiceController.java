package com.example.demo;

import com.example.demo.dao.mysql.InvoiceDAOImpl;
import com.example.demo.dao.InvoiceLineItemDAO;
import com.example.demo.dao.mysql.InvoiceLineItemDAOImpl;
import com.example.demo.model.Invoice;
import com.example.demo.model.InvoiceLineItem;
import com.example.demo.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by avakil on 8/22/17.
 * This class handles the incoming POST/GET request.
 *
 * Incase of POST it calls InvoiceService to handle the business logic from the view.
 * If the customer exists and the invoice creation is successful it returns the invoiceID.
 *
 * Incase customer does not exists it lets the user know that customer does not exists
 *
 * All the invoices created successfully can display the line items attached to it through GET call
 */

@RestController
public class InvoiceController {

    private Invoice invoiceId;

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<?> getLineItem(@PathVariable("invoiceId") Integer invoiceId) throws SQLException {
        InvoiceDAOImpl invoiceDAO = new InvoiceDAOImpl();
        InvoiceLineItemDAO invoiceLineItemDAO = new InvoiceLineItemDAOImpl();
        Invoice invoice = invoiceDAO.getInvoiceByInvoiceId(invoiceId);
        ArrayList<InvoiceLineItem> invoiceLineItem = invoiceLineItemDAO.getAllInvoiceLineItem(invoiceId);

        return ResponseEntity.ok(invoiceLineItem);
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> addLineItem( @RequestBody InvoiceObject invoiceObject) throws SQLException {

        InvoiceResponse result = new InvoiceResponse();
        InvoiceService invoiceService = new InvoiceService();
        String createInvoiceResponse = invoiceService.createInvoice(invoiceObject);
        if( createInvoiceResponse.equals("Customer does not exists")){
            result.setMsg("Customer does not exists");
            return ResponseEntity.status(400).body(result);
        }else if( createInvoiceResponse.equals("Invoice line item is null")){
            result.setMsg("Invoice line item is null Or amount is null");
            return ResponseEntity.status(400).body(result);
        }else {
            System.out.println(invoiceService.getInvoiceObject());
            return ResponseEntity.status(201).body(invoiceService.getInvoiceObject());
        }
    }




}
