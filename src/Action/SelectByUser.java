package Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

import org.bson.Document;

import entity.User;
import net.sf.json.JSONString;
import service.UserService;
import service.UserServiceiImpl;

public class SelectByUser extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response) throws HTTPException,IOException{
		String userName = request.getParameter("username");
		UserService us = new UserServiceiImpl();
	
//		String jsonString = 
		PrintWriter out = response.getWriter();
//		out.println(js);
	}

}
