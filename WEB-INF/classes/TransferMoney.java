import bank.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TransferMoney extends HttpServlet{
private static final long serialVersionUID = 102831973239L;

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doPost(request,response);
}
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException
    {
        PrintWriter out =response.getWriter();
        HttpSession userSession = request.getSession();

        out.println("<html>");
        out.println("<body>");
        out.println("<CENTER><h1>Transfer Screen!</b1>");
        out.println("<button onclick=\"location.href = 'index.html';\"'>Go Back</a></button>");
        out.println("</body>");
    }
}