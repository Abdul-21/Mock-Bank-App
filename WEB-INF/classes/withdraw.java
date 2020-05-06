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
      Vector <Account> acctVect = new Vector<Account>(); //Hold username, and user object with info.
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
      out.println("<head>");
      response.setContentType("text/html");
      response.setCharacterEncoding("UTF-8");
      response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
      response.setHeader("Pragma", "no-cache");
      response.setDateHeader("expires", 0);
      response.setHeader("Expires", "0");
      out.println("</head>");
      userSession.setAttribute("action","Withdraw");
      
      out.println("<html>");
      out.println("<style>");
      out.println("body {");
      out.println("background-image: url('portal.jpg');");
      out.println("background-repeat: no-repeat;");
      out.println("}");
      out.println("div {");
      out.println("height: 900px;");
      out.println("width: 550px;");
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
      out.println("<font COLOR='#7a0019'> <CENTER><h1>GopherBank Withdraw</font></h1><br>");
      out.println("<FORM METHOD='POST' ACTION='updateBankApp'>");
      out.println("<h3>Available accounts for "+UserN+": </h3><br>");
      int i = 1;
      for(Account acct:acctVect){
        if(acct.getCustomerName().equals(UserN)){
          out.println("<h4><font COLOR='#7a0019'>Account " +String.valueOf(i++)+"</font><br> ID: "+String.valueOf((int)acct.getCustomerID())+" | Type: "+acct.getacctType()+" | Balance: "+acct.getBalance()+"|</h4>");
        }
      }
      
      out.println("<center><h3>Please complete the following:</h3>");
      out.println("<font COLOR='#7a0019'>");
      out.println("<h4>ID to withdraw from: <INPUT TYPE=number Name='AcctID'></h4><br>");
      out.println("<h4><label for='Amount'>Amount to withdraw: </label></h4>");
      out.println("<h4><input type='text' placeholder='Dollar Amount(ex: $00.00)' name='Amount'></h4><br>");
      out.println("<INPUT TYPE='Submit' NAME='Deposit' VALUE='Submit'></center>");
      out.println("</font>");
      out.println("</div>");
      out.println("</body>");
      out.println("</html>");

  }
}
