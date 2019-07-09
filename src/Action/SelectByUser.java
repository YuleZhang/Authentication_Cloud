package Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

import org.bson.Document;

import entity.Admin;
import entity.User;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;
import service.AdminService;
import service.AdminServiceImpl;
import service.UserService;
import service.UserServiceiImpl;

public class SelectByUser extends HttpServlet{
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * 响应页面的两处请求，即个人信息查询请求及修改按钮显示请求
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws HTTPException,IOException{
		String userName = request.getParameter("username");
		String flag = request.getParameter("flag");
		JSONObject jsonObject = new JSONObject();
		System.out.println("开始查询user");
		UserService us = new UserServiceiImpl();
		User user = us.queryOne(userName);
		if(flag.equals("0"))
		{
			jsonObject.put("username", user.getUserName());
			jsonObject.put("email", user.getEmail());
			jsonObject.put("phone", user.getPhoneNum());
			jsonObject.put("finalip", user.getFinalip());
			jsonObject.put("finalTime", user.getFinalTime());
		}
		else if(flag.equals("1"))//要查询管理员信息
		{
			AdminService as = new AdminServiceImpl();
			Admin admin = as.queryOne(userName);
			jsonObject.put("username", admin.getUsername());
		}
		String jsonString = jsonObject.toString();
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}

}
