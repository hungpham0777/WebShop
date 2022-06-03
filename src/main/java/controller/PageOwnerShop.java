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
import entity.Product;
import entity.Shop;
import service.ProductService;
@WebServlet(urlPatterns = "/Trangchu/OwnerShop")
public class PageOwnerShop extends HttpServlet{


	ArrayList<Product> productList=new ArrayList<Product>();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
	    HttpSession ses=req.getSession();
	    Shop shop=(Shop) ses.getAttribute("shop");
	    productList=ProductService.getProductsByShop(shop.getId(),"product");
	    req.setAttribute("productList", productList);
		RequestDispatcher dispatcher=req.getRequestDispatcher("/Pages/ManegerPage/OwnerShop.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
	    HttpSession ses=req.getSession();
		int productID;
		String product;
		String priceO;
		String priceS;
		String url;
		int categoryID;
		int shopID;
	    String method=(String)req.getParameter("type");
	    System.out.println(method);
	    switch (method) {
		case "ADD":
			product=(String)req.getParameter("product");
			priceO=(String)req.getParameter("priceO");
			priceS=(String)req.getParameter("priceS");
			url=(String)req.getParameter("url");
			shopID=Integer.parseInt(req.getParameter("shopID"));
			categoryID=Integer.parseInt(req.getParameter("categoryID"));
			ProductService.addProductToData(new Product(0, product, priceO, priceS, url,shopID,categoryID),"product");
			break;
		case "DELETE":
			productID=Integer.parseInt(req.getParameter("pID"));
			ProductService.deleteProductInData(productID, "product");
			break;
		case "PUT":
			productID=Integer.parseInt((String)req.getParameter("id"));
			product=(String)req.getParameter("product");
			priceO=(String)req.getParameter("priceO");
			priceS=(String)req.getParameter("priceS");
			url=(String)req.getParameter("url");
			shopID=Integer.parseInt(req.getParameter("shopID"));
			try {
				System.out.println(req.getParameter("categoryID"));
				categoryID=Integer.parseInt(req.getParameter("categoryID"));
			} catch (Exception e) {
				categoryID=Integer.parseInt(req.getParameter("category0-ID"));
			}
			ProductService.updateProductInData(new Product(productID, product, priceO, priceS, url,shopID,categoryID), "product");
			break;
		default:
			break;
		}
		resp.sendRedirect(req.getContextPath()+"/Trangchu/OwnerShop");
	}
}
