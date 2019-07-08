package Action;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

public class ExitAction extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response) throws HTTPException,IOException{
		response.sendRedirect("/EMS/login.jsp");
	}
}
