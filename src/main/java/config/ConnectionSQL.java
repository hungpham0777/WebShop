package config;

import java.sql.*;
import java.util.ArrayList;
import entity.Client;
import entity.Shop;
import entity.Product;

public class ConnectionSQL {
    private static String url = "jdbc:mysql://localhost:3306/webshop";
    private static String user = "root";
    private static String password = "hung28092003";
    public static Connection getConnection() {
    	Connection connection = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	connection = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(connection==null) System.out.println("Connect failed!");
        else System.out.println("Connect successed!");
        return connection;
    }

}
