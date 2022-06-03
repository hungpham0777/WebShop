package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Product;
import service.ProductService;

@WebServlet(urlPatterns = "/Trangchu/ProductMenu")
public class PageProductMenu extends HttpServlet{

	ArrayList<Product> productList=new ArrayList<Product>();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
	    ArrayList<Product> productList=new ArrayList<Product>();
	    String search=req.getParameter("search");
	    String categoryID=req.getParameter("categoryID");
	    if(search==null) {
	    	if(categoryID==null) {
	    		productList=ProductService.getProductFromData("product");
	    	}else {
	    		productList=ProductService.getProductsByCategory(Integer.parseInt(categoryID),"product");
	    	}
	    }else {
	    	System.out.println(search);
	    }
	    req.setAttribute("productList", productList);
		RequestDispatcher dispatcher=req.getRequestDispatcher("/ProductMenu.jsp");
		dispatcher.forward(req, resp);
	}
}
