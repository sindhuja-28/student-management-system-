package com.sms.servlet;

import java.io.IOException;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/studentProfile")
public class StudentProfileServlet extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException {

try{

// ===== SESSION CHECK =====
HttpSession session = request.getSession(false);

if(session == null || session.getAttribute("regd_no") == null){
response.sendRedirect("login.jsp");
return;
}

// ===== GET STUDENT ID =====
String regd = (String) session.getAttribute("regd_no");

System.out.println("Logged Student : " + regd);

// ===== FETCH STUDENT =====
Student student = StudentDAO.getStudentById(regd);

if(student != null){

request.setAttribute("student", student);

request.getRequestDispatcher("studentProfile.jsp")
.forward(request,response);

}else{

response.getWriter().println("Student profile not found");

}

}catch(Exception e){
e.printStackTrace();
response.getWriter().println("Error loading profile");
}

}

}