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
      PrintWriter out =response.getWriter();
      if(userSession.getAttribute("action").equals("Withdraw")){
        withdraw(response, request);
      }
      else if(userSession.getAttribute("action").equals("Deposit")){
        Deposit(response,request);
      }
      // else if(userSession.getAttribute("action").equals("Close Account"))
      //   CloseAcct(response, request);
      // else if(userSession.getAttribute("action").equals("New Account"))
      //   OpenAcct(request);
      else if(userSession.getAttribute("action").equals("Transfer")){
        Transfer(response, request);
      }
    }

    public void withdraw(HttpServletResponse response, HttpServletRequest request) throws IOException{
      HttpSession userSession = request.getSession();
      Account Accountobj=(Account)userSession.getAttribute("currentUserAccount");
      User Userobj= (User)userSession.getAttribute("currentUserObj");
      Account altAcctobj=(Account)userSession.getAttribute("altAcct");
      double amount=Double.parseDouble(request.getParameter("Amount"));
      String AcctID= request.getParameter("AcctID");
      if(AcctID.contains(String.valueOf(Accountobj.getCustomerID()))){
      Accountobj.withdraw(amount);
      showmenu(Accountobj,Userobj,response,request);
      }else{
        altAcctobj.withdraw(amount);
        showmenu(altAcctobj,Userobj,response,request);
      }
    }
    public void Deposit(HttpServletResponse response,HttpServletRequest request) throws IOException{
      HttpSession userSession = request.getSession();
      Account Accountobj=(Account)userSession.getAttribute("currentUserAccount");
      Account altAcctobj=(Account)userSession.getAttribute("altAcct");
      User Userobj= (User)userSession.getAttribute("currentUserObj");
      double amount=Double.parseDouble(request.getParameter("Amount"));
      String AcctID= request.getParameter("AcctID");
      if(AcctID.contains(String.valueOf(Accountobj.getCustomerID()))){
      Accountobj.deposit(amount);
      showmenu(Accountobj,Userobj,response,request);
      }else{
        altAcctobj.deposit(amount);
        showmenu(altAcctobj,Userobj,response,request);
      }
    }

    public void Transfer(HttpServletResponse response, HttpServletRequest request) throws IOException{
      HttpSession userSession = request.getSession();
      PrintWriter out =response.getWriter();
      Account Accountobj=(Account)userSession.getAttribute("currentUserAccount");
      Account altAcctobj=(Account)userSession.getAttribute("altAcct");
      User Userobj= (User)userSession.getAttribute("currentUserObj");
      

      int altAcctObjID = (int)altAcctobj.getCustomerID();
      int AccountObjID = (int)Accountobj.getCustomerID();

      int fromID =Integer.parseInt(request.getParameter("fromID"));
      int toID =Integer.parseInt(request.getParameter("toID"));
      double amountToTransfer = Double.parseDouble(request.getParameter("Amount"));

      //Assign ID to proper account
      if(fromID == AccountObjID){
        double bal = Accountobj.getBalance();
        //Ensures that there is sufficient funds
        
        if(amountToTransfer > bal){
          out.println("<html>");
          out.println("<body>");
          out.println("<CENTER><h1>INSUFFICIENT BALANCE</b1>");
          out.println("<INPUT TYPE=Button onClick=\"parent.location = 'index.html'\" value=\"Logout\"><br><br");
          out.println("</body>");
          return;
        }
        
        //There is enough funds now to transfer
        
        Accountobj.withdraw(amountToTransfer);
        altAcctobj.deposit(amountToTransfer);
        Accountobj.setCustomerID(fromID);
      }
      else if(fromID == altAcctObjID){
        double bal = altAcctobj.getBalance();
        if(amountToTransfer > bal){
          out.println("<html>");
          out.println("<body>");
          out.println("<CENTER><h1>INSUFFICIENT BALANCE</b1>");
          out.println("<INPUT TYPE=Button onClick=\"parent.location = 'index.html'\" value=\"Logout\"><br><br");
          out.println("</body>");
          return;
        }
        altAcctobj.withdraw(amountToTransfer);
        Accountobj.deposit(amountToTransfer);
        altAcctobj.setCustomerID(fromID);

      }
      writeToFile(Userobj, Accountobj);
      writeToFile(Userobj, altAcctobj);
      showmenu(altAcctobj,Userobj,response,request);
      /*
      else if(fromID == altAcctObjID){
        double bal = altAcctobj.getBalance();
      
        if(amountToTransfer > bal){
          out.println("INSUFFICIENT BALANCE");
        }
        
        
        altAcctobj.withdraw(amountToTransfer);
        Accountobj.deposit(amountToTransfer);
        
        showmenu(Accountobj,Userobj,response);
        */
      
      //writeToFile(Userobj, Accountobj);
      //writeToFile(Userobj, altAcctobj);

    }
    public void showmenu(Account currentUserAccount, User currentUser,HttpServletResponse response, HttpServletRequest request)throws IOException{
      PrintWriter out =response.getWriter();
      HttpSession userSession = request.getSession();

      String UserN = (String)userSession.getAttribute("currentUser");
      User Userobj= (User)userSession.getAttribute("currentUserObj");
      Account Accountobj=(Account)userSession.getAttribute("currentUserAccount");
      Account altAcctobj=(Account)userSession.getAttribute("altAcct");

      Account alternateAccount = new Account();
      File tempFile = new File("../bin/altAcctFile.txt");
      if(tempFile.exists() == true){
        ObjectInputStream readAlt = new ObjectInputStream(new FileInputStream("altAcctFile.txt"));
        try{
        alternateAccount = (Account)readAlt.readObject();
      }catch(Exception e){;}
      }

      out.println("<html>");
      out.println("<body>");
      out.println("<FORM METHOD='POST'>");
      out.println("<CENTER><h1>User account was Found!<br> Welcome "+currentUser.getFirstName()+"</b1>");
      out.println("<h2> Account Summary:"+Accountobj.getacctType()+"</h2>");
      out.println("<h2> Account ID:"+Accountobj.getCustomerID()+"</h2>");
      out.println("<h2> Account Balance: $"+Accountobj.getBalance()+"</h2>");
      out.println("<h2> Transaction History: </h2>");
      out.println("<h2> Initial Deposit of $"+Accountobj.getInitialDeposit()+"</h2>");
      out.println("<br>");
      if(alternateAccount.getInitialDeposit() != 0){
        out.println("<h2> Second Account Summary:"+alternateAccount.getacctType()+"</h2>");
        out.println("<h2> Account ID:"+alternateAccount.getCustomerID()+"</h2>");
        out.println("<h2> Account Balance: $"+currentUserAccount.getBalance()+"</h2>");
        out.println("<h2> Transaction History: </h2>");
        out.println("<h2> Initial Deposit of $"+alternateAccount.getInitialDeposit()+"</h2>");
      }
      out.println("<button formaction='withdraw'>Withdraw</button>");
      out.println("<button formaction='deposit'>Deposit</button>");
      out.println("<button formaction='TransferMoney'>Transfer Money</button>");
      out.println("<button formaction='AddAnotherAccountScreen'>Create another account</button>");
      out.println("</form>");
      out.println("</body>");
      out.println("</body>");
    }

    private void overWriteAccount(Account accountToOverwrite, HttpServletResponse response, HttpServletRequest request) throws IOException{

      HttpSession userSession = request.getSession();
      PrintWriter out = response.getWriter();
      Account Accountobj=(Account)userSession.getAttribute("currentUserAccount");
      User Userobj= (User)userSession.getAttribute("currentUserObj");
      Account altAcctobj=(Account)userSession.getAttribute("altAcct");

      File selectedFile = new File(" ");
      if(accountToOverwrite == Accountobj){
        selectedFile = new File("userFile.txt");//object files for other class to get user data
      }
      else if(accountToOverwrite == altAcctobj){
        selectedFile = new File("altAcctFile.txt");//object files for other class to get account data
      }

      FileOutputStream selectedOutFile =  new FileOutputStream(selectedFile,true);

      AppendingObjectOutputStream selectWrite = new AppendingObjectOutputStream(selectedOutFile);

      selectWrite.writeObject(accountToOverwrite);
      selectWrite.close();
    }
    
  private void writeToFile(User newUser, Account newAccount) throws IOException{
    File userFile = new File("altAcctFile.txt");//object files for other class to get user data
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
