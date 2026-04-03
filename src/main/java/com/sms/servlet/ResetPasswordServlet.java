package com.sms.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sms.util.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/resetPassword")
public class ResetPasswordServlet extends HttpServlet {

protected void doPost(HttpServletRequest request,HttpServletResponse response)
throws IOException{

String regd = request.getParameter("regd_no");
String email = request.getParameter("email");
String password = request.getParameter("password");

try{

Connection con = DBConnection.getConnection();

String query =
"UPDATE student SET password=? WHERE id=? AND email=?";

PreparedStatement ps = con.prepareStatement(query);

ps.setString(1,password);
ps.setString(2,regd);
ps.setString(3,email);

int updated = ps.executeUpdate();

if(updated > 0){

response.getWriter().println("Password Reset Successful <br><a href='studentLogin.jsp'>Login</a>");

}else{

response.getWriter().println("Invalid Registration No or Email");

}

}catch(Exception e){
e.printStackTrace();
}

}
}