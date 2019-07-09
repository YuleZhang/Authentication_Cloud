package Action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

import com.sun.org.apache.regexp.internal.REUtil;

import entity.Admin;
import entity.User;
import service.AdminService;
import service.AdminServiceImpl;
import service.UserService;
import service.UserServiceiImpl;

public class RegistAction extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response) throws HTTPException, IOException{
		//接收输入相关信息
		request.setCharacterEncoding("utf-8");
		String type = request.getParameter("status");
		//调试打印参数列表
//		Enumeration enu = request.getParameterNames();
//		while (enu.hasMoreElements()) {
//			String paraName = (String) enu.nextElement();
//			System.out.println(paraName + ": " + request.getParameter(paraName));
//		}
		//管理员注册账号
		if(type.equals("admin"))
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			AdminService as = new AdminServiceImpl();
			Admin admin = new Admin(username, password);
			as.insertOne(admin);
		}
		else if(type.equals("user"))//用户注册
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String status = "Yes";
			String systemSource = request.getParameter("systemSource");
			String ipAddress = request.getRemoteAddr();
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String registerDate = sdf.format(new Date());
			User user = new User(username, password, email, phone, status, systemSource,registerDate,ipAddress,null);
			UserService us = new UserServiceiImpl();
			us.insertOne(user);
		}
		else {
			System.out.println("参数错误");
		}
		//程序的跳转
		response.sendRedirect("login.jsp");
	}
}
