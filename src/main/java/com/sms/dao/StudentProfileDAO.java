package com.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sms.model.StudentProfile;
import com.sms.util.DBConnection;

public class StudentProfileDAO {

public static StudentProfile getProfile(String regd){

StudentProfile s = null;

try{

Connection con = DBConnection.getConnection();

String query = "SELECT * FROM student_profile WHERE regd_no=?";

PreparedStatement ps = con.prepareStatement(query);

ps.setString(1,regd);

ResultSet rs = ps.executeQuery();

if(rs.next()){

s = new StudentProfile(

rs.getString("regd_no"),
rs.getString("name"),
rs.getString("branch"),
rs.getString("email"),
rs.getString("phone"),
rs.getString("photo")

);

}

}catch(Exception e){
e.printStackTrace();
}

return s;

}

}