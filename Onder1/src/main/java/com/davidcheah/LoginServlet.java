package com.davidcheah;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/loginsecen")
public class LoginServlet extends HttpServlet {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void showLoginForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
  res.setContentType("text/html");
  PrintWriter out = res.getWriter();
  out.println("<html>");
  out.println("<head>");
  out.println("<title>Login</title>");
  out.println("</head>");
  out.println("<body>");
  out.println("<br>Please weqwe enter username and password");
  out.println("<form method=post>");
  out.println("<br>Username: <input type=text name=username>");
  out.println("<br>Password: <input type=text name=password>");
  out.println("<br><input type=submit>");
  out.println("</form>");
  out.println("</body>");
  out.println("</html>");
 }
 
public void init() {
	  // 1. Load JDBC driver
	  try {
	   Class.forName("com.mysql.cj.jdbc.Driver");
	  } catch (ClassNotFoundException e) {
		  e.printStackTrace();
		  //System.out.println("we are here in the init");
	  }
	 }

public boolean login(String name, String password) {
	  String url = "jdbc:mysql://localhost:3307/demowebappdb";
	  String dbUsername = "root";
	  String dbPassword = "1!mysqlROCKS";
	  String query = "select id from users where username='" + name + "' and password='" + password + "'";
	try {
	  
	  // 2. Create a connection
	  Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
	  
	  // 3. Create a statement
	  Statement st = con.createStatement();
	  
	  // 4. Create a ResultSet
	  ResultSet rs = st.executeQuery(query);
	  
	  if (rs.next()) {
	   // 5. Close all connections
	   rs.close();
	   st.close();
	   con.close();
	   return true;
	  }
	  
	  // 5. Close all connections
	  rs.close();
	  st.close();
	  con.close();
	  return false;
	 }
	 catch (SQLException e) {
	  System.out.println(e.toString());
	 } catch (Exception e) {
	  System.out.println(e.toString());
	 }
	 return false;
	}

 public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
  //showLoginForm(req, res);
	 String username = req.getParameter("username");
	  String password = req.getParameter("password");
	  if (username.isEmpty() || password.isEmpty()) {
	   res.sendRedirect("LoginPage.jsp?");
	  } else {
	   res.sendRedirect("WelcomePage.jsp?user="+username);
	  }
 }
 
 public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
  String username = req.getParameter("username");
  String password = req.getParameter("password");
  if (username.isEmpty() || password.isEmpty()) {
		res.sendRedirect("LoginPage.jsp");
	} else {
		
		if (login(username, password)) {
			res.sendRedirect("WelcomePage.jsp?user="+username);
		} else {
			res.sendRedirect("LoginPage.jsp");
		}
		
	}
  
 }
}