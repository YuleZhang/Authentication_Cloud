package Action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;

import entity.Employee;
import entity.User;
//import service.EmployeeService;
//import service.EmployeeServiceImpl;
import service.UserService;
import service.UserServiceiImpl;

public class QueryAll extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response) throws HTTPException,IOException{
		UserService userService = new UserServiceiImpl();
		List<User> listUser = userService.getAllUser();
		int count = listUser.size();
		HttpSession session = request.getSession();
		session.setAttribute("userList", listUser);
		response.sendRedirect(request.getContextPath()+ "userList.jsp");
//		EmployeeService es = new EmployeeServiceImpl();
//		List<Employee> listEmp = es.getALLEmployee();
//		System.out.println("11"+listEmp);
//		HttpSession session = request.getSession(true);
//		session.setAttribute("EmpList", listEmp);
//		response.sendRedirect("/EMS/Success.jsp");
	}
	
}
