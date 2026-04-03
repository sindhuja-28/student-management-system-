package com.sms.servlet;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sms.util.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws IOException {

String username = request.getParameter("username");
String password = request.getParameter("password");

try {

Connection con = DBConnection.getConnection();

/* Check Student */

PreparedStatement ps1 = con.prepareStatement(
"SELECT * FROM student WHERE id=?");

ps1.setString(1, username);

ResultSet rs1 = ps1.executeQuery();

if(rs1.next() && username.equals(password)){

HttpSession session = request.getSession();
session.setAttribute("regd_no", username);   // ⭐ IMPORTANT

response.sendRedirect("studentDashboard");
return;

}

/* Check Staff */

PreparedStatement ps2 = con.prepareStatement(
"SELECT * FROM staff WHERE staff_id=?");

ps2.setString(1, username);

ResultSet rs2 = ps2.executeQuery();

if(rs2.next() && username.equals(password)){
	HttpSession session = request.getSession();
    session.setAttribute("staff_id", username);

    response.sendRedirect(request.getContextPath() + "/staffDashboard");
return;

}

response.sendRedirect("login.jsp?error=1");

}catch(Exception e){
e.printStackTrace();
}

}
}