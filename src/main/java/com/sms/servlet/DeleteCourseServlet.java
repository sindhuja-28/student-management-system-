package com.sms.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sms.util.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/deleteCourse")
public class DeleteCourseServlet extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException {

    int id = Integer.parseInt(request.getParameter("id"));

    try{
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
            "DELETE FROM nptel_courses WHERE id=?"
        );

        ps.setInt(1, id);
        ps.executeUpdate();

    }catch(Exception e){
        e.printStackTrace();
    }

    response.sendRedirect("myCourses");
}
}