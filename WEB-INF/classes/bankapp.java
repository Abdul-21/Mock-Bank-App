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

      out.println("<head>");
      response.setContentType("text/html");
      response.setCharacterEncoding("UTF-8");
      response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
      response.setHeader("Pragma", "no-cache");
      response.setDateHeader("expires", 0);
      response.setHeader("Expires", "0");
      out.println("</head>");

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
          out.println("<br><h3>Exception thrown:"+e+"</h3><br>");
          out.println("<a href='javascript:history.back()'>Go Back</a>");
          out.println("<img src=\"https://image.flaticon.com/icons/svg/569/569575.svg\"  alt=\"ERROR IMAGE\" style=\"width:500px;height:500px;\">");
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
    out.println("<style>");
    out.println("body {");
    out.println("background-image: url('portal.jpg');");
    out.println("background-repeat: no-repeat;");
    out.println("}");
    out.println("div {");
    out.println("height: 900px;");
    out.println("width: 700px;");
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
    out.println("<FORM METHOD='POST'>");
    out.println("<font COLOR='#7a0019'>");
    out.println("<CENTER><h1>Welcome "+userName+"!"+"</h1>");
    out.println("<h3>NOTE: Menu will be at bottom if too many accounts to fit</h3>");
    out.println("</font>");
    for(Account acct:acctVect){
      if(acct.getCustomerName().equals(userName)){
        showmenu(acct,response);
        Total+=acct.getBalance();
      }
    }
    out.println("<font COLOR='#7a0019'>");
    out.println("<h2>Sum of all Balance: $"+Total+"</h2>");
    out.println("</font>");
    out.println("<button formaction='withdraw'>Withdraw</button>");
    out.println("<button formaction='deposit'>Deposit</button>");
    out.println("<button formaction='TransferMoney'>Transfer Money</button>");
    out.println("<button formaction='deleteAccount'>Close Account</button>");
    out.println("<button formaction='AddAnotherAccountScreen'>Create another account</button>");
    out.println("<button formaction='checkHistory'>Check account history</button>");
    out.println("<br><button formaction='index.html'>Log Out</button>");
    out.println("</form>");
    out.println("</div>");
    out.println("</body>");
  }
  public void showmenu(Account acct,HttpServletResponse response) throws IOException{
    PrintWriter out = response.getWriter();
    out.println("<h2> Account Summary:<font COLOR='#7a0019'>"+acct.getacctType()+"</font></h2>");
    out.println("<h2> Account ID:<font COLOR='#7a0019'>"+String.valueOf((int)acct.getCustomerID())+"</font></h2>");
    out.println("<h2> Account Balance:<font COLOR='#7a0019'>$"+acct.getBalance()+"</font></h2>");
    out.println("<h2> Transaction History:</h2>");
    out.println("<h2> Initial Deposit of <font COLOR='#7a0019'>$"+acct.getInitialDeposit()+"</font></h2>");
    out.println("<br>");
  }
}
