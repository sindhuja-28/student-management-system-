package com.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sms.util.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/searchStudent")
public class SearchStudentServlet extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException {

    response.setContentType("text/html");

    String keyword = request.getParameter("id");

    PrintWriter out = response.getWriter();

    try {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM student WHERE id=? OR name LIKE ?"
        );

        ps.setString(1, keyword);
        ps.setString(2, "%" + keyword + "%");

        ResultSet rs = ps.executeQuery();

        out.println("<h2>Search Results</h2>");

        while(rs.next()){
            out.println(rs.getString("name") + "<br>");
        }

    } catch(Exception e){
        e.printStackTrace();
    }
}
}