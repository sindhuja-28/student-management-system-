package com.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewStudents")
public class ViewStudentsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String branch = request.getParameter("branch");

		List<Student> students = (List<Student>) request.getAttribute("students");
		
		if (students == null) {
		
		    if (branch == null || branch.equals("all")) {
		        students = StudentDAO.getAllStudents();
		    } else {
		        students = StudentDAO.getStudentsByBranch(branch);
		    }
		
		}

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Students</title>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("</head>");

        out.println("<body style='background:#f4f6f9'>");

        out.println("<div class='container mt-5'>");

        // Header
        out.println("<div class='d-flex justify-content-between align-items-center mb-4'>");
        out.println("<h2>Student Management</h2>");
        out.println("<div>");
        out.println("<a href='staffDashboard' class='btn btn-secondary'>Dashboard</a>");
        out.println("<a href='add-student.jsp' class='btn btn-success'>Add Student</a>");
        out.println("</div>");
        out.println("</div>");

        // Title
        if(branch == null || branch.equals("all")){
            out.println("<h3 class='text-center mb-4'>All Students</h3>");
            out.println("<div class='row mb-4'>");

         // SEARCH
         out.println("<div class='col-md-6'>");
         out.println("<form action='searchStudent' method='get'>");
         out.println("<div class='input-group'>");
         out.println("<input type='text' name='keyword' class='form-control' placeholder='Search by ID or Name'>");
         out.println("<button class='btn btn-primary'>Search</button>");
         out.println("</div>");
         out.println("</form>");
         out.println("</div>");

         // BRANCH FILTER
         out.println("<div class='col-md-6'>");
         out.println("<form action='viewStudents' method='get'>");
         out.println("<select name='branch' class='form-select' onchange='this.form.submit()'>");
         out.println("<option value='all'>All Branches</option>");
         out.println("<option value='CSE'>CSE</option>");
         out.println("<option value='CSIT'>CSIT</option>");
         out.println("<option value='CSSE'>CSSE</option>");
         out.println("<option value='AIML'>AIML</option>");
         out.println("<option value='ECE'>ECE</option>");
         out.println("<option value='EEE'>EEE</option>");
         out.println("<option value='MECH'>MECH</option>");
         out.println("</select>");
         out.println("</form>");
         out.println("</div>");

         out.println("</div>");
        } else {
            out.println("<h3 class='text-center mb-4'>" + branch + " Students</h3>");
        }

        // Card
        out.println("<div class='card shadow p-4'>");

        // Table
        out.println("<table class='table table-hover table-bordered'>");

        out.println("<thead class='table-primary'>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Name</th>");
        out.println("<th>Age</th>");
        out.println("<th>Branch</th>");
        out.println("<th>Actions</th>");
        out.println("</tr>");
        out.println("</thead>");

        out.println("<tbody>");

        if (students == null || students.isEmpty()) {

            out.println("<tr>");
            out.println("<td colspan='5' class='text-center text-danger'>No Students Found</td>");
            out.println("</tr>");

        } else {

            for (Student s : students) {

                out.println("<tr>");
                out.println("<td>" + s.getId() + "</td>");
                out.println("<td>" + s.getName() + "</td>");
                out.println("<td>" + s.getAge() + "</td>");
                out.println("<td>" + s.getBranch() + "</td>");

                out.println("<td>");
                out.println("<a href='editStudent?id=" + s.getId() + "' class='btn btn-warning btn-sm me-2'>Edit</a>");
                out.println("<a href='deleteStudent?id=" + s.getId() + "' class='btn btn-danger btn-sm' onclick='return confirm(\"Are you sure you want to delete this student?\")'>Delete</a>");
                out.println("</td>");

                out.println("</tr>");
            }
        }

        out.println("</tbody>");
        out.println("</table>");

        out.println("</div>"); // card

        out.println("</div>"); // container
        out.println("</body>");
        out.println("</html>");
    }
}