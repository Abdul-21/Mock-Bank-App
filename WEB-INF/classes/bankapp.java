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
<<<<<<< Updated upstream
=======


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
>>>>>>> Stashed changes
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
       //Now that Account object is populated, display information
        userSession.setAttribute("currentUserAccount",currentUserAccount);
        out.println("<html>");
        out.println("<body>");
        out.println("<FORM METHOD='POST' ACTION='withdraw'>");
        out.println("<CENTER><h1>User account was Found!<br> Welcome "+currentUser.getFirstName()+"</b1>");
        out.println("<h2> Account Summary:"+currentUser.getacctType()+"</h2>");
        currentUserAccount.deposit(1.00);
        out.println("<h2> Account Balance: $"+currentUserAccount.getBalance()+"</h2>");
        out.println("<h2> Transaction History: </h2>");
        out.println("<h2> Initial Deposit of $"+currentUserAccount.getInitialDeposit()+"</h2>");
<<<<<<< Updated upstream
        out.println("<INPUT TYPE='Submit' NAME='withdraw' VALUE='withdraw'>");
=======
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
        out.println("<button formaction='AddCustomer'>Create another account</button>");
        out.println("</form>");
>>>>>>> Stashed changes
        out.println("</body>");
        //Now to display actions to take
        //Transfer between account - if sufficient balance
        //History of transaction
        //View balances (name: $ amount, optional ID/key)
        //Delete Account











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
