package com.example.demo.dao;

import com.example.demo.model.Customer;

/**
 * Created by avakil on 8/27/17.
 */
public interface CustomerDAO {

    public void insertCustomer(String name,String email);
    public Customer getCustomerByName(String name);
    public Customer getCustomerIdByEmail(String email);


}
