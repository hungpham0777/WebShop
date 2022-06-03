package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import config.ConnectionSQL;
import entity.Category;

public class CategoryService {
    public static String getCategory(int categoryID, String data) {
    	try {
    		String name=null;
    		Connection connection=ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			ResultSet rs=stm.executeQuery(String.format("Select (nameCategory) from %s where categoryID=%d",data,categoryID));
			if (rs.next()) {
				name= rs.getString("nameCategory");
			}
			System.out.println("Get database successed!");
			connection.close();
			return name;
		} catch (SQLException e) {
			System.out.println("Get database failed!");
			e.printStackTrace();
		}
		return null;
    }
    public static ArrayList<Category> getCategorysInData(String data) {
    	try {
    		ArrayList<Category> categoryList=new ArrayList<Category>();
    		Connection connection=ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			ResultSet rs=stm.executeQuery(String.format("Select * from %s",data));
			while (rs.next()) {
				categoryList.add(new Category(rs.getInt("categoryID"),rs.getString("nameCategory")));
			}
			System.out.println("Get database successed!");
			connection.close();
			return categoryList;
		} catch (SQLException e) {
			System.out.println("Get database failed!");
			e.printStackTrace();
		}
		return null;
    }
}
