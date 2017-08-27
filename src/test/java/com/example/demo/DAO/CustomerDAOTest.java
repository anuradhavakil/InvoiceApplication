package com.example.demo.DAO;

import com.example.demo.model.Customer;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by avakil on 8/24/17.
 */
public class CustomerDAOTest {

    @Test
    public void insertCustomerTest() throws SQLException{

            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = new Customer();
            customer.setName("Riti");
            customer.setEmail("riti@gmail.com");
            customerDAO.insertCustomer(customer.getName(),customer.getEmail());

    }

    @Test
    public void getCustomerDataByNameTest() throws SQLException{

            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = new Customer();
            customer.setName("Riti");
            Assert.assertEquals("Riti",customerDAO.getCustomerByName(customer.getName()).getName());
    }
}
