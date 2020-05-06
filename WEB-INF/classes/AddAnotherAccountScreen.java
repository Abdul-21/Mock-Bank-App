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
        out.println("height: 400px;");
        out.println("width: 700px;");
        out.println("background:#ffcc33;");
        out.println("position: fixed;");
        out.println("top: 50%;");
        out.println("left: 50%;");
        out.println("margin-top: -500px;");
        out.println("margin-left: -200px;");
        out.println("}");
        out.println("</style>");
        out.println("<body>");
        out.println("<div>");
        out.println("<FORM METHOD='POST' ACTION='updateBankApp'>");
        out.println("<font COLOR='#7a0019'>");
        out.println("<CENTER><h1>Complete the following:</b1><br><br></h1>");
        out.println("</font>");
        out.println("<h2>");
        out.println("<label for='type-of-account'><b><font COLOR=#7a0019>Account Type:</font></b></label>");
        out.println("<select id='type-of-account' name='type-of-account'>");
        out.println("<option value='Checkings'>Checkings Account</option>");
        out.println("<option value='Savings'>Savings Account</option>");
        out.println("<option value='Money market'>Money market Account</option>");
        out.println("<option value='Retirement'>Retirement Account</option>");
        out.println("<option value='Brokerage'>Brokerage Account</option>");
        out.println("</select><br><br>");
        out.println("<label for='initial-deposit'><b><font COLOR=#7a0019>Initial Deposit:</font></b></label>");
        out.println("<input type='text' placeholder='Dollar Amount(ex: $00.00)' name='initial-deposit'><br><br>");
        out.println("<h2>");
        out.println("<INPUT TYPE='Submit' NAME='Submit' VALUE='Submit'></center>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
