import bank.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TransferMoney extends HttpServlet{
private static final long serialVersionUID = 102831973239L;

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doPost(request,response);
}
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException
    {
        PrintWriter out =response.getWriter();
        HttpSession action = request.getSession();
        HttpSession userSession = request.getSession();

        String UserN = (String)userSession.getAttribute("currentUser");
        User Userobj= (User)userSession.getAttribute("currentUserObj");
        Account Accountobj=(Account)userSession.getAttribute("currentUserAccount");
        Account altAcctobj=(Account)userSession.getAttribute("altAcct");

        String accountObjType = (String)Accountobj.getacctType();
        String accountObjID = String.valueOf(Accountobj.getCustomerID());
        String accountObjBal = String.valueOf(Accountobj.getBalance());

        String altAcctObjType = (String)altAcctobj.getacctType();
        String altAcctObjID = String.valueOf(altAcctobj.getCustomerID());
        String altAcctObjBal = String.valueOf(altAcctobj.getBalance());
        userSession.setAttribute("action","Transfer");

        out.println("<html>");
        out.println("<body>");
        out.println("<FORM METHOD='POST' ACTION='updateBankApp'>");
        out.println("<CENTER><h1>Transfer money screen</h1><br>");
        out.println("<h2>Available accounts: </h2><br>");
        out.println("<h2>Account Type: "+accountObjType+" with ID: "+accountObjID+" and a balance of "+accountObjBal+"</h2><br>");
        out.println("<h2>Account Type: "+altAcctObjType+" with ID: "+altAcctObjID+" and a balance of "+altAcctObjBal+"</h2><br>");
        out.println("<h2>To finish the transfer enter the following: </h2><br>");
        //out.println("ID to transfer from: <INPUT TYPE=text Name='fromID'><br>");
        out.println("<FONT COLOR = 'blue'>From ID: <INPUT TYPE=Text Name='fromID'></FONT><br>");
        out.println("<br>");
        //out.println("ID to transfer to: <INPUT TYPE=text Name='toID'><br>");
        out.println("<FONT COLOR = 'blue'>To ID: <INPUT TYPE=Text Name='toID'></FONT><br>");
        out.println("<center><h4>"+Userobj.getFirstName()+", Please put in an amount to send:</h4>");
        out.println("<FONT COLOR = 'blue'>Amount: <INPUT TYPE=Text Name='Amount'></FONT><br>");
        out.println("<INPUT TYPE='Submit' NAME='submitTransfer' VALUE='Submit Transfer'></center>");
        //out.println("<INPUT TYPE=Button onClick=\"parent.location = 'index.html'\" value=\"Logout\"><br><br");
        out.println("</body>");
        
    }
}
