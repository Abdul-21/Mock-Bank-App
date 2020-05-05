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

      String accountObjType = (String)Accountobj.getacctType();
      String accountObjID = String.valueOf(Accountobj.getCustomerID());
      String altAcctObjType = (String)altAcctobj.getacctType();
      String altAcctObjID = String.valueOf(altAcctobj.getCustomerID());

      out.println("<html>");
      out.println("<body>");
      out.println("<title>withdraw</title>");
      out.println("<FORM METHOD='POST' ACTION='updateBankApp'>");
      out.println("<center><h2>"+Userobj.getFirstName()+", Please enter the following:</h2>");
      out.println("ID to transfer to: <INPUT TYPE=number Name='AcctID'>");
      out.println("<select id='choose-acct' name=choose-acct>");
      //out.println("<option value='"+accountObjType+"'>"+"Acct Type: "+accountObjType+" ID:"+Accountobj.getCustomerID()+"</option>");
      //out.println("<option value='"+altAcctObjType+"'>"+"Acct Type: "+altAcctObjType+" ID:"+altAcctobj.getCustomerID()+"</option>");
      out.println("<option value='acct1'>"+"Take from: "+accountObjID+" with type: "+accountObjType+"</option>");
      out.println("<option value='acct1'>"+"Take from: "+altAcctObjID+" with type: "+altAcctObjType+"</option>");
      out.println("</select>");
      out.println("<label for='Amount'>Amount Desired</b></label>");
      out.println("<input type='text' placeholder='Dollar Amount(ex: $00.00)' name='Amount'><br><br>");
      //out.println("Amount Desired: <INPUT TYPE=number Name='Amount'>");
      out.println("<INPUT TYPE='Submit' NAME='Withdraw' VALUE='Submit'></center>");
      out.println("</body>");
}
}
