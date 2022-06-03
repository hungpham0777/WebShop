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

import entity.Client;
import entity.Shop;
import service.CartServive;
import service.OwnerShopService;

@WebServlet("/Trangchu/GioHang")
public class PageCart extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
	    String actionCart=(String) req.getParameter("actionCart");
	    if(actionCart!=null) {
	    int cartID=Integer.parseInt(req.getParameter("cartID"));
	    int quantity;
	    switch (actionCart) {
		case "remove":
			CartServive.deleteItemInCart(cartID, "cart");
			break;
		case "plus":
			quantity=Integer.parseInt(req.getParameter("quantity"));
			CartServive.increaseItemInCart(cartID, quantity, "cart");
			break;
		case "minus":
			quantity=Integer.parseInt(req.getParameter("quantity"));
			CartServive.decreaseItemInCart(cartID, quantity, "cart");
			break;
		default:
			break;
		}
	  }
		RequestDispatcher dispatcher=req.getRequestDispatcher("/Pages/ActionDataPage/Cart.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
	    HttpSession ses=req.getSession();
	    Client client=(Client)ses.getAttribute("user");
	    long totalMoney=Long.parseLong(req.getParameter("totalMoney"));
		CartServive.paymentInCart(client.getId(), totalMoney, "cart", "client");
		req.getRequestDispatcher("/Pages/ActionDataPage/Cart.jsp").forward(req, resp);
	}
}
