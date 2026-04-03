package com.sms.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sms.model.Student;
import com.sms.util.DBConnection;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public static boolean addStudent(Student student) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO student (id, name, age, branch) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getBranch());

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // ================= GET ALL STUDENTS =================

    public static List<Student> getAllStudents() {

        List<Student> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM student";
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Student s = new Student(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("branch")
                );

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= DELETE STUDENT =================

    public static boolean deleteStudent(String id) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String query = "DELETE FROM student WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, id);

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // ================= GET STUDENT BY ID =================

    public static Student getStudentById(String id) {

        Student student = null;

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM student WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                student = new Student(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("branch")
                );

                student.setPhoto(rs.getString("photo"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

    // ================= UPDATE STUDENT =================

    public static boolean updateStudent(Student student) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String query = "UPDATE student SET name=?, age=?, branch=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getBranch());
            ps.setString(4, student.getId());

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // ================= TOTAL STUDENT COUNT =================

    public static int getStudentCount() {

        int count = 0;

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT COUNT(*) FROM student";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    // ================= SEARCH STUDENT =================

    public static List<Student> searchStudents(String keyword) {

        List<Student> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM student WHERE id LIKE ? OR name LIKE ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Student s = new Student(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("branch")
                );

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= COUNT BY BRANCH =================

    public static int getStudentCountByBranch(String branch) {

        int count = 0;

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT COUNT(*) FROM student WHERE branch=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, branch);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    // ================= GET STUDENTS BY BRANCH =================

    public static List<Student> getStudentsByBranch(String branch) {

        List<Student> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM student WHERE branch=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, branch);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Student s = new Student(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("branch")
                );

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
      
    public static List<Student> getRegisteredStudentsByBranch(String branch){

    	List<Student> list = new ArrayList<>();

    	try{

    	Connection con = DBConnection.getConnection();

    	String query =
    	"SELECT DISTINCT s.* FROM student s " +
    	"JOIN nptel_courses c ON s.id=c.regd_no " +
    	"WHERE s.branch=? AND c.payment_status='Paid'";

    	PreparedStatement ps = con.prepareStatement(query);
    	ps.setString(1, branch);

    	ResultSet rs = ps.executeQuery();

    	while(rs.next()){

    	Student s = new Student(
    	rs.getString("id"),
    	rs.getString("name"),
    	rs.getInt("age"),
    	rs.getString("branch")
    	);

    	list.add(s);

    	}

    	}catch(Exception e){
    	e.printStackTrace();
    	}

    	return list;

    	}
    
    public static List<Student> getNotRegisteredStudentsByBranch(String branch){

    	List<Student> list = new ArrayList<>();

    	try{

    	Connection con = DBConnection.getConnection();

    	String query =
    	"SELECT * FROM student s WHERE s.branch=? AND s.id NOT IN (" +
    	"SELECT DISTINCT regd_no FROM nptel_courses WHERE payment_status='Paid')";

    	PreparedStatement ps = con.prepareStatement(query);
    	ps.setString(1, branch);

    	ResultSet rs = ps.executeQuery();

    	while(rs.next()){

    	Student s = new Student(
    	rs.getString("id"),
    	rs.getString("name"),
    	rs.getInt("age"),
    	rs.getString("branch")
    	);

    	list.add(s);

    	}

    	}catch(Exception e){
    	e.printStackTrace();
    	}

    	return list;
    	}
    
    public static List<Student> getStudentsWithCertificates(String branch){

    	List<Student> list = new ArrayList<>();

    	try{

    	Connection con = DBConnection.getConnection();

    	String query =
    	"SELECT DISTINCT s.* FROM student s " +
    	"JOIN nptel_courses c ON s.id=c.regd_no " +
    	"WHERE s.branch=? AND c.result='Pass'";

    	PreparedStatement ps = con.prepareStatement(query);
    	ps.setString(1, branch);

    	ResultSet rs = ps.executeQuery();

    	while(rs.next()){

    	Student s = new Student(
    	rs.getString("id"),
    	rs.getString("name"),
    	rs.getInt("age"),
    	rs.getString("branch")
    	);

    	list.add(s);

    	}

    	}catch(Exception e){
    	e.printStackTrace();
    	}

    	return list;

    	}
    
    public static boolean updatePhoto(String id,String photo){

    	boolean status=false;

    	try{

    	Connection con = DBConnection.getConnection();

    	String query="UPDATE student SET photo=? WHERE id=?";

    	PreparedStatement ps = con.prepareStatement(query);

    	ps.setString(1,photo);
    	ps.setString(2,id);

    	status = ps.executeUpdate()>0;

    	}catch(Exception e){
    	e.printStackTrace();
    	}

    	return status;
    	}
}