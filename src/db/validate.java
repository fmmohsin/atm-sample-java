package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServlet;
public class validate extends HttpServlet {
private static final long serialVersionUID = 1L;
public static boolean checkUser(int num)
{
boolean st =false;
try{
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection
con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
PreparedStatement ps=con.prepareStatement("select * from db where rno=?");
ps.setInt(1, num);
ResultSet rs =ps.executeQuery();
st = rs.next();
con.close();
}catch(Exception e)
{
e.printStackTrace();
}
return st;
}
}
