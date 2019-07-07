package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;

import entity.User;

public class QueryAllView extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, HTTPException{
		HttpSession session = request.getSession(true);
		ArrayList arrayList = (ArrayList) session.getAttribute("value");
		response.setContentType("text/html");
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter ();
		out.println("<html><body>");
		out.println("<h1>下面是所有学生的信息列表</h1>");
		out.println("<table border='1'>");
		out.println("<tr><td>编号</td><td>姓名</td><td>年龄</td><td>电话</td></tr>");
		for(Object s: arrayList)
		{
			User user = (User)s;
			out.print("<tr><td>"+ user.getUserName()+"</td><td>"+user.getPassword()+"</td><td>"+user.getEmail()+"</td><td>"+user.getPhoneNum()+"</td><td>"+"</tr>");
		}
		out.println("</table>");
		out.println("</html></body>");
		out.flush();
	}
}
