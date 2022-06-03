package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import config.ConnectionSQL;
import entity.Cart;
import entity.Product;


public class CartServive {
    public static void addCartToData(Cart cart, String data) {
    	try {
    		Connection connection=ConnectionSQL.getConnection();
    		PreparedStatement stm=connection.prepareStatement("Insert Into "+data+" (quantity,feature,client_clientID,product_productID) values(?,?,?,?)");
			stm.setInt(1, cart.getQuantity());
			stm.setString(2, cart.getFeature());
			stm.setInt(3, cart.getClientID());
			stm.setInt(4, cart.getProductID());
    		stm.executeUpdate();
    		stm.close();
			connection.close();
			System.out.println("Add into database successed!");
		} catch (SQLException e) {
			System.out.println("Add into database failed!");
			e.printStackTrace();
		}
    }
    public static ArrayList<Cart> getAllItemsCart(String data) {
    	try {
    		ArrayList<Cart> itemsCartList=new ArrayList<Cart>();
    		Connection connection=ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			ResultSet rs=stm.executeQuery(String.format("Select * from %s",data));
			while (rs.next()) {
				itemsCartList.add(new Cart(rs.getInt("clientID"), rs.getInt("quantity"), rs.getString("feature"),rs.getInt("client_clientID"),rs.getInt("product_productID")));
			}
			System.out.println("Get database successed!");
			connection.close();
			return itemsCartList;
		} catch (SQLException e) {
			System.out.println("Get database failed!");
			e.printStackTrace();
		}
    	
		return null;
    }
    public static int getNumItemCartByClient(int clientID,String data) {
    	try {
    		Connection connection=ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			ResultSet rs=stm.executeQuery(String.format("SELECT COUNT(*) FROM %s WHERE client_clientID=%d;",data,clientID));
			System.out.println("Get database successed!");
			connection.close();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Get database failed!");
			e.printStackTrace();
		}
    	
		return 0;	
    }
    public static ArrayList<Cart> getItemsCartByClient(int clientID,String data) {
    	try {
    		ArrayList<Cart> itemsCartList=new ArrayList<Cart>();
    		Connection connection=ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			ResultSet rs=stm.executeQuery(String.format("SELECT * FROM %s WHERE client_clientID=%d;",data,clientID));
			while (rs.next()) {
				itemsCartList.add(new Cart(rs.getInt("cartID"), rs.getInt("quantity"), rs.getString("feature"),rs.getInt("client_clientID"),rs.getInt("product_productID")));
			}
			System.out.println("Get database successed!");
			connection.close();
			return itemsCartList;
		} catch (SQLException e) {
			System.out.println("Get database failed!");
			e.printStackTrace();
		}	
		return null;	
    }
    public static void deleteItemInCart(int cartID, String data) {
    	try {
    		Connection connection=ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			stm.executeUpdate(String.format("DELETE FROM %s WHERE cartID=%d;",data,cartID));
			connection.close();
			System.out.println("Delete in database successed!");
		} catch (SQLException e) {
			System.out.println("Delete in database failed!");
			e.printStackTrace();
		}
    }
    public static void decreaseItemInCart(int cartID,int quantity, String data) {
    	try {
    		Connection connection= ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			if(quantity==1) {
				stm.executeUpdate(String.format("DELETE FROM %s WHERE cartID=%d;",data,cartID));
			}
			else {
			stm.executeUpdate(String.format("UPDATE %s SET quantity=%d WHERE cartID=%d;",
					data,quantity-1,cartID));
			}
			connection.close();
			System.out.println("Update in database successed!");
		} catch (SQLException e) {
			System.out.println("Update in database failed!");
			e.printStackTrace();
		}
    }
    public static void increaseItemInCart(int cartID,int quantity, String data) {
    	try {
    		Connection connection= ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			stm.executeUpdate(String.format("UPDATE %s SET quantity=%d WHERE cartID=%d;",
					data,quantity+1,cartID));
			connection.close();
			System.out.println("Update in database successed!");
		} catch (SQLException e) {
			System.out.println("Update in database failed!");
			e.printStackTrace();
		}
    }
    public static void paymentInCart(int clientID,long money, String dataCart, String dataClient) {
    	try {
    		Connection connection=ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			stm.executeUpdate(String.format("DELETE FROM %s WHERE client_clientID=%d;",dataCart,clientID));
			stm=connection.createStatement();
			stm.executeUpdate(String.format("Update %s set money=money-%d WHERE clientID=%d",dataClient,money,clientID));
			connection.close();
			System.out.println("Delete in database successed!");
		} catch (SQLException e) {
			System.out.println("Delete in database failed!");
			e.printStackTrace();
		}
    }
}
