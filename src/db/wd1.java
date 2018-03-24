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
public class wd1 extends HttpServlet {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,
IOException {
PrintWriter pw=res.getWriter();
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
res.setContentType("text/html");
int amt=Integer.parseInt(req.getParameter("amt"));
int bal;
try{
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection
con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
HttpSession session=req.getSession(false);
int x=(Integer)session.getAttribute("acn");
PreparedStatement ps=con.prepareStatement("select * from db where rno=?");
ps.setInt(1,x);
ps.execute();
ResultSet rs=ps.executeQuery();
rs.next();
bal=rs.getInt(3);
if(amt<bal){
bal-=amt;
PreparedStatement u=con.prepareStatement("update db set bal=? where rno=?");
u.setInt(1,bal);
u.setInt(2,x);
int ch=u.executeUpdate();
if(ch==1)
{
	s+="TRANSACTION COMPLETED.<br><br>YOUR CURRENT BALANCE<br>"+bal
			+ "<br><br><form method='get' action='logout'>"
			+ "<input type='submit' class='btn btn-danger' value='Log Out'/>"
			+ "</form></div></div></body></html>";
	pw.println(s);
	res.setHeader("Refresh", "4;option.jsp");
}
else
{
	s+="unable to Withdraw the amount</div></div></body></html>";
	pw.println(s);
}
}else{
s+="unable process the request</div></div></body></html>";
pw.println(s);
}
con.close();
pw.close();
} catch (Exception e)
{ e.printStackTrace(); }
}
}