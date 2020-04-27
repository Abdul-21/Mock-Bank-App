import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class bankapp extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException,ServletException
	{
    try{
      String login = request.getParameter("username");
    }catch(NumberFormatException nfEx){
    			sendPage(response,"*** Invalid entry! ***");
    			return;
    }
    HttpSession userSession = request.getSession();
    if(userSession == NULL){
      PrintWriter out = reply.getWriter();
      out.println("<HTML>");
      out.println("<HEAD>");
      out.println("<TITLE>User Session wasn't found</TITLE>");
      out.println("</HEAD>");
    }
    PrintWriter out = reply.getWriter();
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Session was found</TITLE>");
    out.println("</HEAD>");
  }
}
