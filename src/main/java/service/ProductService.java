package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import config.ConnectionSQL;
import entity.Product;

public class ProductService {
    public static ArrayList<Product> getProductFromData(String data){
    	try {
    		ArrayList<Product> productList=new ArrayList<Product>();
    		Connection connection=ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			ResultSet rs=stm.executeQuery(String.format("Select * from %s",data));
			while (rs.next()) {
				productList.add(new Product(rs.getInt("productID"), rs.getString("product"), rs.getString("priceO"),
						rs.getString("priceS"), rs.getString("img"),rs.getInt("shop_shopID"),rs.getInt("category_categoryID")));
			}
			System.out.println("Get database successed!");
			connection.close();
			return productList;
		} catch (SQLException e) {
			System.out.println("Get database failed!");
			e.printStackTrace();
		}
    	
		return null;
    }
    public static ArrayList<Product> getProductsByShop(int shopID,String data){
    	try {
    		ArrayList<Product> productList=new ArrayList<Product>();
    		Connection connection=ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			ResultSet rs=stm.executeQuery(String.format("Select * from %s where shop_shopID=%d",data,shopID));
			while (rs.next()) {
				productList.add(new Product(rs.getInt("productID"), rs.getString("product"), rs.getString("priceO"),
						rs.getString("priceS"), rs.getString("img"),rs.getInt("shop_shopID"),rs.getInt("category_categoryID")));
			}
			System.out.println("Get database successed!");
			connection.close();
			return productList;
		} catch (SQLException e) {
			System.out.println("Get database failed!");
			e.printStackTrace();
		}
    	
		return null;
    }
    public static Product getProductByID(int productID,String data){
    	try {
    		Product p=null;
    		Connection connection=ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			ResultSet rs=stm.executeQuery(String.format("Select * from %s where productID=%d",data,productID));
			if (rs.next()) {
				p=new Product(rs.getInt("productID"), rs.getString("product"), rs.getString("priceO"),
						rs.getString("priceS"), rs.getString("img"),rs.getInt("shop_shopID"),rs.getInt("category_categoryID"));
			}
			System.out.println("Get database successed!");
			connection.close();
			return p;
		} catch (SQLException e) {
			System.out.println("Get database failed!");
			e.printStackTrace();
		}
    	
		return null;
    }
    public static void addProductToData(Product product, String data) {
    	try {
    		Connection connection=ConnectionSQL.getConnection();
			PreparedStatement stm=connection.prepareStatement("Insert Into "+ data+"(product,priceO,priceS,img,shop_shopID,category_categoryID) values(?,?,?,?,?,?)");
			stm.setString(1, product.getProduct());
			stm.setString(2, product.getOriginalPrice());
			stm.setString(3, product.getSalePrice());
			stm.setString(4, product.getUrl());
			stm.setInt(5, product.getShopID());
			stm.setInt(6, product.getCategoryID());
    		stm.executeUpdate();
			connection.close();
			stm.close();
			System.out.println("Add into database successed!");
		} catch (SQLException e) {
			System.out.println("Add into database failed!");
			e.printStackTrace();
		}
    }
    public static void deleteProductInData(int productID, String data) {
    	try {
    		Connection connection=ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			stm.executeUpdate(String.format("DELETE FROM %s WHERE productID=%d;",data,productID));
			connection.close();
			System.out.println("Delete in database successed!");
		} catch (SQLException e) {
			System.out.println("Delete in database failed!");
			e.printStackTrace();
		}
    }
    public static void updateProductInData(Product product, String data) {
    	try {
    		Connection connection= ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			stm.executeUpdate(String.format("UPDATE %s SET product=\"%s\", priceO=\"%s\", priceS=\"%s\", img=\"%s\", category_categoryID=%d WHERE productID=%d;",
					data,product.getProduct(),product.getOriginalPrice(),product.getSalePrice(),product.getUrl(),product.getCategoryID(),product.getId()));
			connection.close();
			System.out.println("Update in database successed!");
		} catch (SQLException e) {
			System.out.println("Update in database failed!");
			e.printStackTrace();
		}
    }
    public static ArrayList<Product> getProductsByCategory(int categoryID,String data){
    	try {
    		ArrayList<Product> productList=new ArrayList<Product>();
    		Connection connection=ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			ResultSet rs=stm.executeQuery(String.format("Select * from %s where category_categoryID=%d",data,categoryID));
			while (rs.next()) {
				productList.add(new Product(rs.getInt("productID"), rs.getString("product"), rs.getString("priceO"),
						rs.getString("priceS"), rs.getString("img"),rs.getInt("shop_shopID"),rs.getInt("category_categoryID")));
			}
			System.out.println("Get database successed!");
			connection.close();
			return productList;
		} catch (SQLException e) {
			System.out.println("Get database failed!");
			e.printStackTrace();
		}
    	
		return null;
    }
}
