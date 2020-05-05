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
			throws IOException,ServletException
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
        }
        userSession.setAttribute("currentUserObj",currentUser);
        //Reads from acctFile.txt into Account object.
        Account currentUserAccount = new Account();
        HashMap<Long, Account> AccountHmap = new HashMap<Long, Account>(); //Hold customer ID, and account object info.
        ObjectInputStream readAccount = new ObjectInputStream(new FileInputStream("acctFile.txt"));
        while(true){
          try{
            currentUserAccount = (Account)readAccount.readObject();
            AccountHmap.put(currentUserAccount.getCustomerID(),currentUserAccount);
          }catch(Exception e){
            break;
        }
      }

      Account alternateAccount = new Account();
      ObjectInputStream readAltAccount = new ObjectInputStream(new FileInputStream("altAcctFile.txt"));
      while(true){
        try{
          alternateAccount = (Account)readAltAccount.readObject();
        }catch(Exception e){
          break;
      }
    }
       //Now that Account object is populated, display information
        userSession.setAttribute("currentUserAccount",currentUserAccount);
        out.println("<html>");
        out.println("<body>");
        out.println("<FORM METHOD='POST'>");
        out.println("<CENTER><h1>User account was Found!<br> Welcome "+currentUser.getFirstName()+"</b1>");
        out.println("<h2> Account Summary:"+currentUser.getacctType()+"</h2>");
        out.println("<h2> Account ID:"+currentUserAccount.getCustomerID()+"</h2>");
        out.println("<h2> Account Balance: $"+currentUserAccount.getBalance()+"</h2>");
        out.println("<h2> Transaction History: </h2>");
        out.println("<h2> Initial Deposit of $"+currentUserAccount.getInitialDeposit()+"</h2>");
        out.println("<br>");
        if(alternateAccount.getInitialDeposit() != 0){
          out.println("<h2> Second Account Summary:"+currentUser.getacctType()+"</h2>");
          out.println("<h2> Account ID:"+alternateAccount.getCustomerID()+"</h2>");
          out.println("<h2> Account Balance: $"+alternateAccount.getBalance()+"</h2>");
          out.println("<h2> Transaction History: </h2>");
          out.println("<h2> Initial Deposit of $"+alternateAccount.getInitialDeposit()+"</h2>");
        }
        out.println("<button formaction='withdraw'>Withdraw</button>");
        out.println("<button formaction='TransferMoney'>Transfer Money</button>");
        out.println("<button formaction='AddAnotherAccountScreen'>Create another account</button>");
        out.println("</form>");
        out.println("</body>");













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
}
