package com.sms.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sms.util.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/updateCourse")
public class UpdateCourseServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws IOException {

    int id = Integer.parseInt(request.getParameter("id"));
    String course = request.getParameter("course_name");
    String payment = request.getParameter("payment_status");

    try{
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
            "UPDATE nptel_courses SET course_name=?, payment_status=? WHERE id=?"
        );

        ps.setString(1, course);
        ps.setString(2, payment);
        ps.setInt(3, id);

        ps.executeUpdate();

    }catch(Exception e){
        e.printStackTrace();
    }

    response.sendRedirect("myCourses");
}
}