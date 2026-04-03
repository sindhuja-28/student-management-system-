package com.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/staffStudents")
public class StaffStudentsServlet extends HttpServlet {

protected void doGet(HttpServletRequest request,HttpServletResponse response)
throws IOException{

String branch = request.getParameter("branch");

List<Student> students = StudentDAO.getStudentsByBranch(branch);

response.setContentType("text/html");

PrintWriter out = response.getWriter();

out.println("<html>");
out.println("<head>");

out.println("<title>"+branch+" Students</title>");

out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");

out.println("</head>");

out.println("<body style='background:#f4f6f9'>");

out.println("<div class='container mt-5'>");

out.println("<div class='d-flex justify-content-between align-items-center mb-4'>");

out.println("<h2>"+branch+" Students</h2>");

out.println("<a href='staffDepartment?branch="+branch+"' class='btn btn-secondary'>Back</a>");

out.println("</div>");

out.println("<table class='table table-bordered table-striped'>");

out.println("<thead class='table-dark'>");

out.println("<tr>");
out.println("<th>ID</th>");
out.println("<th>Name</th>");
out.println("<th>Branch</th>");
out.println("<th>View</th>");
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

out.println("</div>");

out.println("</body>");
out.println("</html>");

}
}