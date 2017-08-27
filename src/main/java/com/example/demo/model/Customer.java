package com.example.demo.model;

/**
 * Created by avakil on 8/24/17.
 */
public class Customer {
    public String name;
    public String email;
    public int customerId;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setCustomerId(int customerId ){
        this.customerId = customerId;
    }

    public int getCustomerId(){
        return customerId;
    }

}
