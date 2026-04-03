package com.sms.servlet;

import java.io.IOException;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/editProfile")
public class EditProfileServlet extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException {

try{

HttpSession session = request.getSession(false);

if(session == null || session.getAttribute("regd_no")==null){
response.sendRedirect("login.jsp");
return;
}

String regd = (String) session.getAttribute("regd_no");

Student s = StudentDAO.getStudentById(regd);

request.setAttribute("student", s);

request.getRequestDispatcher("editProfile.jsp")
.forward(request, response);

}catch(Exception e){
e.printStackTrace();
}

}
}