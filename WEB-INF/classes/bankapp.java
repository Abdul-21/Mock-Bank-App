import bank.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;



public class bankapp extends HttpServlet {
  private static final long serialVersionUID = 102831973239L;

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
  }
  public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException,FileNotFoundException,ServletException
	{
    PrintWriter out =response.getWriter();
    try{
      HttpSession userSession = request.getSession();
      String UserN = request.getParameter("username");
      String passWord = request.getParameter("Password");
      userSession.setAttribute("currentUser",UserN);

      HashMap<String, User> UserHmap = new HashMap<String, User>(); //Hold username, and user object with info.
      ObjectInputStream UserObjects = new ObjectInputStream(new FileInputStream("userFile.txt")); //Read profile
      while(true){
        try{
          User Objs= (User)UserObjects.readObject();
          UserHmap.put(Objs.getUserName(),Objs);
        }catch(Exception e){
          break;
      }
    }
      if(UserHmap.get(UserN)!=null){
        User currentUser= UserHmap.get(UserN);
        if(!(currentUser.getPassword().equals(passWord) && currentUser.getUserName().equals(UserN))){
          throw new IllegalArgumentException("Unable To Recognized Account Credentials");
        }else{
          showacct(UserN,response);
        }
        userSession.setAttribute("currentUserObj",currentUser);
      }else{
        throw new IllegalArgumentException("User Account Was Not Found");
      }
    }catch(Exception e){
          out.println("<html>");
          out.println("<body>");
          out.println("<img src=\"https://i.kym-cdn.com/photos/images/facebook/001/517/016/cf1.jpg\" alt=\"Waling out here\">");
          out.println("<br>Exception thrown:"+e+"<br>");
          out.println("</body>");
    			return;
    }
  }

  public void showacct(String userName, HttpServletResponse response) throws FileNotFoundException, IOException{
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

    // currentUserAccount.setacctType(currentUser.getacctType());
    PrintWriter out = response.getWriter();
    double Total=0;
    out.println("<html>");
    out.println("<body>");
    out.println("<FORM METHOD='POST'>");
    out.println("<CENTER><h1>User account was Found!<br> Welcome "+userName+"</b1>");
    for(Account acct:acctVect){
      if(acct.getCustomerName().equals(userName)){
        showmenu(acct,response);
        Total+=acct.getBalance();
      }
    }
    out.println("<br><h2>Sum of all Balance: $"+Total+"</h2>");
    out.println("<button formaction='withdraw'>Withdraw</button>");
    out.println("<button formaction='deposit'>Deposit</button>");
    out.println("<button formaction='TransferMoney'>Transfer Money</button>");
    out.println("<button formaction='AddAnotherAccountScreen'>Create another account</button>");
    out.println("</form>");
    out.println("</body>");
  }
  public void showmenu(Account acct,HttpServletResponse response) throws IOException{
    PrintWriter out = response.getWriter();
    out.println("<h2> Account Summary:"+acct.getacctType()+"</h2>");
    out.println("<h2> Account ID:"+acct.getCustomerID()+"</h2>");
    out.println("<h2> Account Balance: $"+acct.getBalance()+"</h2>");
    out.println("<h2> Transaction History: </h2>");
    out.println("<h2> Initial Deposit of $"+acct.getInitialDeposit()+"</h2>");
    out.println("<br>");
  }
  // public void addAccountToExisting(HttpServletResponse response) throws IOException,ServletException{
  //   Account balternateAccount = new Account();
  //   PrintWriter out =response.getWriter();
  //   ObjectInputStream readAltAccount = new ObjectInputStream(new FileInputStream("altAcctFile.txt"));
  //   while(true){
  //     try{
  //       balternateAccount = (Account)readAltAccount.readObject();
  //     }catch(Exception e){
  //       break;
  //   }
  // }
  //   out.println("<html>");
  //   out.println("<body>");
  //   out.println("<FORM METHOD='POST' ACTION='AddAnotherAccountToUser'>");
  //   out.println("<CENTER><h1>Complete the following:</b1>");
  //
  //   out.println("<label for='type-of-account'><b><font COLOR='PURPLE'>Account Type:</font></b></label>");
  //   out.println("<select id='type-of-account' name='type-of-account'>");
  //   out.println("<option value='Checkings'>Checkings Account</option>");
  //   out.println("<option value='Savings'>Savings Account</option>");
  //   out.println("<option value='Money market'>Money market Account</option>");
  //   out.println("<option value='Retirement'>Retirement Account</option>");
  //   out.println("<option value='Brokerage'>Brokerage Account</option>");
  //   out.println("</select>");
  //
  //   out.println("<label for='initial-deposit'><b><font COLOR='PURPLE'>Initial Deposit:</font></b></label>");
  //   out.println("<input type='text' placeholder='Dollar Amount(ex: $00.00)' name='initial-deposit'><br><br>");
  //
  //   out.println("<INPUT TYPE='Submit' NAME='Submit' VALUE='Submit'></center>");
  //   out.println("</body>");
  // }
}
