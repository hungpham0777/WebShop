package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import entity.Client;
import entity.Shop;
import service.ClientService;
import service.OwnerShopService;

@WebServlet("/Trangchu/Account")
@MultipartConfig(maxFileSize = 1024*100, maxRequestSize = 1024*300)
public class PageAccount extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher=req.getRequestDispatcher("/Pages/ManegerPage/User.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
	    HttpSession ses=req.getSession();
	    int clientID=Integer.parseInt((String)req.getParameter("clientID"));
		String user=(String)req.getParameter("user");
		String pass=(String)req.getParameter("pass");
		String name=(String)req.getParameter("fullname");
		String address=(String)req.getParameter("address");
		String phone=(String)req.getParameter("phone");
		String birthday=(String)req.getParameter("birthday");
		String money=(String) req.getParameter("money");
		Part image= req.getPart("image");
		String realPart=req.getServletContext().getRealPath("/DataImage/user");
		System.out.println(realPart);
		if(!Files.exists(Path.of(realPart))) {
			Files.createDirectory(Path.of(realPart));
		}
		String pathImage=realPart+"/"+image.getSubmittedFileName();
		File fileOldName=new File(pathImage);
		File fileNewName=new File(realPart+"/"+"avatar_userID"+clientID);
		try {
			fileNewName.delete();
		} catch (Exception e) {
			System.out.println("Delete failed!");
		}
		fileNewName=new File(realPart+"/"+"avatar_userID"+clientID);
		image.write(pathImage);
		if(fileOldName.renameTo(fileNewName))
			System.out.println("Rename successed!");
		else
			System.out.println("Rename failed!");
		Client client=new Client(clientID,user,pass,money,name,birthday,address,phone,"/DataImage/user/avatar_userID"+clientID);
		ClientService.updateAccountInData(client, "client");
		ses.setAttribute("user", client);
		resp.sendRedirect(req.getContextPath()+"/Trangchu");
	}
}
