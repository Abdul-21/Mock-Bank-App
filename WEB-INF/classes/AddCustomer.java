import bank.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddCustomer extends HttpServlet{
private static final long serialVersionUID = 102831973239L;

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doPost(request,response);
}
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException
    {
      User newUser= new User();
      Account newAccount= new Account();
      Random rand = new Random();
      double id= rand.nextInt(1000);


      newUser.setFirstName(request.getParameter("First Name"));
      newUser.setLastName(request.getParameter("Last Name"));
      newUser.setacctType(request.getParameter("Type of Account"));
      newUser.setUserName(request.getParameter("Username"));
      newUser.setPassword(request.getParameter("Password"));
      newUser.setacccountID(id);

      double amount = Double.parseDouble(request.getParameter("initial deposit"));
      newAccount.deposit(amount);
      newAccount.setInitialDeposit(amount);
      newAccount.setCustomerID(id);
      newAccount.setCustomerName(request.getParameter("Username"));
      newAccount.setacctType(request.getParameter("Type of Account"));
      writeToFile(newUser,newAccount);

      PrintWriter out = response.getWriter();
      out.println("<head>");
      response.setContentType("text/html");
      response.setCharacterEncoding("UTF-8");
      response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
      response.setHeader("Pragma", "no-cache");
      response.setDateHeader("expires", 0);
      response.setHeader("Expires", "0");
      out.println("</head>");
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
      out.println("<CENTER>");
      out.println("<font COLOR='#7a0019'>");
      out.println("<h1>Registration Successful!</h1><br>");
      out.println("<h1>Welcome to the Club:"+newAccount.getCustomerName()+"</font></h1><br>");
      out.println("<INPUT TYPE=Button onClick=\"parent.location = 'index.html'\" value=\"Login\">");
      out.println("</CENTER>");
      out.println("</div>");
      out.println("</body>");
      out.println("</html>");

  }

  private void writeToFile(User newUser, Account newAccount) throws IOException{
    File userFile = new File("userFile.txt");//object files for other class to get user data
    File acctFile = new File("acctFile.txt");//object files for other class to get account data

    FileOutputStream userOutFile =  new FileOutputStream(userFile,true);
    FileOutputStream acctOutFile =  new FileOutputStream(acctFile,true);

    if(userFile.length() == 0){ // if files existed when write to current file
      ObjectOutputStream userWrite=new ObjectOutputStream(userOutFile);
      ObjectOutputStream acctWrite=new ObjectOutputStream(acctOutFile);

      userWrite.writeObject(newUser);
      acctWrite.writeObject(newAccount);

      userWrite.close();
      acctWrite.close();
      return;
    }

    AppendingObjectOutputStream userWrite = new AppendingObjectOutputStream(userOutFile);
    AppendingObjectOutputStream acctWrite = new AppendingObjectOutputStream(acctOutFile);

    userWrite.writeObject(newUser);
    acctWrite.writeObject(newAccount);

    userWrite.close();
    acctWrite.close();
  }
}
