import bank.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class updateBankApp extends HttpServlet{
private static final long serialVersionUID = 102831973239L;

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doPost(request,response);
}
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException
    {
      HttpSession userSession = request.getSession();
      Account Accountobj=(Account)userSession.getAttribute("currentUserAccount");
      User Userobj= (User)userSession.getAttribute("currentUserObj");
      double amount=Double.parseDouble(request.getParameter("Amount"));
<<<<<<< Updated upstream
=======
      String AcctID= request.getParameter("AcctID");
      logging log= new logging();
      if(AcctID.contains(String.valueOf(Accountobj.getCustomerID()))){
>>>>>>> Stashed changes
      Accountobj.withdraw(amount);
      log.logact(Userobj.getFirstName()+" Withdrawn Money Of Amount "+amount+" From "+AcctID);
      showmenu(Accountobj,Userobj,response);
<<<<<<< Updated upstream
=======
      }else{
        altAcctobj.withdraw(amount);
        log.logact(Userobj.getFirstName()+" Withdrawn Money Of Amount "+amount+" From "+AcctID);
        showmenu(altAcctobj,Userobj,response);
      }
    }
    public void Deposit(HttpServletResponse response,HttpServletRequest request) throws IOException{
      HttpSession userSession = request.getSession();
      Account Accountobj=(Account)userSession.getAttribute("currentUserAccount");
      Account altAcctobj=(Account)userSession.getAttribute("altAcct");
      User Userobj= (User)userSession.getAttribute("currentUserObj");
      double amount=Double.parseDouble(request.getParameter("Amount"));
      String AcctID= request.getParameter("AcctID");
      logging log= new logging();
      if(AcctID.contains(String.valueOf(Accountobj.getCustomerID()))){
      log.logact(Userobj.getFirstName()+" deposit Money Of Amount "+amount+" From "+AcctID);
      Accountobj.deposit(amount);
      showmenu(Accountobj,Userobj,response);
      }else{
        log.logact(Userobj.getFirstName()+" deposit Money Of Amount "+amount+" From "+AcctID);
        altAcctobj.deposit(amount);
        showmenu(altAcctobj,Userobj,response);
      }
>>>>>>> Stashed changes
    }
    public void showmenu(Account currentUserAccount, User currentUser,HttpServletResponse response)throws IOException{
      PrintWriter out =response.getWriter();
      out.println("<html>");
      out.println("<body>");
      out.println("<FORM METHOD='POST' ACTION='withdraw'>");
      out.println("<CENTER>Welcome "+currentUser.getFirstName()+"</b1>");
      out.println("<h2> Account Summary:"+currentUser.getacctType()+"</h2>");
      out.println("<h2> Account Balance: $"+currentUserAccount.getBalance()+"</h2>");
      out.println("<h2> Transaction History: </h2>");
      out.println("<h2> Initial Deposit of $"+currentUserAccount.getInitialDeposit()+"</h2>");
      out.println("<INPUT TYPE='Submit' NAME='withdraw' VALUE='withdraw'>");
      out.println("</body>");
    }
}
