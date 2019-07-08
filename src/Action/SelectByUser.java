package Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

import org.bson.Document;

import entity.User;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;
import service.UserService;
import service.UserServiceiImpl;

public class SelectByUser extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response) throws HTTPException,IOException{
		String userName = request.getParameter("username");
		System.out.println("¿ªÊ¼²éÑ¯user");
		UserService us = new UserServiceiImpl();
		User user = us.queryOne(userName);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", user.getUserName());
		jsonObject.put("email", user.getEmail());
		jsonObject.put("phone", user.getPhoneNum());
		jsonObject.put("finalip", user.getFinalip());
		jsonObject.put("finalTime", user.getFinalTime());
		String jsonString = jsonObject.toString();
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}

}
