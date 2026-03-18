package com.tss.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection connection;
    private DBConnection(){}

    public static Connection connect(){
        try {
            if (connection == null) {
                Properties props = new Properties();
                InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
                props.load(input);
                Class.forName(props.getProperty("db.driver"));
                connection = DriverManager.getConnection(props.getProperty("db.url"),props.getProperty("db.username"),props.getProperty("db.password"));
                System.out.println("connection established successfully");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
