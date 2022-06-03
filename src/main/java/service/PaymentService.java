package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import config.ConnectionSQL;
import entity.Payment;
import entity.Product;

public class PaymentService {
	public static ArrayList<Payment> getPayments( String data) {
		ArrayList<Payment> paymentList=new ArrayList<Payment>();
		Connection connection=ConnectionSQL.getConnection();
		Statement stm;
		try {
			stm = connection.createStatement();
			ResultSet rs=stm.executeQuery(String.format("Select * from %s",data));
			while (rs.next()) {
				paymentList.add(new Payment(rs.getInt("paymentID"), rs.getString("cardholderName"), rs.getString("nameAccount"),
						rs.getInt("status"), rs.getInt("clientID"),rs.getString("money")));
			}
			connection.close();
			return paymentList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paymentList;
		
	}
    public static void addPaymentToData(Payment pay, String data) {
    	try {
    		Connection connection= ConnectionSQL.getConnection();
    		PreparedStatement stm=connection.prepareStatement("Insert Into "+data+" (cardholderName,nameAccount,status,clientID,money) values(?,?,?,?,?)");
			stm.setString(1, pay.getCardholderName());
			stm.setString(2, pay.getNameAccount());
			stm.setString(3, ""+pay.getStatus());
			stm.setInt(4, pay.getClientID());
			stm.setString(5, pay.getMoney());
    		stm.executeUpdate();
			connection.close();
			stm.close();
			System.out.println("Add into database successed!");
		} catch (SQLException e) {
			System.out.println("Add into database failed!");
			e.printStackTrace();
		}
    }
    public static void updateStatusPayment(int paymentID,String status, String data) {
    	try {
    		Connection connection= ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
    		stm.executeUpdate(String.format("UPDATE %s SET status=%s WHERE paymentID=%d",data,status,paymentID));
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public static void deletePayment(int paymentID, String data) {
    	try {
    		Connection connection= ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
    		stm.executeUpdate(String.format("DELETE FROM %s WHERE paymentID=%d;",data,paymentID));
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public static void updatePaymentsMoney(String data1,String data2) {
    	try {
    		Connection connection= ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			ResultSet rs=stm.executeQuery(String.format("SELECT paymentID,clientID,money,status FROM %s WHERE status!=%d;",data1,0));
			while(rs.next()) {
				stm=connection.createStatement();
				if(rs.getInt("status")==1) {
				stm=connection.createStatement();
				stm.executeUpdate(String.format("Update %s set money=money+%d WHERE clientID=%d",data2,Integer.parseInt(Product.form(rs.getString("money"))),rs.getInt("clientID")));
				stm=connection.createStatement();
				stm.executeUpdate(String.format("DELETE FROM %s WHERE paymentID=%d;",data1,rs.getInt("paymentID")));
				}
				else {
					stm=connection.createStatement();
					stm.executeUpdate(String.format("DELETE FROM %s WHERE paymentID=%d;",data1,rs.getInt("paymentID")));
				}
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
