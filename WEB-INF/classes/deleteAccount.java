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
      userSession.setAttribute("action","Delete Account");
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


      out.println("<html>");
      out.println("<body>");
      out.println("<FORM METHOD='POST' ACTION='updateBankApp'>");
      for(Account acct:acctVect){
        if(acct.getCustomerName().equals(UserN)){
          out.println("<h3>Account Type: "+acct.getacctType()+" with ID: "+String.valueOf(acct.getCustomerID()) + " and a balance of "+"$"+String.valueOf(acct.getBalance())+"</h3><br>");
        }
      }
      out.println("<h3>ID of Acccount you would like to close: <INPUT TYPE=number Name='AcctID'></h3>");
      out.println("<INPUT TYPE='Submit' NAME='Submit' VALUE='Submit'></center>");
      out.println("</body>");

    }

}
