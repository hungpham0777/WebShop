package controller;


import java.io.IOException;
import java.util.ArrayList;

import entity.Cart;
import entity.Product;
import entity.Shop;
import service.CartServive;
import service.OwnerShopService;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.ConnectionSQL;

@WebServlet("/Trangchu")
public class PageHome extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
		ArrayList<Shop> shopList=new ArrayList<Shop>();
		shopList= OwnerShopService.getShops("shop");
		req.setAttribute("shopList", shopList);
		RequestDispatcher dispatcher=req.getRequestDispatcher("/Home.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
	    int productID=Integer.parseInt(req.getParameter("productID"));
	    int clientID=Integer.parseInt(req.getParameter("clientID"));
	    Cart cart=new Cart(0, 1, "", clientID, productID );
	    CartServive.addCartToData(cart, "cart");
		resp.sendRedirect(req.getContextPath()+"/Trangchu");
	}
}
