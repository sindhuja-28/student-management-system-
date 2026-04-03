package com.sms.servlet;

import java.io.IOException;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {

protected void doPost(HttpServletRequest request,HttpServletResponse response)
throws IOException{

String id = request.getParameter("id");
String name = request.getParameter("name");
int age = Integer.parseInt(request.getParameter("age"));
String branch = request.getParameter("branch");

Student s = new Student(id,name,age,branch);

boolean updated = StudentDAO.updateStudent(s);

if(updated){
response.sendRedirect("studentProfile");
}else{
response.getWriter().println("Update failed");
}

}
}