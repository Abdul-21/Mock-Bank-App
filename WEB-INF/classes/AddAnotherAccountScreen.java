import bank.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddAnotherAccountScreen extends HttpServlet{

    private static final long serialVersionUID = 201L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException
    {

        PrintWriter out =response.getWriter();
        HttpSession userSession = request.getSession();
        String userName = request.getParameter("Username");
        userSession.setAttribute("Username",userName);
        userSession.setAttribute("action","Add User");

        out.println("<html>");
        out.println("<body>");
        out.println("<FORM METHOD='POST' ACTION='updateBankApp'>");
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
