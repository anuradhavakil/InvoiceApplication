package com.example.demo;

import com.example.demo.DAO.InvoiceDAO;
import com.example.demo.DAO.InvoiceLineItemDAO;
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
 */

@RestController
public class InvoiceController {

    private Invoice invoiceId;

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<?> getLineItem(@PathVariable("invoiceId") Integer invoiceId) throws SQLException {
        InvoiceDAO invoiceDAO = new InvoiceDAO();
        InvoiceLineItemDAO invoiceLineItemDAO = new InvoiceLineItemDAO();
        Invoice invoice = invoiceDAO.getInvoiceByInvoiceId(invoiceId);
        ArrayList<InvoiceLineItem> invoiceLineItem = invoiceLineItemDAO.getAllInvoiceLineItem(invoiceId);

        return ResponseEntity.ok(invoiceLineItem);
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> addLineItem( @RequestBody InvoiceObject invoiceObject){

        InvoiceResponse result = new InvoiceResponse();
        InvoiceService invoiceService = new InvoiceService();
        boolean createInvoiceResponse = invoiceService.createInvoice(invoiceObject);
        if( createInvoiceResponse == false){
            result.setMsg("Customer does not exists");
            return ResponseEntity.status(400).body(result);
        }
        System.out.println(invoiceService.getInvoiceObject());
        return ResponseEntity.status(201).body(invoiceService.updatedInvoiceObject);
    }




}
