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
      PrintWriter out =response.getWriter();;
      out.println("<html>");
      out.println("<body>");
      out.println("<FORM METHOD='POST'>");
      if(userSession.getAttribute("action").equals("Withdraw")){
        withdraw(response, request);
      }
      else if(userSession.getAttribute("action").equals("Deposit")){
        Deposit(response,request);
      }
      else if(userSession.getAttribute("action").equals("Add User")){
        adduser(response,request);
      }
      else if(userSession.getAttribute("action").equals("Delete Account")){
        CloseAcct(response, request);
      }
      else if(userSession.getAttribute("action").equals("Transfer")){
        Transfer(response, request);
      }
      if(!userSession.getAttribute("action").equals("Add User")){
      out.println("<button formaction='withdraw'>Withdraw</button>");
      out.println("<button formaction='deposit'>Deposit</button>");
      out.println("<button formaction='TransferMoney'>Transfer Money</button>");
      out.println("<button formaction='deleteAccount'>Close Account</button>");
      out.println("<button formaction='AddAnotherAccountScreen'>Create another account</button>");
      out.println("</form>");
      out.println("</body>");
    }
    }
    public void CloseAcct(HttpServletResponse response,HttpServletRequest request) throws IOException{
      HttpSession userSession = request.getSession();
      String UserN = (String)userSession.getAttribute("currentUser");
      double AcctID=Double.parseDouble(request.getParameter("AcctID"));
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
    File acctFile = new File("acctFile.txt");
    FileOutputStream acctOutFile =  new FileOutputStream(acctFile);
    ObjectOutputStream acctWrite = new ObjectOutputStream(acctOutFile);
    for(Account acct:acctVect){
      if(acct.getCustomerName().equals(UserN) && acct.getCustomerID()==(AcctID)){
        acctVect.remove(acct);
      }
    }
    for(Account acct:acctVect){
      acctWrite.writeObject(acct);
    }
    acctWrite.close();
    showacct(UserN,response);
    }

    public void adduser(HttpServletResponse response,HttpServletRequest request) throws IOException{
      PrintWriter out =response.getWriter();
      HttpSession userSession = request.getSession();
      Random rand = new Random();
      Account newAccount = new Account();

      User Userobj= (User)userSession.getAttribute("currentUserObj");
      newAccount.setCustomerID(rand.nextInt(1000));
      String type = (String)request.getParameter("type-of-account");
      newAccount.setacctType(type);
      double amount = Double.parseDouble(request.getParameter("initial-deposit"));
      newAccount.setInitialDeposit(amount);
      newAccount.deposit(amount);
      newAccount.setCustomerName(Userobj.getUserName());

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
      return;
    }

    public void withdraw(HttpServletResponse response, HttpServletRequest request) throws IOException{
      HttpSession userSession = request.getSession();
      PrintWriter out =response.getWriter();
      double amount=Double.parseDouble(request.getParameter("Amount"));
      double AcctID=Double.parseDouble(request.getParameter("AcctID"));
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
      File acctFile = new File("acctFile.txt");
      FileOutputStream acctOutFile =  new FileOutputStream(acctFile);
      ObjectOutputStream acctWrite = new ObjectOutputStream(acctOutFile);
      for(Account acct:acctVect){
        if(acct.getCustomerName().equals(UserN) && acct.getCustomerID()==(AcctID)){
          if(acct.getBalance() < amount){
            out.println("<Center> <h1> Invalid! You don't have enough money in your account </h1></center>");
          }else{
            acct.withdraw(amount);
          }
          break;
        }
      }
      for(Account acct:acctVect){
        acctWrite.writeObject(acct);
      }
      acctWrite.close();
      showacct(UserN,response);
    }
    public void Deposit(HttpServletResponse response,HttpServletRequest request) throws IOException{
      HttpSession userSession = request.getSession();
      double amount=Double.parseDouble(request.getParameter("Amount"));
      double AcctID=Double.parseDouble(request.getParameter("AcctID"));
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
      File acctFile = new File("acctFile.txt");
      FileOutputStream acctOutFile =  new FileOutputStream(acctFile);
      ObjectOutputStream acctWrite = new ObjectOutputStream(acctOutFile);
      for(Account acct:acctVect){
        if(acct.getCustomerName().equals(UserN) && acct.getCustomerID()==(AcctID)){
          acct.deposit(amount);
          break;
        }
      }
      for(Account acct:acctVect){
        acctWrite.writeObject(acct);
      }
      acctWrite.close();
      showacct(UserN,response);
    }

    public void Transfer(HttpServletResponse response, HttpServletRequest request) throws IOException{
      HttpSession userSession = request.getSession();
         PrintWriter out = response.getWriter();
         int fromID =(int)Double.parseDouble(request.getParameter("fromID"));
         int toID =(int)Double.parseDouble(request.getParameter("toID"));
         double amountToTransfer = Double.parseDouble(request.getParameter("Amount"));
         String UserN = (String)userSession.getAttribute("currentUser");

         Vector <Account> acctVect = new Vector<Account>();
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
         File acctFile = new File("acctFile.txt");
         FileOutputStream acctOutFile =  new FileOutputStream(acctFile);
         ObjectOutputStream acctWrite = new ObjectOutputStream(acctOutFile);

         for(Account acct:acctVect){
           int chosenID = (int)acct.getCustomerID();
           if(chosenID == fromID){
             double bal = acct.getBalance();
             if(amountToTransfer > bal){
               out.println("<html>");
               out.println("<body>");
               out.println("<CENTER><h1>INSUFFICIENT BALANCE</b1>");
               out.println("<INPUT TYPE=Button onClick=\"parent.location = 'index.html'\" value=\"Logout\"><br><br");
               out.println("</body>");
               return;
             }
             acct.withdraw(amountToTransfer);
           }
           else if(chosenID == toID){
             acct.deposit(amountToTransfer);
           }
         }
         for(Account acct:acctVect){
           acctWrite.writeObject(acct);
         }
         acctWrite.close();
         showacct(UserN,response);
    }
    private void writeToFile(Account newAccount) throws IOException{
        File acctFile = new File("acctFile.txt");//object files for other class to get account data

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
      out.println("<CENTER><h1>Welcome "+userName+"</b1>");
      double Total=0;
      for(Account acct:acctVect){
        if(acct.getCustomerName().equals(userName)){
          showmenu(acct,response);
          Total+=acct.getBalance();
        }
      }
      out.println("<br><h2>Sum of all Balance: $"+Total+"</h2>");

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
}
