package com.tss.repository;

import com.tss.config.DBConnection;
import com.tss.entity.Address;
import com.tss.entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressRepositoryImpl implements AddressRepository{
    private Connection connection = DBConnection.connect();
    private List<Address> list;
    @Override
    public List<Address> readAddresses() {
        try{
            list = new ArrayList<>();

            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from address");
            while(resultset.next()){
                list.add(new Address(resultset.getInt("address_id"),
                        resultset.getString("city"),
                        resultset.getString("state"),
                        resultset.getString("pinCode"),
                        resultset.getInt("student_id")
                ));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void addNewAddress(Address address) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into address(address_id,city,state,pinCode,student_id) values (?,?,?,?,?)");
            statement.setInt(1,address.getAddressId());
            statement.setString(2,address.getCity());
            statement.setString(3,address.getState());
            statement.setString(4,address.getPinCode());
            statement.setInt(5,address.getStudent_id());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
