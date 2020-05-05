import bank.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class withdraw extends HttpServlet{
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
      userSession.setAttribute("action","Withdraw");
      out.println("<html>");
      out.println("<title>withdraw</title>");
      out.println("<FORM METHOD='POST' ACTION='updateBankApp'>");
      out.println("<center><h4>"+Userobj.getFirstName()+",Please note what amount you would like to withdraw</h4>");
      out.println("Account ID: <INPUT TYPE=number Name='AcctID'>");
      out.println("<option value='"+Accountobj.getacctType()+"'>"+"Acct Type: "+Accountobj.getacctType()+" ID:"+Accountobj.getCustomerID()+"</option>");
      out.println("<option value='"+altAcctobj.getacctType()+"'>"+"Acct Type: "+altAcctobj.getacctType()+" ID:"+altAcctobj.getCustomerID()+"</option>");
      out.println("Amount Desired: <INPUT TYPE=number Name='Amount'>");
      out.println("<INPUT TYPE='Submit' NAME='Withdraw' VALUE='Submit'></center>");
      out.println("<body>");
      out.println("</body>");
}
}
