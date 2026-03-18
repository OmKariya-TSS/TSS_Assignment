package com.tss.FoodApp.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static String url;
    private static String username;
    private static String password;

    static {
        try {
            Properties props = new Properties();
            InputStream input = DBConnection.class.getClassLoader()
                    .getResourceAsStream("db.properties");
            if (input == null) {
                throw new RuntimeException("db.properties not found in classpath");
            }
            props.load(input);
            Class.forName(props.getProperty("db.driver"));
            url      = props.getProperty("db.url");
            username = props.getProperty("db.username");
            password = props.getProperty("db.password");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load DB config", e);
        }
    }

    private DBConnection() {}
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}