package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import config.ConnectionSQL;
import entity.Client;


public class ClientService {
    public static void addClientToData(Client client, String data) {
    	try {
    		Connection connection=ConnectionSQL.getConnection();
    		PreparedStatement stm=connection.prepareStatement("Insert Into "+data+" (user,pass,money,fullname,birthday,address,phone) values(?,?,?,?,?,?,?)");
			stm.setString(1, client.getUser());
			stm.setString(2, client.getPassword());
			stm.setString(3, client.getMoney());
			stm.setString(4, client.getFullName());
			stm.setString(5, client.getBirthday());
			stm.setString(6, client.getAddress());
			stm.setString(7, client.getPhone());
    		stm.executeUpdate();
    		stm.close();
			connection.close();
			System.out.println("Add into database successed!");
		} catch (SQLException e) {
			System.out.println("Add into database failed!");
			e.printStackTrace();
		}
    }
    public static ArrayList<Client> getClients(String data) {
    	try {
    		ArrayList<Client> clientList=new ArrayList<Client>();
    		Connection connection=ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			ResultSet rs=stm.executeQuery(String.format("Select * from %s",data));
			while (rs.next()) {
				clientList.add(new Client(rs.getInt("clientID"), rs.getString("user"), rs.getString("pass"),rs.getString("money"),
						rs.getString("fullname"),rs.getDate("birthday").toString(),rs.getString("address"),rs.getString("phone"),rs.getString("image")));
			}
			System.out.println("Get database successed!");
			connection.close();
			return clientList;
		} catch (SQLException e) {
			System.out.println("Get database failed!");
			e.printStackTrace();
		}
    	
		return null;
    }
    public static Client getClientById(int clientID,String data) {
    	try {
    		Connection connection=ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			ResultSet rs=stm.executeQuery(String.format("Select * from %s where clientID=%d",data,clientID));
			System.out.println("Get database successed!");
			if(rs.next()) {
				return new Client(rs.getInt("clientID"), rs.getString("user"), rs.getString("pass"),rs.getString("money"),
						rs.getString("fullname"),rs.getDate("birthday").toString(),rs.getString("address"),rs.getString("phone"),rs.getString("image"));
			}
			connection.close();
		} catch (SQLException e) {
			System.out.println("Get database failed!");
			e.printStackTrace();
		}
    	
		return null;
    }
    public static Client getAccesser(String user, String pass,String data) {
    	try {
    		Connection connection=ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			ResultSet rs=stm.executeQuery(String.format("Select * from %s where user=\"%s\"AND pass=\"%s\"",data,user,pass));
			if(rs.next()) {
				return new Client(rs.getInt("clientID"), rs.getString("user"), rs.getString("pass"),rs.getString("money"),
						rs.getString("fullname"),rs.getDate("birthday").toString(),rs.getString("address"),rs.getString("phone"),rs.getString("image"));
			}
			System.out.println("Get database successed!");
			connection.close();
		} catch (SQLException e) {
			System.out.println("Get database failed!");
			e.printStackTrace();
		}
		return null;
    }
    public static void updateAccountInData(Client client, String data) {
    	try {
    		Connection connection= ConnectionSQL.getConnection();
			Statement stm=connection.createStatement();
			stm.executeUpdate(String.format("UPDATE %s SET user=\"%s\", pass=\"%s\", fullname=\"%s\", birthday=\"%s\", address=\"%s\", phone=\"%s\", image=\"%s\" WHERE clientID=%d;",
					data,client.getUser(),client.getPassword(),client.getFullName(),client.getBirthday(),client.getAddress(),
					client.getPhone(),client.getImage(),client.getId()));
			connection.close();
			System.out.println("Update in database successed!");
		} catch (SQLException e) {
			System.out.println("Update in database failed!");
			e.printStackTrace();
		}
    }
}
