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

import com.mysql.cj.Session;

import config.ConnectionSQL;
import entity.Payment;
import entity.Product;
import service.PaymentService;
import service.ProductService;

@WebServlet(urlPatterns = "/Admin")
public class PageAdmin extends HttpServlet{


	ArrayList<Product> productList=new ArrayList<Product>();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher=req.getRequestDispatcher("/Pages/ManegerPage/Admin.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
	    HttpSession ses=req.getSession();
	    String table=req.getParameter("table");
	    System.out.println(table);
	    switch (table) {
		case "payment":
			String status=req.getParameter("status");
			int paymentID=Integer.parseInt(req.getParameter("paymentID"));
			if(!status.equals("3")) PaymentService.updateStatusPayment(paymentID, status, "payment");
			else PaymentService.deletePayment(paymentID, "payment");
			break;
		case "submit":
			PaymentService.updatePaymentsMoney("payment","client");
			break;
//		case "DELETE":
//			productID=Integer.parseInt(req.getParameter("pID"));
//			ProductService.deleteProductInData(productID, "products");
//			break;
//		case "PUT":
//			productID=Integer.parseInt(req.getParameter("id"));
//			product=(String)req.getParameter("product");
//			priceO=(String)req.getParameter("priceO");
//			priceS=(String)req.getParameter("priceS");
//			url=(String)req.getParameter("url");
//			ses.setAttribute("id", productID);
//			ses.setAttribute("product", product);
//			ses.setAttribute("priceO", priceO);
//			ses.setAttribute("priceS", priceS);
//			ses.setAttribute("url", url);
//			break;
		default:
			break;
		}
		
		RequestDispatcher dispatcher=req.getRequestDispatcher("/Pages/ManegerPage/Admin.jsp");
		dispatcher.forward(req, resp);
	}

}
