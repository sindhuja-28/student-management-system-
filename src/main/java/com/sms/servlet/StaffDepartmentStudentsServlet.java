package com.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/staffDepartmentStudents")
public class StaffDepartmentStudentsServlet extends HttpServlet {

protected void doGet(HttpServletRequest request,HttpServletResponse response)
throws IOException{

response.setContentType("text/html");

String branch = request.getParameter("branch");
String type = request.getParameter("type");

List<Student> students;

if("all".equals(type)){
    students = StudentDAO.getStudentsByBranch(branch);
}
else{
    students = new java.util.ArrayList<>(); // ✅ FIX
}

PrintWriter out = response.getWriter();

out.println("<html>");
out.println("<head>");

out.println("<title>"+branch+" Students</title>");

out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");

out.println("<style>");
out.println("body{background:#f4f6f9;font-family:Segoe UI;}");
out.println(".card{border:none;border-radius:10px;}");
out.println("</style>");

out.println("</head>");

out.println("<body>");

out.println("<div class='container mt-5'>");

/* ===== HEADER ===== */

out.println("<div class='d-flex justify-content-between align-items-center mb-4'>");

if("all".equals(type)){
out.println("<h3>"+branch+" - All Students</h3>");
}else{
out.println("<h3>"+branch+" - Not Registered Students</h3>");
}

out.println("<a href='staffDepartment?branch="+branch+"' class='btn btn-dark'>Back</a>");

out.println("</div>");

/* ===== STUDENT TABLE ===== */

out.println("<div class='card shadow p-4'>");

out.println("<table class='table table-hover table-bordered'>");

out.println("<thead class='table-primary'>");

out.println("<tr>");
out.println("<th>Registration No</th>");
out.println("<th>Name</th>");
out.println("<th>Branch</th>");
out.println("<th>Action</th>");
out.println("</tr>");

out.println("</thead>");

out.println("<tbody>");

if(students.size() == 0){

out.println("<tr>");
out.println("<td colspan='4' class='text-center text-muted'>No students found</td>");
out.println("</tr>");

}

for(Student s : students){

out.println("<tr>");

out.println("<td>"+s.getId()+"</td>");
out.println("<td>"+s.getName()+"</td>");
out.println("<td>"+s.getBranch()+"</td>");

out.println("<td>");

out.println("<a href='staffStudentProfile?id="+s.getId()+"' class='btn btn-primary btn-sm'>View Profile</a>");

out.println("</td>");

out.println("</tr>");

}

out.println("</tbody>");

out.println("</table>");

out.println("</div>");

out.println("</div>");

out.println("</body>");
out.println("</html>");

}
}