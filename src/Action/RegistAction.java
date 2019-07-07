package Action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

import entity.User;
import service.UserService;
import service.UserServiceiImpl;

public class RegistAction extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response) throws HTTPException, IOException{
		//接收输入相关信息
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String status = "Yes";
		String systemSource = request.getParameter("systemSource");
		String ipAddress = request.getRemoteAddr();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd hh24:mm:ss");
		String registerDate = sdf.format(new Date());
		User user = new User(username, password, email, phone, status, systemSource,registerDate,ipAddress,null);
		UserService us = new UserServiceiImpl();
		us.insertOne(user);
		//程序的跳转
		response.sendRedirect("login.jsp");
	}
}
