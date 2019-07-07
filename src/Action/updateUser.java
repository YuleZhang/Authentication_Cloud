package Action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;

import entity.User;
import service.UserService;
import service.UserServiceiImpl;

public class updateUser extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response) throws HTTPException,IOException{
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		UserService us = new UserServiceiImpl();
		System.out.println("更新的用户为：："+username);
		User u = us.queryOne(username);
		u.setPhoneNum(phone);
		u.setEmail(email);
		us.updateOne(u);
		List<User> userList = us.getAllUser();
		HttpSession session = request.getSession(true);
		System.out.println(userList);
		session.setAttribute("userList", userList);
		response.sendRedirect(request.getContextPath()+"/admin.jsp");
	}
}
