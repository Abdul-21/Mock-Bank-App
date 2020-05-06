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
        String UserN = (String)userSession.getAttribute("currentUser");
        Vector <Account> acctVect = new Vector<Account>();
        ObjectInputStream acctObjects = new ObjectInputStream(new FileInputStream("acctFile.txt")); //Read profile
        while(true){
            try{
              Account Objs= (Account)acctObjects.readObject();
              acctVect.addElement(Objs);
            }catch(Exception e){
              acctObjects.close();
              break;
          }
        }
        userSession.setAttribute("action","Transfer");

        out.println("<html>");
        out.println("<body>");
        out.println("<FORM METHOD='POST' ACTION='updateBankApp'>");
        out.println("<CENTER><h1>Transfer money screen</h1><br>");
        out.println("<h2>Available accounts: </h2><br>");
        for(Account acct : acctVect){
            out.println("<h2>Account Type: "+acct.getacctType()+" with ID: "+String.valueOf(acct.getCustomerID()) + " and a balance of "+"$"+String.valueOf(acct.getBalance())+"</h2><br>");
        }
        out.println("<h2>To finish the transfer enter the following: </h2><br>");
        out.println("ID to transfer from: <INPUT TYPE=number Name='fromID'><br>");
        out.println("<br>");
        out.println("ID to transfer to: <INPUT TYPE=number Name='toID'><br>");
        out.println("<center><h4> Please put in an amount to send:</h4>");
        out.println("<label for='Amount'><b><font COLOR='PURPLE'>Amount to transfer:</font></b></label>");
        out.println("<input type='text' placeholder='Dollar Amount(ex: $00.00)' name='Amount'><br><br>");
        out.println("<INPUT TYPE='Submit' NAME='submitTransfer' VALUE='Submit Transfer'></center>");
        out.println("<INPUT TYPE=Button onClick=\"parent.location = 'index.html'\" value=\"Logout\"><br><br");
        out.println("</body>");
    }
}
