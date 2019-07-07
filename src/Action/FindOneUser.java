package Action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;

import entity.User;
import service.UserService;
import service.UserServiceiImpl;

public class FindOneUser extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response) throws HTTPException,IOException{
		String username = request.getParameter("username");
		UserService us = new UserServiceiImpl();
		List<User> userList = new ArrayList<>();
		if(null==username || username.equals(""))
		{
			userList = us.getAllUser();
		}
		else{
			User user = us.queryOne(username);
			userList.add(user);
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("userList", userList);
		response.sendRedirect(request.getContextPath()+"/admin.jsp");
	}
}
