package db;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class mp extends HttpServlet {
private static final long serialVersionUID = 1L;
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,
IOException {
// TODO Auto-generated method stub
PrintWriter pw=res.getWriter();
res.setContentType("text/html");
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection
con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
int acc=Integer.parseInt(req.getParameter("acc"));

if(validate.checkUser(acc)){
    HttpSession session=req.getSession();  
    session.setAttribute("acn",acc);  
PreparedStatement ps=con.prepareStatement("select * from db where rno=?");
ps.setInt(1,acc);
ps.execute();
ResultSet rs=ps.executeQuery();
rs.next();
session.setAttribute("name",rs.getString(2));
con.close();
res.sendRedirect("option.jsp");  
}else {
	con.close();
pw.println("<h1>invalid account number<h1>");
}
} catch (Exception e)
{ e.printStackTrace(); }
}
}