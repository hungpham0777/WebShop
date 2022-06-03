package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import config.ConnectionSQL;
import entity.Shop;

public class OwnerShopService {
    public static void addShopToData(Shop shop, String data) {
    	try {
    		Connection connection= ConnectionSQL.getConnection();
    		PreparedStatement stm=connection.prepareStatement("Insert Into "+data+" (user,pass,nameShop,numFollower,urlAvatar) values(?,?,?,?,?)");
			stm.setString(1, shop.getUser());
			stm.setString(2, shop.getPass());
			stm.setString(3, shop.getNameShop());
			stm.setString(4, "0");
			stm.setString(5, shop.getUrlAvatar());
    		stm.executeUpdate();
			connection.close();
			stm.close();
			System.out.println("Add into database successed!");
		} catch (SQLException e) {
			System.out.println("Add into database failed!");
			e.printStackTrace();
		}
    }
    public static ArrayList<Shop> getShops(String data) {
    	try {
    		ArrayList<Shop> shopList=new ArrayList<Shop>();
    		Connection connection=ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			ResultSet rs=stm.executeQuery(String.format("Select * from %s",data));
			while (rs.next()) {
				shopList.add(new Shop(rs.getInt("shopID"), rs.getString("user"), rs.getString("pass"),
						rs.getString("nameShop"),rs.getString("numFollower"), rs.getString("urlAvatar")));
			}
			System.out.println("Get database successed!");
			connection.close();
			return shopList;
		} catch (SQLException e) {
			System.out.println("Get database failed!");
			e.printStackTrace();
		}
    	
		return null;
    }
    public static Shop getShopByID(int shopID,String data) {
    	try {
    		Shop shop=null;
    		Connection connection=ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			ResultSet rs=stm.executeQuery(String.format("Select * from %s where shopID=%d",data,shopID));
			if (rs.next()) {
				shop=new Shop(rs.getInt("shopID"), rs.getString("user"), rs.getString("pass"),
						rs.getString("nameShop"),rs.getString("numFollower"),rs.getString("urlAvatar"));
			}
			System.out.println("Get database successed!");
			connection.close();
			return shop;
		} catch (SQLException e) {
			System.out.println("Get database failed!");
			e.printStackTrace();
		}
    	
		return null;
    }
    public static Shop getAccesser(String user, String pass,String data) {
    	try {
    		Connection connection=ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			ResultSet rs=stm.executeQuery(String.format("Select * from %s where user=\"%s\"AND pass=\"%s\"",data,user,pass));
			if(rs.next()) {
				return new Shop(rs.getInt("shopID"), rs.getString("user"), rs.getString("pass"),
						rs.getString("nameShop"),rs.getString("numFollower"), rs.getString("urlAvatar"));
			}
			System.out.println("Get database successed!");
			connection.close();
		} catch (SQLException e) {
			System.out.println("Get database failed!");
			e.printStackTrace();
		}
		return null;
    	
    }
}
