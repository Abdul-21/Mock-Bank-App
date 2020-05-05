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
        HttpSession userSession = request.getSession();

        User Userobj= (User)userSession.getAttribute("currentUserObj");
        Account selectedAccount = (Account)userSession.getAttribute("currentUserAccount");
        
        out.println("<html>");
        out.println("<body>");
        out.println("<FORM METHOD='POST' ACTION='updateBankApp'>");
        out.println("<CENTER><h1>Transfer money below</b1>");
        out.println("<h2> Transferring from user: "+Userobj.getUserName()+"</h2>");
        out.println("<h2> With ID: "+selectedAccount.getCustomerID()+"</h2>");
        out.println("<h2> Current Account Balance: $"+selectedAccount.getBalance()+"</h2>");
        out.println("<h2> Transferring to account: </h2>");
        out.println("Account ID: <INPUT TYPE=number Name='accountID'><br>");
        out.println("<center><h4>"+Userobj.getFirstName()+", Please put in an amount to send:</h4>");
        out.println("Amount Desired: <INPUT TYPE=number Name='Amount'><br>");
        out.println("<INPUT TYPE='Submit' NAME='submitTransfer' VALUE='submitTransfer'></center>");
        out.println("<button onclick=\"location.href = 'index.html';\"'>Logout</a></button>");
        out.println("</body>");
    }
}