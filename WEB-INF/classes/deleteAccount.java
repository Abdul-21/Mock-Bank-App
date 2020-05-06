import bank.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class deleteAccount extends HttpServlet{
private static final long serialVersionUID = 1028313239L;

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

      userSession.setAttribute("action","Delete Account");
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
      out.println("top: 50%;");
      out.println("left: 50%;");
      out.println("margin-top: -500px;");
      out.println("margin-left: -200px;");
      out.println("}");
      out.println("</style>");
      out.println("<body>");
      out.println("<div>");
      out.println("<FORM METHOD='POST' ACTION='updateBankApp'>");
      for(Account acct:acctVect){
        if(acct.getCustomerName().equals(UserN)){
          out.println("<h3>Account Type: "+acct.getacctType()+" with ID: "+String.valueOf(acct.getCustomerID()) + " and a balance of "+"$"+String.valueOf(acct.getBalance())+"</h3><br>");
        }
      }
      out.println("<h3>ID of Acccount you would like to close: <INPUT TYPE=number Name='AcctID'></h3>");
      out.println("<INPUT TYPE='Submit' NAME='Submit' VALUE='Submit'></center>");
      out.println("</div>");
      out.println("</body>");
      out.println("</html>");
    }

}
