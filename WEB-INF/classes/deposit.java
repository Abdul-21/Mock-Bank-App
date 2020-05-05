import bank.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class deposit extends HttpServlet{
private static final long serialVersionUID = 102831973239L;

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doPost(request,response);
}
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException
    {
      PrintWriter out =response.getWriter();
      HttpSession userSession = request.getSession();
      String UserN = (String)userSession.getAttribute("currentUser");
      User Userobj= (User)userSession.getAttribute("currentUserObj");
      Account Accountobj=(Account)userSession.getAttribute("currentUserAccount");
      Account altAcctobj=(Account)userSession.getAttribute("altAcct");
      userSession.setAttribute("action","Deposit");
      out.println("<html>");
      out.println("<title>Deposit</title>");
      out.println("<FORM METHOD='POST' ACTION='updateBankApp'>");
      out.println("<center><h4>"+Userobj.getFirstName()+" Please note what amount you would like to deposit</h4>");
      out.println("Account ID: <INPUT TYPE=number Name='AcctID'>");
      out.println("<option value='"+Accountobj.getacctType()+"'>"+Accountobj.getacctType()+" ID:"+Accountobj.getCustomerID()+"</option>");
      out.println("<option value='"+altAcctobj.getacctType()+"'>"+altAcctobj.getacctType()+" ID:"+altAcctobj.getCustomerID()+"</option>");
      out.println("Amount Desired: <INPUT TYPE=number Name='Amount'>");
      out.println("<INPUT TYPE='Submit' NAME='Deposit' VALUE='Submit'></center>");
      out.println("<body>");
      out.println("</body>");
}
}
