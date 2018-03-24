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
public class bal extends HttpServlet {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,
IOException {
PrintWriter pw=res.getWriter();
res.setContentType("text/html");
String s="<html>"
		+ "<head>"
		+ "<meta charset='utf-8'>"
		+ "<meta name='viewport' content='width=device-width, initial-scale=1'>"
		+ "<title>Transcation Result</title>"
		+ "<link rel='stylesheet' href='css/bootstrap.css'>"
		+ "<link rel='stylesheet' href='css/bootstrap.min.css'>"
		+ "<script src='js/bootstrap.min.js'></script>"
		+ "<script src='js/popper.min.js'></script>"
		+ "<script src='js/jquery.min.js'></script>"
		+ "</head><body>"
		+ "<br>"
		+ "<div class='container  h-100 card col-4 text-center'>"
		+ "<div class='card-body'>";
try{
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection
con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
PreparedStatement ps=con.prepareStatement("select * from dm where na='dummy'");
ps.execute();
ResultSet rs=ps.executeQuery();
rs.next();
HttpSession session=req.getSession(false);
int x=(Integer)session.getAttribute("acn");
PreparedStatement s1=con.prepareStatement("select * from db where rno=?");
s1.setInt(1,x);
s1.execute();
ResultSet m=s1.executeQuery();
m.next();
s+="YOUR CURRENT BALANCE<br>"+m.getInt(3)+ "<br><br><form method='get' action='logout'>"
		+ "<input type='submit' class='btn btn-danger' value='Log Out'/>"
		+ "</form></div></div></body></html>";
pw.println(s);
res.setHeader("Refresh", "4;option.jsp");
con.close();
pw.close();
} catch (Exception e)
{ e.printStackTrace(); }
}
}
