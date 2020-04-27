import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;



public class bankapp extends HttpServlet {
  private static final long serialVersionUID = 102831973239L;

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
  public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException,ServletException
	{
    PrintWriter out =response.getWriter();
    try{
      String login = request.getParameter("username");
    }catch(NumberFormatException nfEx){
          out.println("<HTML>");
          out.println("<HEAD>");
          out.println("<TITLE>RIP</TITLE>");
          out.println("</HEAD>");
    			// sendPage(response,"*** Invalid entry! ***");
    			return;
    }
    HttpSession userSession = request.getSession();
    if(userSession == null){
      out.println("<HTML>");
      out.println("<HEAD>");
      out.println("<TITLE>User Session wasn't found</TITLE>");
      out.println("</HEAD>");
    }else{
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Session was found</TITLE>");
    out.println("</HEAD>");
  }
  }
}
