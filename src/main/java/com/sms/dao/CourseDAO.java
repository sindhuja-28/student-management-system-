package com.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sms.model.Course;
import com.sms.util.DBConnection;

public class CourseDAO {


// ================= ADD COURSE =================

	public static boolean addCourse(Course c){

	    boolean saved=false;

	    try{

	        Connection con = DBConnection.getConnection();

	        String query = "INSERT INTO nptel_courses(regd_no,course_name,payment_status,start_date,end_date,duration_weeks,result,certificate_path) VALUES(?,?,?,?,?,?,?,?)";

	        PreparedStatement ps = con.prepareStatement(query);

	        ps.setString(1,c.getRegd_no());
	        ps.setString(2,c.getCourse_name());
	        ps.setString(3,c.getPayment_status());
	        ps.setString(4,c.getStart_date());
	        ps.setString(5,c.getEnd_date());   // ✅ NEW
	        ps.setInt(6,c.getDuration_weeks());
	        ps.setString(7,c.getResult());
	        ps.setString(8,c.getCertificate_path());

	        saved = ps.executeUpdate()>0;

	    }catch(Exception e){
	        e.printStackTrace();
	    }

	    return saved;
	}


// ================= GET COURSES BY STUDENT =================
	public static List<Course> getCoursesByStudent(String regd){

	    List<Course> list = new ArrayList<>();

	    try{

	        Connection con = DBConnection.getConnection();

	        // ✅ CORRECT QUERY
	        String query = "SELECT * FROM nptel_courses WHERE regd_no=?";

	        PreparedStatement ps = con.prepareStatement(query);

	        ps.setString(1, regd);

	        ResultSet rs = ps.executeQuery();

	        while(rs.next()){

	        	Course c = new Course(
	        		    rs.getInt("id"), // ✅ ADD THIS
	        		    rs.getString("regd_no"),
	        		    rs.getString("course_name"),
	        		    rs.getString("payment_status"),
	        		    rs.getString("start_date"),
	        		    rs.getString("end_date"),
	        		    rs.getInt("duration_weeks"),
	        		    rs.getString("result"),
	        		    rs.getString("certificate_path")
	        		);
	            list.add(c);
	        }

	    }catch(Exception e){
	        e.printStackTrace();
	    }

	    return list;
	}


// ================= TOTAL COURSES =================

public static int getTotalCourses(){

int count = 0;

try{

Connection con = DBConnection.getConnection();

String query = "SELECT COUNT(*) FROM nptel_courses";

PreparedStatement ps = con.prepareStatement(query);

ResultSet rs = ps.executeQuery();

if(rs.next()){
count = rs.getInt(1);
}

}catch(Exception e){
e.printStackTrace();
}

return count;

}


// ================= COMPLETED COURSES =================

public static int getCompletedCourses(){

int count = 0;

try{

Connection con = DBConnection.getConnection();

String query = "SELECT COUNT(*) FROM nptel_courses WHERE result IS NOT NULL";

PreparedStatement ps = con.prepareStatement(query);

ResultSet rs = ps.executeQuery();

if(rs.next()){
count = rs.getInt(1);
}

}catch(Exception e){
e.printStackTrace();
}

return count;

}


// ================= TOTAL CERTIFICATES =================

public static int getCertificates(){

int count = 0;

try{

Connection con = DBConnection.getConnection();

String query = "SELECT COUNT(*) FROM nptel_courses WHERE result='Pass'";

PreparedStatement ps = con.prepareStatement(query);

ResultSet rs = ps.executeQuery();

if(rs.next()){
count = rs.getInt(1);
}

}catch(Exception e){
e.printStackTrace();
}

return count;

}


// ================= REGISTERED STUDENTS BY BRANCH =================

public static int getRegisteredStudentsByBranch(String branch){

int count = 0;

try{

Connection con = DBConnection.getConnection();

String query =
"SELECT COUNT(DISTINCT s.id) " +
"FROM student s JOIN nptel_courses c ON s.id=c.regd_no " +
"WHERE s.branch=? AND c.payment_status='Paid'";

PreparedStatement ps = con.prepareStatement(query);

ps.setString(1, branch);

ResultSet rs = ps.executeQuery();

if(rs.next()){
count = rs.getInt(1);
}

}catch(Exception e){
e.printStackTrace();
}

return count;

}

}