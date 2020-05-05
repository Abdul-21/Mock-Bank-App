import bank.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddAnotherAccountToUser extends HttpServlet{
    private static final long serialVersionUID = 102831973239L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException
    {
        PrintWriter out =response.getWriter();
        HttpSession userSession = request.getSession();
        Random rand = new Random();
        Account newAccount = new Account();

        User Userobj= (User)userSession.getAttribute("currentUserObj");
        Account selectedAccount = (Account)userSession.getAttribute("currentUserAccount");
        newAccount.setCustomerID(rand.nextInt(1000));
        String type = (String)request.getParameter("type-of-account");
        newAccount.setacctType(type);
        double amount = Double.parseDouble(request.getParameter("initial-deposit"));
        newAccount.setInitialDeposit(amount);
        newAccount.deposit(amount);
        newAccount.setCustomerName(Userobj.getFirstName() + " " + Userobj.getLastName());

        //userSession.setAttribute("newAccount",newAccount);
        writeToFile(newAccount);
        out.println("<html>");
        out.println("<body>");
        out.println("<CENTER>");
        out.println("<h1> <font COLOR=\"PURPLE\">Adding Account Successful!</font>");
        out.println("</h1><br>");
        out.println("<h1> <font COLOR=\"BLUE\"> Type of Account added:" + newAccount.getacctType() + " </font></font>");
        out.println("<br>");
        out.println("<INPUT TYPE=Button onClick=\"parent.location = 'index.html'\" value=\"Login\">");
        out.println("<body>");
        out.println("</html>");

    }
    private void writeToFile(Account newAccount) throws IOException{
        File acctFile = new File("altAcctFile.txt");//object files for other class to get account data

        FileOutputStream acctOutFile =  new FileOutputStream(acctFile,true);

        if(acctFile.length() == 0){ // if files existed when write to current file
          ObjectOutputStream acctWrite=new ObjectOutputStream(acctOutFile);

          acctWrite.writeObject(newAccount);

          acctWrite.close();
          return;
        }

        AppendingObjectOutputStream acctWrite = new AppendingObjectOutputStream(acctOutFile);

        acctWrite.writeObject(newAccount);

        acctWrite.close();
      }

}
