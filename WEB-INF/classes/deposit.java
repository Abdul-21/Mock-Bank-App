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
      userSession.setAttribute("action","Deposit");

      out.println("<html>");
      out.println("<body>");
      out.println("<title>Deposit</title>");
      out.println("<FORM METHOD='POST' ACTION='updateBankApp'>");
      out.println("<center><h2>"+UserN+", Please enter the following:</h2>");
      out.println("ID to deposit to: <INPUT TYPE=number Name='AcctID'>");
      out.println("<select id='choose-acct' name=choose-acct>");
      //out.println("<option value='"+accountObjType+"'>"+"Acct Type: "+accountObjType+" ID:"+Accountobj.getCustomerID()+"</option>");
      //out.println("<option value='"+altAcctObjType+"'>"+"Acct Type: "+altAcctObjType+" ID:"+altAcctobj.getCustomerID()+"</option>");
      for(Account acct:acctVect){
        if(acct.getCustomerName().equals(UserN)){
          out.println("<option value='acct1'>"+"Put into: "+acct.getCustomerID()+" with type: "+acct.getacctType()+"</option>");
        }
      }
      // out.println("<option value='acct1'>"+"Put into: "+accountObjID+" with type: "+accountObjType+"</option>");
      // out.println("<option value='acct1'>"+"Put into: "+altAcctObjID+" with type: "+altAcctObjType+"</option>");
      out.println("</select>");
      out.println("<label for='Amount'>Amount to Deposit: </b></label>");
      out.println("<input type='text' placeholder='Dollar Amount(ex: $00.00)' name='Amount'><br><br>");
      //out.println("Amount Desired: <INPUT TYPE=number Name='Amount'>");
      out.println("<INPUT TYPE='Submit' NAME='Deposit' VALUE='Submit'></center>");
      out.println("</body>");
}
}
