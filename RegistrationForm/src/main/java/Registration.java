

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public Registration() {
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter o=response.getWriter();
		String namee=request.getParameter("name");
		String email=request.getParameter("email");
		String ph=request.getParameter("phonenumber");
		String gender=request.getParameter("gender");
		String Qulification=request.getParameter("Qulification");
		String dob=request.getParameter("dob");
		Connection c=null;
		PreparedStatement pst=null;
		ServletContext context=getServletContext();
		String uname=context.getInitParameter("username");
		String password=context.getInitParameter("password");
		try
		{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",uname,password);
				System.out.println("connected");
				//String query="create table GCRegistration(Name varchar2(20),Email varchar2(20),Phoneno number,Gender varchar2(10),Qulification varchar2(10),DOB varchar2(20))";
				pst=c.prepareStatement("insert into GCTech values(?,?,?,?,?,?)");
				pst.setString(1,namee);
				pst.setString(2, email);
				pst.setString(3, ph);
				pst.setString(4, gender);
				pst.setString(5, Qulification);
				pst.setString(6, dob);
				int i=pst.executeUpdate();
				System.out.println(i+"-records inserted");
				if(i>0)
				{
					response.sendRedirect("Done.html");
				}
				else if(i==0)
				{
					response.sendRedirect("NotDone.html");
				}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
