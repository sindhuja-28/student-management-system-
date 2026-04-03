package com.sms.servlet;

import com.sms.dao.StudentDAO;
import java.io.IOException;
import java.io.PrintWriter;

import com.sms.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String id = request.getParameter("id");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String branch = request.getParameter("branch");
        System.out.println("Branch selected: " + branch);
        Student student = new Student(id, name, age, branch);

        boolean status = StudentDAO.addStudent(student);

        if (status) {

            response.sendRedirect("viewStudents?msg=added");

        } else {

            response.sendRedirect("viewStudents?msg=error");

        }
    }
}

