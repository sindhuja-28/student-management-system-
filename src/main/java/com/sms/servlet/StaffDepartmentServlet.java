package com.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sms.dao.StudentDAO;
import com.sms.dao.CourseDAO;
import com.sms.model.Student;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/staffDepartment")
public class StaffDepartmentServlet extends HttpServlet {

protected void doGet(HttpServletRequest request,HttpServletResponse response)
throws IOException{

response.setContentType("text/html");

String branch = request.getParameter("branch");
String filter = request.getParameter("filter");

/* ===== ANALYTICS ===== */

int totalStudents = StudentDAO.getStudentCountByBranch(branch);
int registered = CourseDAO.getRegisteredStudentsByBranch(branch);
int notRegistered = totalStudents - registered;

/* ===== STUDENT LIST BASED ON FILTER ===== */

List<Student> students;

if(filter == null || filter.equals("all")){
students = StudentDAO.getStudentsByBranch(branch);
}
else if(filter.equals("registered")){
students = StudentDAO.getRegisteredStudentsByBranch(branch);
}
else if(filter.equals("notregistered")){
students = StudentDAO.getNotRegisteredStudentsByBranch(branch);
}
else{
students = StudentDAO.getStudentsWithCertificates(branch);
}

PrintWriter out = response.getWriter();

out.println("<html>");
out.println("<head>");

out.println("<title>"+branch+" Department</title>");

out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");

out.println("<style>");
out.println("body{background:#f4f6f9;font-family:Segoe UI;}");
out.println(".card{border:none;border-radius:10px;}");
out.println(".card:hover{transform:scale(1.03);transition:0.3s;}");
out.println("</style>");

out.println("</head>");
out.println("<body>");

out.println("<div class='container-fluid mt-4'>");
out.println("<div class='row'>");



/* ===== RIGHT SIDE STUDENT LIST ===== */

out.println("<div class='col-md-12'>");

out.println("<h3>"+branch+" Students</h3>");

out.println("<div class='mb-3'>");

out.println("<a href='staffDepartment?branch="+branch+"&filter=all' class='btn btn-secondary btn-sm me-2'>All Students</a>");
out.println("<a href='staffDepartment?branch="+branch+"&filter=registered' class='btn btn-success btn-sm me-2'>Registered</a>");
out.println("<a href='staffDepartment?branch="+branch+"&filter=notregistered' class='btn btn-danger btn-sm me-2'>Not Registered</a>");
out.println("<a href='staffDepartment?branch="+branch+"&filter=certificates' class='btn btn-primary btn-sm'>Certificates</a>");

out.println("</div>");

out.println("<table class='table table-bordered table-striped mt-3'>");

out.println("<thead class='table-dark'>");
out.println("<tr>");
out.println("<th>Regd No</th>");
out.println("<th>Name</th>");
out.println("<th>Branch</th>");
out.println("<th>Action</th>");
out.println("</tr>");
out.println("</thead>");

out.println("<tbody>");

for(Student s : students){

out.println("<tr>");
out.println("<td>"+s.getId()+"</td>");
out.println("<td>"+s.getName()+"</td>");
out.println("<td>"+s.getBranch()+"</td>");

out.println("<td>");
out.println("<a href='staffStudentProfile?id="+s.getId()+"' class='btn btn-primary btn-sm'>View</a>");
out.println("</td>");

out.println("</tr>");

}

out.println("</tbody>");
out.println("</table>");

out.println("<a href='staffDashboard' class='btn btn-secondary'>Back</a>");

out.println("</div>"); // right

out.println("</div>"); // row
out.println("</div>"); // container

out.println("</body>");
out.println("</html>");

}

/* ===== CARD METHOD ===== */

private void card(PrintWriter out,String title,int count,String color,String link){

out.println("<a href='"+link+"' style='text-decoration:none'>");

out.println("<div class='card shadow p-4 text-center mb-4'>");

out.println("<h6>"+title+"</h6>");
out.println("<h2 class='text-"+color+"'>"+count+"</h2>");

out.println("</div>");

out.println("</a>");

}

}