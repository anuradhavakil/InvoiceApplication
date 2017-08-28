package com.example.demo.dao;

import com.example.demo.dao.mysql.CustomerDAOImpl;
import com.example.demo.model.Customer;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by avakil on 8/24/17.
 */
public class CustomerDAOTest {


    @Test
    public void getCustomerDataByNameTest() throws SQLException{

            CustomerDAOImpl customerDAO = new CustomerDAOImpl();
            Customer customer = new Customer();
            customer.setName("Riti");
            Assert.assertEquals("Riti",customerDAO.getCustomerByName(customer.getName()).getName());
    }
}
