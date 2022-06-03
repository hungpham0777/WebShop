package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import entity.Shop;
import service.ClientService;
import service.OwnerShopService;
import service.ProductService;
import entity.Product;
@WebServlet(urlPatterns = "/Trangchu/SignUpIn")
public class SignUpIn extends HttpServlet{
	ArrayList<Product> productList=new ArrayList<Product>();
	private String userAdmin="Admin";
	private String passAdmin="0123456789";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
	    HttpSession ses=req.getSession();
	    ses.setAttribute("accesser", "unknow");
	    ses.setAttribute("eror", null);
		RequestDispatcher dispatcher=req.getRequestDispatcher("/Pages/SignUp-In/SignUpIn.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
	    String account=(String)req.getParameter("account");
	    String user=null,pass = null,accesser=null;
	    HttpSession ses=req.getSession();
	    switch (account) {
		case "client":
			user=(String)req.getParameter("user");
			pass=(String)req.getParameter("password");
			String name=(String)req.getParameter("fullName");
			String address=(String)req.getParameter("address");
			String phone=(String)req.getParameter("phone");
			String birthday=(String)req.getParameter("birthday");
			Client client=new Client(0,user,pass,"0",name,birthday,address,phone,null);
			ClientService.addClientToData(client, "client");
			ses.setAttribute("accesser","user");
			ses.setAttribute("user", client);
			resp.sendRedirect(req.getContextPath()+"/Trangchu");
			break;
		case "shop":
			user=(String)req.getParameter("user");
			pass=(String)req.getParameter("password");
			String nameShop=(String)req.getParameter("nameShop");
			String urlAvatar=(String)req.getParameter("urlAvatar");
			Shop shop=new Shop(0, user, pass, nameShop,urlAvatar);
			OwnerShopService.addShopToData(shop, "shop");
			ses.setAttribute("accesser","shop");
			ses.setAttribute("shop", shop);
			resp.sendRedirect(req.getContextPath()+"/Trangchu/OwnerShop");
			break;
		case "login":
			user=(String)req.getParameter("user");
			pass=(String)req.getParameter("password");
			Client object1=ClientService.getAccesser(user, pass,"client");
			if(object1!=null)
			{
				ses.setAttribute("accesser", "user");
				ses.setAttribute("user", object1);
				break;
			}
			Shop object2=OwnerShopService.getAccesser(user, pass, "shop");
			if(object2!=null)
			{
				ses.setAttribute("accesser", "shop");
				ses.setAttribute("shop", object2);
				break;
			}
		}
	    accesser=(String) ses.getAttribute("accesser");
	    if(account.equals("login")) {
		if(user.equals(userAdmin) && pass.equals(passAdmin) )
		{
			ses.setAttribute("eror",null);
			ses.setAttribute("accesser", "admin");
			resp.sendRedirect(req.getContextPath()+"/Admin");	
		}else if(accesser.equals("user")) {
			ses.setAttribute("eror",null);
	    	resp.sendRedirect(req.getContextPath()+"/Trangchu");
	    }
	    else if(accesser.equals("shop")) {
	    	ses.setAttribute("eror",null);
	    	resp.sendRedirect(req.getContextPath()+"/Trangchu/OwnerShop");
	    } else {
	    	ses.setAttribute("eror", "Tài khoản hoặc mật khẩu không đúng.");
	    	req.getRequestDispatcher("/Pages/SignUp-In/SignUpIn.jsp").forward(req, resp);
	    }
	    }
	}
}
