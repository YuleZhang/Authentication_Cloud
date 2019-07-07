package Action;

import java.io.IOException;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import javax.xml.ws.http.HTTPException;

import entity.Employee;
import entity.User;
import service.AdminService;
import service.AdminServiceImpl;
//import service.EmployeeService;
//import service.EmployeeServiceImpl;
import service.UserService;
import service.UserServiceiImpl;
import java.util.ArrayList;
import java.util.List;

public class UserAction extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response) throws HTTPException,IOException{
		//获取url传入参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String status = request.getParameter("role");	
		UserService uService = new UserServiceiImpl(); //用户服务类
		List<User> listUser = uService.getAllUser(); //获取用户列表
		HttpSession session = request.getSession();
//		System.out.println("status: "+status);
		//根据不同身份进行不同的服务实例化
		if(status.equals("admin"))
		{
			AdminService aService = new AdminServiceImpl();
			if(aService.Login(username, password))
			{
				session.setAttribute("userList", listUser);
				response.sendRedirect("/EMS/admin.jsp");
			}
			else {
				response.sendRedirect("/EMS/Faied.jsp");
			}
		}
		else{
			
			if(uService.Login(username, password))
			{
				session.setAttribute("userLogs", listUser);
				response.sendRedirect("/EMS/userList.jsp");
			}
			else {
				response.sendRedirect("/EMS/Faied.jsp");
			}
		}
		
	}
}
