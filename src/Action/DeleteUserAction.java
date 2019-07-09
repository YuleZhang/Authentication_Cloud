package Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;

import entity.User;
import service.UserService;
//import service.EmployeeService;
//import service.EmployeeServiceImpl;
import service.UserServiceiImpl;

public class DeleteUserAction extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, HTTPException{
		String username = request.getParameter("username");
		String status = request.getParameter("status");
		String pageIndex = request.getParameter("pageIndex");
		String count = request.getParameter("count");
		User user = new User(username, null, null, null, status, null, null,null,null);
		UserService us = new UserServiceiImpl();
		us.deleteOne(user);
		List<User> userList = us.getAllUser();
		System.out.println("userlist" + userList);
		HttpSession session = request.getSession(true);
		session.setAttribute("userList", userList);
		response.sendRedirect(request.getContextPath()+"/admin.jsp");
	}
}
