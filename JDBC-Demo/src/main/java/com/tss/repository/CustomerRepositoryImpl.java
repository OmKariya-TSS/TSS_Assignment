package com.tss.repository;

import com.tss.config.DBConnection;
import com.tss.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository{
    private Connection connection = DBConnection.connect();
    private List<Customer> list;
    @Override
    public List<Customer> readCustomers() {
        try{
            list = new ArrayList<>();

            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from customers");
            while(resultset.next()){
                list.add(new Customer(
                        resultset.getInt("customer_id"),
                        resultset.getString("name"),
                        resultset.getString("email"),
                        resultset.getString("password"),
                        resultset.getString("phone"),
                        resultset.getString("address")
                )) ;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void addNewCustomer(Customer customer) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into customers (customer_id,name,phone,email,password,address) values (?,?,?,?,?,?)");
            statement.setInt(1,customer.getCustomer_id());
            statement.setString(2,customer.getName());
            statement.setString(3,customer.getPhone());
            statement.setString(4,customer.getEmail());
            statement.setString(5,customer.getPassword());
            statement.setString(6,customer.getAddress());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
