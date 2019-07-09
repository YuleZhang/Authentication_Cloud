package Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import javax.xml.ws.http.HTTPException;

import entity.Admin;
import entity.User;
import net.sf.json.JSONObject;
import service.AdminService;
import service.AdminServiceImpl;
//import service.EmployeeService;
//import service.EmployeeServiceImpl;
import service.UserService;
import service.UserServiceiImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAction extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response) throws HTTPException,IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String status = request.getParameter("role");	
		UserService uService = new UserServiceiImpl(); //�û�������
		List<User> listUser = uService.getAllUser(); //��ȡ�û��б�
		HttpSession session = request.getSession();
//		System.out.println("status: "+status);
		//���ݲ�ͬ��ݽ��в�ͬ�ķ���ʵ����
		
		if(status.equals("admin"))
		{
			Admin admin = new Admin(username, password);
			AdminService aService = new AdminServiceImpl();
			if(aService.Login(username, password))
			{
				session.setAttribute("userList", listUser);
				session.setAttribute("admin", admin);
				response.sendRedirect(request.getContextPath()+"/admin.jsp");
			}
			else {
				response.sendRedirect(request.getContextPath()+"/login.jsp");
			}
		}
		else{
			
			if(uService.Login(username, password))
			{
				session.setAttribute("userLogs", listUser);
				session.setAttribute("username", username);
				response.sendRedirect(request.getContextPath()+"/userList.jsp");
			}
			else {
				response.sendRedirect(request.getContextPath()+"/login.jsp");
			}
		}
		
	}
}
