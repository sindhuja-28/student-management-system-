package com.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import com.sms.dao.StudentDAO;
import com.sms.dao.CourseDAO;
import com.sms.model.Student;
import com.sms.model.Course;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/staffStudentProfile")
public class StaffStudentProfileServlet extends HttpServlet {

protected void doGet(HttpServletRequest request,HttpServletResponse response)
throws IOException{

response.setContentType("text/html");

String id = request.getParameter("id");

Student s = StudentDAO.getStudentById(id);
List<Course> courses = CourseDAO.getCoursesByStudent(id);

PrintWriter out = response.getWriter();

out.println("<html>");
out.println("<head>");

out.println("<title>Student Profile</title>");

out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");

out.println("<style>");
out.println("body{background:#f4f6f9;font-family:Segoe UI;}");
out.println(".card{border:none;border-radius:10px;}");
out.println("</style>");

out.println("</head>");
out.println("<body>");

out.println("<div class='container mt-5'>");

out.println("<h2 class='mb-4'>Student Profile</h2>");

/* ===== STUDENT PROFILE CARD ===== */

out.println("<div class='card shadow p-4'>");

out.println("<div class='row'>");

out.println("<div class='col-md-4 text-center'>");

if(s.getPhoto()!=null && !s.getPhoto().equals("")){
out.println("<img src='photos/"+s.getPhoto()+"' width='200' class='rounded-circle'>");
}else{
out.println("<img src='https://via.placeholder.com/200' class='rounded-circle'>");
}

out.println("</div>");

out.println("<div class='col-md-8'>");

out.println("<h5>Registration No : "+s.getId()+"</h5>");
out.println("<h5>Name : "+s.getName()+"</h5>");
out.println("<h5>Branch : "+s.getBranch()+"</h5>");
out.println("<h5>Age : "+s.getAge()+"</h5>");

out.println("</div>");

out.println("</div>");
out.println("</div>");

/* ===== COURSE TABLE ===== */

out.println("<h3 class='mt-5'>NPTEL Courses</h3>");

out.println("<table class='table table-bordered mt-3'>");

out.println("<thead class='table-dark'>");

out.println("<tr>");
out.println("<th>Course Name</th>");
out.println("<th>Payment</th>");
out.println("<th>Start Date</th>");
out.println("<th>Duration</th>");
out.println("<th>Status</th>");
out.println("<th>Result</th>");
out.println("<th>Certificate</th>");
out.println("</tr>");

out.println("</thead>");

out.println("<tbody>");

for(Course c : courses){

LocalDate start = LocalDate.parse(c.getStart_date());
int duration = c.getDuration_weeks();

LocalDate end = start.plusWeeks(duration);

LocalDate today = LocalDate.now();

String status;

if(today.isBefore(end)){
status = "Ongoing";
}else{
status = "Completed";
}

out.println("<tr>");

out.println("<td>"+c.getCourse_name()+"</td>");
out.println("<td>"+c.getPayment_status()+"</td>");
out.println("<td>"+c.getStart_date()+"</td>");
out.println("<td>"+duration+" Weeks</td>");
out.println("<td>"+status+"</td>");

if(c.getResult()==null){
out.println("<td>-</td>");
}else{
out.println("<td>"+c.getResult()+"</td>");
}

/* ===== CERTIFICATE ===== */

if("Pass".equals(c.getResult()) && c.getCertificate_path()!=null){

out.println("<td>");

out.println("<a href='certificates/"+c.getCertificate_path()+"' target='_blank' class='btn btn-primary btn-sm'>View</a>");

out.println("<a href='certificates/"+c.getCertificate_path()+"' download class='btn btn-success btn-sm ms-2'>Download</a>");

out.println("</td>");

}else{

out.println("<td>-</td>");

}

out.println("</tr>");

}

out.println("</tbody>");
out.println("</table>");

out.println("<a href='javascript:history.back()' class='btn btn-secondary'>Back</a>");

out.println("</div>");

out.println("</body>");
out.println("</html>");

}
}