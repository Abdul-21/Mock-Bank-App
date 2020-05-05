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


      //Metatags using Java Servlets
      
      out.println("<head>");
      response.setContentType("text/html");
      response.setCharacterEncoding("UTF-8");
      response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
      response.setHeader("Pragma", "no-cache");
      response.setDateHeader("expires", 0);
      response.setHeader("Expires", "0");
      out.println("</head>");

      //Equivalent in HTML
      //out.println("<META HTTP-Equiv='BackButton' Content='Width:30; Left:50; Top:50; Height:50; Visibility:Visible'>");
      /*
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        out.println("<head>");
        //out.println("<meta HTTP-Equiv='BackButton' Content='Visibility:Visible'>");
        out.println("<meta HTTP-Equiv='BackButton' Content='Width:30; Left:50; ImageUp:url('http://myaddress/back_up.gif'); ImageDown:url('http://myaddress/back_down.gif'); Visibility:Visible'>");
        out.println("<meta http-equiv='Cache-Control' content='no-cache, no-store, must-revalidate'>");
        out.println("<meta http-equiv='Pragma' content='no-cache'>");
        out.println("<meta http-equiv='Expires' content='0'>");
        out.println("</head>");
      */
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
      File tempFile = new File("../bin/altAcctFile.txt");
      if(tempFile.exists() == true){
        ObjectInputStream readAlt = new ObjectInputStream(new FileInputStream("altAcctFile.txt"));
        alternateAccount = (Account)readAlt.readObject();
      }
       //Now that Account object is populated, display information
        currentUserAccount.setacctType(currentUser.getacctType());
        userSession.setAttribute("currentUserAccount",currentUserAccount);
        userSession.setAttribute("altAcct",alternateAccount);
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
          out.println("<h2> Second Account Summary:"+alternateAccount.getacctType()+"</h2>");
          out.println("<h2> Account ID:"+alternateAccount.getCustomerID()+"</h2>");
          out.println("<h2> Account Balance: $"+alternateAccount.getBalance()+"</h2>");
          out.println("<h2> Transaction History: </h2>");
          out.println("<h2> Initial Deposit of $"+alternateAccount.getInitialDeposit()+"</h2>");
        }
        out.println("<button formaction='withdraw'>Withdraw</button>");
        out.println("<button formaction='deposit'>Deposit</button>");
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

  public void addAccountToExisting(HttpServletResponse response) throws IOException,ServletException{
    Account balternateAccount = new Account();
    PrintWriter out =response.getWriter();
    ObjectInputStream readAltAccount = new ObjectInputStream(new FileInputStream("altAcctFile.txt"));
    while(true){
      try{
        balternateAccount = (Account)readAltAccount.readObject();
      }catch(Exception e){
        break;
    }
  }
    out.println("<html>");
    out.println("<body>");
    out.println("<FORM METHOD='POST' ACTION='AddAnotherAccountToUser'>");
    out.println("<CENTER><h1>Complete the following:</b1>");

    out.println("<label for='type-of-account'><b><font COLOR='PURPLE'>Account Type:</font></b></label>");
    out.println("<select id='type-of-account' name='type-of-account'>");
    out.println("<option value='Checkings'>Checkings Account</option>");
    out.println("<option value='Savings'>Savings Account</option>");
    out.println("<option value='Money market'>Money market Account</option>");
    out.println("<option value='Retirement'>Retirement Account</option>");
    out.println("<option value='Brokerage'>Brokerage Account</option>");
    out.println("</select>");

    out.println("<label for='initial-deposit'><b><font COLOR='PURPLE'>Initial Deposit:</font></b></label>");
    out.println("<input type='text' placeholder='Dollar Amount(ex: $00.00)' name='initial-deposit'><br><br>");

    out.println("<INPUT TYPE='Submit' NAME='Submit' VALUE='Submit'></center>");
    out.println("</body>");
  }
}
