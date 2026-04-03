package com.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import com.sms.dao.CourseDAO;
import com.sms.model.Course;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/myCourses")
public class MyCoursesServlet extends HttpServlet {

protected void doGet(HttpServletRequest request,HttpServletResponse response)
throws IOException{

response.setContentType("text/html");

HttpSession session = request.getSession(false);

if(session == null || session.getAttribute("regd_no")==null){
response.sendRedirect("login.jsp");
return;
}

String regd = (String) session.getAttribute("regd_no");

List<Course> courses = CourseDAO.getCoursesByStudent(regd);

PrintWriter out = response.getWriter();
String path = request.getContextPath(); // ✅ IMPORTANT

out.println("<html>");
out.println("<head>");
out.println("<title>My Courses</title>");
out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
out.println("</head>");

out.println("<body style='background:#f4f6f9'>");

out.println("<div class='container mt-5'>");

out.println("<h2 class='mb-4'>My NPTEL Courses</h2>");

out.println("<table class='table table-bordered table-hover'>");

out.println("<tr>");
out.println("<th>Course</th>");
out.println("<th>Payment</th>");
out.println("<th>Status</th>");
out.println("<th>Result</th>");
out.println("<th>Certificate</th>");
out.println("<th>Actions</th>"); // ✅ NEW
out.println("</tr>");

if(courses != null && !courses.isEmpty()){

for(Course c : courses){

    String status = "N/A";

    try {
        if(c.getStart_date() != null){

            LocalDate start = LocalDate.parse(c.getStart_date());
            LocalDate end = start.plusWeeks(c.getDuration_weeks());

            LocalDate today = LocalDate.now();

            if(today.isBefore(end)){
                status = "Ongoing";
            }else{
                status = "Completed";
            }
        }
    } catch(Exception e){
        status = "Invalid Date";
    }

    out.println("<tr>");

    out.println("<td>"+c.getCourse_name()+"</td>");
    out.println("<td>"+c.getPayment_status()+"</td>");
    out.println("<td>"+status+"</td>");

    out.println("<td>"+(c.getResult()==null ? "In Progress" : c.getResult())+"</td>");

    // CERTIFICATE
    if("Pass".equals(c.getResult()) && c.getCertificate_path()!=null){
        out.println("<td>");
        out.println("<a href='"+path+"/certificates/"+c.getCertificate_path()+"' class='btn btn-sm btn-primary'>View</a>");
        out.println("</td>");
    } else {
        out.println("<td>-</td>");
    }

    // ✅ ACTION BUTTONS
    out.println("<td>");

    out.println("<a href='"+path+"/editCourse?id="+c.getId()+"' class='btn btn-warning btn-sm'>Edit</a> ");

    out.println("<a href='"+path+"/deleteCourse?id="+c.getId()+"' class='btn btn-danger btn-sm' onclick='return confirm(\"Are you sure?\")'>Delete</a>");

    out.println("</td>");

    out.println("</tr>");
}

}else{

out.println("<tr>");
out.println("<td colspan='6' class='text-center text-danger'>No Courses Found</td>");
out.println("</tr>");

}

out.println("</table>");

out.println("<a href='"+path+"/studentDashboard' class='btn btn-secondary'>Back</a>");

out.println("</div>");

out.println("</body>");
out.println("</html>");
}
}