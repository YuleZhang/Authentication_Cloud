package Action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.xml.ws.http.HTTPException;

import entity.Employee;
//import service.EmployeeService;
//import service.EmployeeServiceImpl;
import entity.User;
import service.UserService;
import service.UserServiceiImpl;

public class AddOneEmployee extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException,HTTPException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String ipAddress = getIpAddress(request);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String registerDate = sdf.format(new Date());
		String status = request.getParameter("status");
		String systemSource = request.getParameter("systemSource");
		User user = new User(username, password, email, phone, status, systemSource, registerDate, ipAddress, null);
		UserService us = new UserServiceiImpl();
		us.insertOne(user);
		List<User> userList = us.getAllUser();
		HttpSession session = request.getSession(true);
		session.setAttribute("userList", userList);
		response.sendRedirect(request.getContextPath()+"/admin.jsp");
	}
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
