//package controller;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import config.ConnectionSQL;
//import entity.Product;
//import service.ProductService;
//@WebServlet(urlPatterns ={"/Trangchu/Admin/Update"})
//public class PageUpdate extends HttpServlet {
//
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//	    resp.setCharacterEncoding("UTF-8");
//	    resp.setContentType("text/html; charset=UTF-8");
//		HttpSession ses=req.getSession();
//		
//		req.setAttribute("id",ses.getAttribute("id"));
//		req.setAttribute("product",ses.getAttribute("product"));
//		req.setAttribute("priceO",ses.getAttribute("priceO"));
//		req.setAttribute("priceS",ses.getAttribute("priceS"));
//		req.setAttribute("url",ses.getAttribute("url"));
//		RequestDispatcher dispatcher=req.getRequestDispatcher("/Pages/ActionDataPage/Update.jsp");
//		dispatcher.forward(req, resp);
//	}
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//	    resp.setCharacterEncoding("UTF-8");
//	    resp.setContentType("text/html; charset=UTF-8");
//		int productID=Integer.parseInt((String)req.getParameter("id"));
//		String product=(String)req.getParameter("product");
//		String priceO=(String)req.getParameter("priceO");
//		String priceS=(String)req.getParameter("priceS");
//		String url=(String)req.getParameter("url");
//		System.out.println(productID+product);
//		ProductService.updateProductInData(new Product(productID, product, priceO, priceS, url), "products");
//		resp.sendRedirect(req.getContextPath()+"/Trangchu/Admin");
//	}
//}
