import bank.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddCustomer extends HttpServlet{
private static final long serialVersionUID = 102831973239L;

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doPost(request,response);
}
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException
    {
      PrintWriter out =response.getWriter();
      HttpSession userSession = request.getSession();
      String UserN = (String)userSession.getaAttribute("currentUser");
      User Userobj= (User)userSession.getaAttribute("currentUserObj")
      out.println("<html>");
      out.println("<title>withdraw</title>");
      out.println("<center><h4>"+Userobj.getFirstName()+"Please note what amount you would like to withdraw</h4>");
      out.println("Amount Desired: <INPUT TYPE=Text Name='Amount'>");
      out.println("<INPUT TYPE='Submit' NAME='Withdraw' VALUE='Submit'></center>");
      out.println("<body>");
      out.println("</body>");
}
