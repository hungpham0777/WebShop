package controller;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.ConnectionSQL;
import entity.Client;
import entity.Comment;
import entity.Product;
import service.CartServive;
import service.CommentService;
import service.ProductService;


@WebServlet(urlPatterns = "/Trangchu/Product")
public class DetailProduct extends HttpServlet{

	ArrayList<Product> productList=new ArrayList<Product>();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
	    int id= Integer.parseInt(req.getParameter("id"));
		String product=(String)req.getParameter("product");
		String priceO=(String)req.getParameter("priceO");
		String priceS=(String)req.getParameter("priceS");
		String url=(String)req.getParameter("url");
		int shopID=Integer.parseInt(req.getParameter("shopID"));
	    req.setAttribute("product", new Product(id, product, priceO, priceS, url,shopID));
	    
		RequestDispatcher dispatcher=req.getRequestDispatcher("/Detail.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
	    HttpSession ses=req.getSession();
	    Client client=(Client)ses.getAttribute("user");
	    String comment=req.getParameter("comment");
	    int id= Integer.parseInt(req.getParameter("id"));
		String product=(String)req.getParameter("product");
		String priceO=(String)req.getParameter("priceO");
		String priceS=(String)req.getParameter("priceS");
		String url=(String)req.getParameter("url");
		int shopID=Integer.parseInt(req.getParameter("shopID"));
//		int rating= Integer.parseInt(req.getParameter("rate"));
	    req.setAttribute("product", new Product(id, product, priceO, priceS, url,shopID));
	    CommentService.addCommentToData(new Comment(comment,client.getId(),id) , "comment");
		req.getRequestDispatcher("/Detail.jsp").forward(req, resp);
	}
}
