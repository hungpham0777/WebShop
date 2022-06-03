package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import config.ConnectionSQL;
import entity.Comment;

public class CommentService {
	public static ArrayList<Comment> getCommentsByProductID(int productID, String data) {
		ArrayList<Comment>  commentList=new ArrayList<Comment>();
		Connection connection=ConnectionSQL.getConnection();
		Statement stm;
		try {
			stm = connection.createStatement();
			ResultSet rs=stm.executeQuery(String.format("Select * from %s where product_productID=%d",data,productID));
			while (rs.next()) {
				commentList.add(new Comment(rs.getInt("commentID"), rs.getString("comment"), rs.getString("date"),
						rs.getInt("client_clientID"),rs.getInt("product_productID")));
			}
			connection.close();
			return commentList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentList;
		
	}
    public static void addCommentToData(Comment comment, String data) {
    	try {
    		Connection connection= ConnectionSQL.getConnection();
    		PreparedStatement stm=connection.prepareStatement("Insert Into "+data+" (comment,client_clientID,product_productID,date) values(?,?,?,NOW())");
			stm.setString(1, comment.getComment());
			stm.setInt(2, comment.getClientID());
			stm.setInt(3, comment.getProductID());
    		stm.executeUpdate();
			connection.close();
			stm.close();
			System.out.println("Add into database successed!");
		} catch (SQLException e) {
			System.out.println("Add into database failed!");
			e.printStackTrace();
		}
    }
}
