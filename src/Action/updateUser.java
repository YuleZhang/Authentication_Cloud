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
		String operate = request.getParameter("operate");
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		UserService us = new UserServiceiImpl();
		System.out.println("���µ��û�Ϊ����"+username);
		System.out.println("Ҫ���еĲ���Ϊ"+operate);
		User u = us.queryOne(username);
		String status = u.getStatus();
		if(null == operate)//��ʾҪ������߶���
		{
			if(status.equals("Yes"))
			{
				u.setStatus("No");
			}
			else
			{
				u.setStatus("Yes");
			}
		}
		else{//��ʾҪ�޸���Ϣ
			u.setPhoneNum(phone);
			u.setEmail(email);
		}
		us.updateOne(u);
		List<User> userList = us.getAllUser();
		HttpSession session = request.getSession(true);
		System.out.println(userList);
		session.setAttribute("userList", userList);
		response.sendRedirect(request.getContextPath()+"/admin.jsp");
	}
}
