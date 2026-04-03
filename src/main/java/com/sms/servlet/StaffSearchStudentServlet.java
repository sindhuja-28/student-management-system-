package com.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import com.sms.util.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/staffSearchStudent")
public class StaffSearchStudentServlet extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException {

    response.setContentType("text/html");

    String keyword = request.getParameter("id");

    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("<title>Search Results</title>");
    out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
    out.println("</head>");

    out.println("<body style='background:#f4f6f9'>");

    out.println("<div class='container mt-5'>");

    out.println("<h2 class='mb-4'>Search Results</h2>");

    try {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM student WHERE id=? OR name LIKE ?"
        );

        ps.setString(1, keyword);
        ps.setString(2, "%" + keyword + "%");

        ResultSet rs = ps.executeQuery();

        out.println("<table class='table table-bordered table-hover'>");
        out.println("<thead class='table-dark'>");
        out.println("<tr><th>ID</th><th>Name</th><th>Branch</th></tr>");
        out.println("</thead>");
        out.println("<tbody>");

        boolean found = false;

        while(rs.next()){
            found = true;

            out.println("<tr>");
            out.println("<td>"+rs.getString("id")+"</td>");
            out.println("<td>"+rs.getString("name")+"</td>");
            out.println("<td>"+rs.getString("branch")+"</td>");
            out.println("</tr>");
        }

        if(!found){
            out.println("<tr>");
            out.println("<td colspan='3' class='text-center text-danger'>No Student Found</td>");
            out.println("</tr>");
        }

        out.println("</tbody>");
        out.println("</table>");

    } catch(Exception e){
        e.printStackTrace();
    }

    out.println("<a href='staffDashboard' class='btn btn-secondary mt-3'>Back to Dashboard</a>");

    out.println("</div>");
    out.println("</body>");
    out.println("</html>");
}
}