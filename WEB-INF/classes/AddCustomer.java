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

      newUser.setFirstName(request.getParameter("First Name"));
      newUser.setLastName(request.getParameter("Last Name"));
      newUser.setacctType(request.getParameter("Type of Account"));
      newUser.setUserName(request.getParameter("Username"));
      newUser.setPassword(request.getParameter("Password"));

<<<<<<< Updated upstream
      newAccount.deposit(Double.parseDouble(request.getParameter("initial deposit")));
=======

      double amount = Double.parseDouble(request.getParameter("initial deposit"));
      newAccount.deposit(amount);
      newAccount.setInitialDeposit(amount);
>>>>>>> Stashed changes
      newAccount.setCustomerID(rand.nextInt(1000));
      newAccount.setCustomerName(newUser.getFirstName()+" "+newUser.getLastName());

      logging log= new logging();
      log.logact(request.getParameter("First Name")+" Created "+request.getParameter("Type of Account")+" With Account ID "+newAccount.getCustomerID());

      writeToFile(newUser,newAccount);

      PrintWriter out = response.getWriter();
      out.println("<html>");
      out.println("<body>");
      out.println("<CENTER>");
      out.println("<h1> <font COLOR=\"PURPLE\"> Registration Successful!</font>");
      out.println("</h1><br>");
      out.println("<h1> <font COLOR=\"BLUE\"> Welcome to the Club:</font>"+newAccount.getCustomerName()+"</font>");
      out.println("<br>");
      out.println("<INPUT TYPE=Button onClick=\"parent.location = 'index.html'\" value=\"Login\">");
      out.println("<body>");
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
  public class AppendingObjectOutputStream extends ObjectOutputStream {

    public AppendingObjectOutputStream(OutputStream out) throws IOException {
      super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
      // do not write a header, but reset:
      // this line added after another question
      // showed a problem with the original
      reset();
    }

  }
}
