package com.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


@WebServlet("/staffDashboard")
public class StaffDashboardServlet extends HttpServlet {

protected void doGet(HttpServletRequest request,HttpServletResponse response)
throws IOException{

response.setContentType("text/html");

/* SESSION CHECK */

HttpSession session = request.getSession(false);

if(session == null || session.getAttribute("staff_id") == null){
response.sendRedirect("login.jsp");
return;
}

String staff = (String) session.getAttribute("staff_id");

PrintWriter out = response.getWriter();

out.println("<html>");
out.println("<head>");

out.println("<title>Staff Dashboard</title>");

out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css'>");

out.println("<style>");

out.println("body{margin:0;background:#f1f5f9;font-family:Segoe UI;}");

out.println(".sidebar{height:100vh;background:#1e293b;color:white;padding-top:20px;}");

out.println(".sidebar a{color:white;text-decoration:none;display:block;padding:12px 20px;}");

out.println(".sidebar a:hover{background:#334155;}");

out.println(".card{border:none;border-radius:12px;}");

out.println(".card:hover{transform:scale(1.05);transition:0.3s;}");

out.println("</style>");

out.println("</head>");
out.println("<body>");

out.println("<div class='container-fluid'>");
out.println("<div class='row'>");

/* SIDEBAR */

out.println("<div class='col-md-2 sidebar'>");

out.println("<h4 class='text-center'>Staff Panel</h4>");
out.println("<hr>");

out.println("<a href='staffDashboard'><i class='bi bi-speedometer2'></i> Dashboard</a>");
out.println("<a href='viewStudents'><i class='bi bi-people'></i> Students</a>");

out.println("<a href='logout'><i class='bi bi-box-arrow-right'></i> Logout</a>");

out.println("</div>");

/* MAIN CONTENT */

out.println("<div class='col-md-10 p-4'>");

/* HEADER */

out.println("<div class='d-flex justify-content-between mb-4'>");

out.println("<h2>Staff Dashboard</h2>");
out.println("<span class='fw-semibold'>Welcome " + staff + "</span>");

out.println("</div>");

/* SEARCH STUDENT */

out.println("<div class='row mb-4'>");

out.println("<div class='col-md-6'>");

out.println("<form action='staffSearchStudent' method='get'>");

out.println("<div class='input-group'>");

out.println("<input type='text' name='id' class='form-control' placeholder='Enter ID or Name' required>");

out.println("<button class='btn btn-primary'>Search</button>");

out.println("</div>");

out.println("</form>");

out.println("</div>");

out.println("</div>");

/* DEPARTMENT CARDS */

out.println("<div class='row'>");

card(out,"CSE","primary");
card(out,"CSIT","success");
card(out,"CSSE","warning");
card(out,"AIML","dark");
card(out,"ECE","secondary");
card(out,"EEE","danger");
card(out,"MECH","info");

out.println("</div>");

out.println("</div>");
out.println("</div>");
out.println("</div>");

out.println("</body>");
out.println("</html>");

}

/* DEPARTMENT CARD */

private void card(PrintWriter out,String branch,String color){

out.println("<div class='col-md-3 mb-4'>");

out.println("<div class='card shadow p-4 text-center'>");

out.println("<h5>"+branch+" Students</h5>");

out.println("<a href='staffDepartment?branch="+branch+"' class='btn btn-"+color+" mt-2'>Open</a>");

out.println("</div>");

out.println("</div>");

}

}