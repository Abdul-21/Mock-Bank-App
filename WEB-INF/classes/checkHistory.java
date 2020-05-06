import bank.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class checkHistory extends HttpServlet{
private static final long serialVersionUID = 102834573239L;

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doPost(request,response);
}
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException
    {
      HttpSession userSession = request.getSession();
      String UserN = (String)userSession.getAttribute("currentUser");
      userSession.setAttribute("action","history");
      File logfile = new File("logfile.txt");
  		PrintWriter out = response.getWriter();
  		try {
        out.println("<html>");
        out.println("<html>");
        out.println("<head>");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("expires", 0);
        response.setHeader("Expires", "0");
        out.println("</head>");
        out.println("<style>");
        out.println("body {");
        out.println("background-image: url('portal.jpg');");
        out.println("background-repeat: no-repeat;");
        out.println("}");
        out.println("div {");
        out.println("height: 900px;");
        out.println("width: 550px;");
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
        out.println("<font COLOR='#7a0019'> <CENTER><h1>GopherBank Deposit</font></h1><br>");
        out.println("<FORM METHOD='POST' ACTION='updateBankApp'>");
        out.println("<h3>History Of transctions: </h3><br>");
  			String transction;
  			BufferedReader reader = new BufferedReader(new FileReader(logfile));
  			while((transction=reader.readLine())!=null){
  				if(transction.contains(UserN))
  					out.println("<br>"+transction+"<br>");
  			}
        reader.close();
        out.println("<br>");
        out.println("<br>");
        out.println("<button formaction='withdraw'>Withdraw</button>");
        out.println("<button formaction='deposit'>Deposit</button>");
        out.println("<button formaction='TransferMoney'>Transfer Money</button>");
        out.println("<button formaction='deleteAccount'>Close Account</button>");
        out.println("<button formaction='AddAnotherAccountScreen'>Create another account</button>");
        out.println("<br><button formaction='index.html'>Log Out</button>");
        out.println("</div>");
        out.println("</body>");
  		} catch (Exception e) {
        out.println("<html>");
        out.println("<body>");
        out.println("<CENTER><h1>No Log Data Found</b1>");
        out.println("<a href='javascript:history.back()'>Go Back</a>");
        out.println("</body>");
  		}
    }
  }
