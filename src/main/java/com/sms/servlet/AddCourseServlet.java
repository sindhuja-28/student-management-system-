package com.sms.servlet;

import java.io.File;
import java.io.IOException;

import com.sms.dao.CourseDAO;
import com.sms.model.Course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/addCourse")
@MultipartConfig
public class AddCourseServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws IOException, ServletException {

    HttpSession session = request.getSession();
    String regd = (String) request.getSession().getAttribute("regd_no");

    // 🔴 SESSION CHECK
    if (regd == null) {
        response.sendRedirect("studentLogin.jsp");
        return;
    }

    String course = request.getParameter("course_name");
    String payment = request.getParameter("payment_status");
    String startDate = request.getParameter("start_date");
    String endDate = request.getParameter("end_date"); // ✅ FIXED

    // 🔴 SAFE PARSE
    int duration = 0;
    try {
        duration = Integer.parseInt(request.getParameter("duration_weeks"));
    } catch (Exception e) {
        duration = 0;
    }

    String result = request.getParameter("result");

    if (result == null || result.equals("")) {
        result = null;
    }

    /* ===== CERTIFICATE UPLOAD ===== */

    String fileName = null;

    // ✅ Upload only if PASS
    if ("Pass".equals(result)) {

        Part filePart = request.getPart("certificate");

        if (filePart == null || filePart.getSize() == 0) {

            request.getSession().setAttribute("msg", "Certificate is required for PASS");
            response.sendRedirect("addCourse.jsp");
            return; // ❌ stop saving
        }

        fileName = filePart.getSubmittedFileName();

        String uploadPath = getServletContext().getRealPath("") 
                + File.separator + "certificates";

        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        filePart.write(uploadPath + File.separator + fileName);
    }
    // 🔴 UPDATED CONSTRUCTOR (include endDate)
    Course c = new Course(0, regd, course, payment, startDate, endDate, duration, result, fileName);

    boolean saved = CourseDAO.addCourse(c);

    if (saved) {
    	 request.getSession().setAttribute("msg", "Course Added Successfully");
        response.sendRedirect("studentDashboard.jsp");
    } else {
        response.getWriter().println("Error saving course");
    }
}
}