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
        out.println("<head>");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("expires", 0);
        response.setHeader("Expires", "0");
        out.println("</head>");
        out.println("<html>");
        out.println("<style>");
        out.println("body {");
        out.println("background-image: url('portal.jpg');");
        out.println("background-repeat: no-repeat;");
        out.println("}");
        out.println("div {");
        out.println("height: 900px;");
        out.println("width: 600px;");
        out.println("background:#ffcc33;");
        out.println("position: fixed;");
        out.println("overflow: scroll;");
        out.println("top: 50%;");
        out.println("left: 50%;");
        out.println("margin-top: -500px;");
        out.println("margin-left: -200px;");
        out.println("}");
        out.println("</style>");
        out.println("<body>");
        out.println("<div>");
        out.println("<FORM METHOD='POST' ACTION='updateBankApp'>");
        out.println("<CENTER><h1>GopherBank Transfer</h1><br>");
        out.println("<h2>Available accounts: </h2><br>");
        int i = 1;
        for(Account acct : acctVect){
            out.println("<h2>Account " +String.valueOf(i++)+"<br> ID: "+String.valueOf(acct.getCustomerID())+" | Type: "+acct.getacctType()+" | Balance: "+acct.getBalance()+"|</h2>"); 
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
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}


