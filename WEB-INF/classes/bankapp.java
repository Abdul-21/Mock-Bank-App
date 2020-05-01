package Bank;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;



public class bankapp extends HttpServlet {
  private static final long serialVersionUID = 102831973239L;

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
  public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException,ServletException
	{
    PrintWriter out =response.getWriter();
    try{
      HttpSession userSession = request.getSession();
      String UserN = request.getParameter("username");
      String passWord = request.getParameter("Password");
      userSession.setAttribute("currentUser",UserN);
      HashMap<String, User> UserHmap = new HashMap<String, User>(); //Hold username, and user object with info.
      ObjectInputStream UserObjects = new ObjectInputStream(new FileInputStream("custProfile.so")); //Read profile
      User Objs= (User)UserObjects.readObject();
      UserHmap.put(Objs.getFirstName(),Objs);
      for(String Username : UserHmap.keySet()){
        out.println("<html>");
        out.println("<body>");
        out.println("<p>"+Username+"<p>");
        out.println("<body>");
      }
    }catch(Exception e){
          out.println("<html>");
          out.println("<body>");
          out.println("<img src=\"https://i.kym-cdn.com/photos/images/facebook/001/517/016/cf1.jpg\" alt=\"Waling out here\">");
          out.println("</body>");
    			return;
    }


  }
}
