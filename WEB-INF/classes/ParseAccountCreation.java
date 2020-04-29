import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/*
PSUEDO CODE
https://tomcat.apache.org/tomcat-5.5-doc/servletapi/javax/servlet/http/HttpSession.html
https://tomcat.apache.org/tomcat-7.0-doc/servletapi/javax/servlet/http/HttpServletRequest.html
https://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletResponse.html
https://www.oxxus.net/tutorials/servlet/servlet-stepbystep

getParameter returns a STR
     -> Need to convert
1. Parse the following info and assign to variables
-First name STR
-Last name STR
-Date of birth STR
-Phone type, Phone number STR
-User name STR
-Password STR
-Account Type STR
-Initial deposit STR->DOUBLE
-ID type, ID number STR, STR->INT

2. Make sure that the account balance is tied to that user's username 
3. Differentiate between duplicate accounts using unique identifier (ID number)
4. Store user file into csv for viewing account summary
*/
public class ParseAccountCreation extends HttpServlet {

    String firstName, lastName, dateOfBirth, phoneType, phoneNumber, username, password, accountType, idType;
    double initialDeposit;
    int idNumber; 

    private static final long serialVersionUID = 102831973239L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        response.setContentType("text/html");
        PrintWriter output = response.getWriter();
        HttpSession userSession = request.getSession();
        
        //Checks for a valid session
        if(userSession == null){
          output.println("<HTML>");
          output.println("<HEAD>");
          output.println("<TITLE>User Session wasn't found</TITLE>");
          output.println("</HEAD>");
        }

        //Populate the variables with the user's input
        firstName = request.getParameter("First Name");
        lastName = request.getParameter("Last Name");
        dateOfBirth = request.getParameter("Date of Birth");
        phoneType = request.getParameter("Phone Type");
        phoneNumber = request.getParameter("Phone Number");
        username = request.getParameter("Username");
        password = request.getParameter("Password");
        accountType = request.getParameter("Type of Account");
        idType = request.getParameter("unique ID");
        initialDeposit = Double.parseDouble(request.getParameter("initial deposit"));
        idNumber = Integer.parseInt(request.getParameter("ID number"));

        //Stores all the user info into an object
        Object[] userInfo = new Object[11];
        userInfo[0] = firstName;
        userInfo[1] = lastName;
        userInfo[2] = dateOfBirth;
        userInfo[3] = phoneType;
        userInfo[4] = phoneNumber;
        userInfo[5] = username;
        userInfo[6] = password;
        userInfo[7] = accountType;
        userInfo[8] = idType;
        userInfo[9] = initialDeposit;
        userInfo[10] = idNumber;

        //Creates a CSV for storing user info
        try (PrintWriter writer = new PrintWriter(new File("..webapps/Lab2/userAccount.csv"))) {
            StringBuilder sb = new StringBuilder();
           
            //Apply the header to the CSV
            String[] header = {"First Name,","Last Name","Date Of Birth","Phone Type","Phone Number","Username","Password","Type Of Account","ID type","Initial Deposit","ID Number"};
            sb.append(header[0]);
            sb.append(",");
            for(int i = 1; i<=header.length-1;i++){
                sb.append(header[i]);
                if(i != 10){
                    sb.append(',');
                }
            }
            sb.append("\n");

            //Populate CSV with user info
            for(int j = 0; j < header.length; j++){
                sb.append(userInfo[j]);
                if(j != 10){
                    sb.append(',');
                }
            }
            sb.append("\n");
            writer.write(sb.toString());
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}