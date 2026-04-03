package com.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import com.sms.util.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/editCourse")
public class EditCourseServlet extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException {

    int id = Integer.parseInt(request.getParameter("id"));

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    try{
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM nptel_courses WHERE id=?"
        );

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if(rs.next()){

            out.println("<html><head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("</head><body class='p-5'>");

            out.println("<h3>Edit Course</h3>");

            out.println("<form action='updateCourse' method='post'>");

            out.println("<input type='hidden' name='id' value='"+id+"'>");

            out.println("<label>Course Name</label>");
            out.println("<input type='text' name='course_name' value='"+rs.getString("course_name")+"' class='form-control mb-2'>");

            out.println("<label>Payment</label>");
            out.println("<input type='text' name='payment_status' value='"+rs.getString("payment_status")+"' class='form-control mb-2'>");

            out.println("<button class='btn btn-success'>Update</button>");

            out.println("</form>");

            out.println("</body></html>");
        }

    }catch(Exception e){
        e.printStackTrace();
    }
}
}