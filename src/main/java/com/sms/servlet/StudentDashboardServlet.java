package com.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/studentDashboard")
public class StudentDashboardServlet extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException {

response.setContentType("text/html");

/* ===== SESSION CHECK ===== */

HttpSession session = request.getSession(false);

if(session == null || session.getAttribute("regd_no") == null){
response.sendRedirect("login.jsp");
return;
}

String regd = (String) session.getAttribute("regd_no");

PrintWriter out = response.getWriter();

String path = request.getContextPath();  // ✅ IMPORTANT

out.println("<html>");
out.println("<head>");

out.println("<title>Student Dashboard</title>");

out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css'>");

out.println("<style>");
out.println("body{background:#f4f6f9;font-family:Segoe UI;}");
out.println(".sidebar{height:100vh;background:#1e293b;color:white;padding-top:20px;}");
out.println(".sidebar a{color:white;text-decoration:none;display:block;padding:12px 20px;font-weight:500;}");
out.println(".sidebar a:hover{background:#334155;}");
out.println(".card{border:none;border-radius:12px;}");
out.println(".card:hover{transform:scale(1.05);transition:0.3s;}");
out.println("</style>");

out.println("</head>");
out.println("<body>");

out.println("<div class='container-fluid'>");
out.println("<div class='row'>");

/* ===== SIDEBAR ===== */

out.println("<div class='col-md-2 sidebar'>");

out.println("<h4 class='text-center'>Student Portal</h4>");
out.println("<hr>");

out.println("<a href='" + path + "/studentDashboard'><i class='bi bi-speedometer2'></i> Dashboard</a>");
out.println("<a href='" + path + "/studentProfile'><i class='bi bi-person'></i> Profile</a>");
out.println("<a href='" + path + "/myCourses'><i class='bi bi-book'></i> My Courses</a>");
out.println("<a href='" + path + "/addCourse.jsp'><i class='bi bi-plus-circle'></i> Add Course</a>");
out.println("<a href='" + path + "/logout'><i class='bi bi-box-arrow-right'></i> Logout</a>");

out.println("</div>");

/* ===== MAIN CONTENT ===== */

out.println("<div class='col-md-10 p-4'>");

/* HEADER */

out.println("<div class='d-flex justify-content-between align-items-center mb-4'>");

out.println("<h3>Student Dashboard</h3>");

out.println("<div>");
out.println("<span class='me-3'>Welcome " + regd + "</span>");
out.println("<a href='" + path + "/logout' class='btn btn-danger btn-sm'>Logout</a>");
out.println("</div>");

out.println("</div>");

/* DASHBOARD CARDS */

out.println("<div class='row'>");

/* PROFILE CARD */

out.println("<div class='col-md-4'>");
out.println("<div class='card shadow p-4 text-center bg-primary text-white'>");
out.println("<i class='bi bi-person-circle fs-1'></i>");
out.println("<h5 class='mt-2'>My Profile</h5>");
out.println("<p>View and update student details</p>");
out.println("<a href='" + path + "/studentProfile' class='btn btn-light'>Open</a>");
out.println("</div>");
out.println("</div>");

/* COURSES CARD */

out.println("<div class='col-md-4'>");
out.println("<div class='card shadow p-4 text-center bg-success text-white'>");
out.println("<i class='bi bi-journal-bookmark fs-1'></i>");
out.println("<h5 class='mt-2'>My NPTEL Courses</h5>");
out.println("<p>View registered courses</p>");
out.println("<a href='" + path + "/myCourses' class='btn btn-light'>View</a>");
out.println("</div>");
out.println("</div>");

/* ADD COURSE CARD */

out.println("<div class='col-md-4'>");
out.println("<div class='card shadow p-4 text-center bg-warning text-dark'>");
out.println("<i class='bi bi-plus-square fs-1'></i>");
out.println("<h5 class='mt-2'>Add Course</h5>");
out.println("<p>Register for NPTEL course</p>");
out.println("<a href='" + path + "/addCourse.jsp' class='btn btn-dark'>Add</a>");
out.println("</div>");
out.println("</div>");

out.println("</div>");

out.println("</div>");
out.println("</div>");
out.println("</div>");

out.println("</body>");
out.println("</html>");
}
}