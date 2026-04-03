package com.sms.model;

public class StudentProfile {

String regd_no;
String name;
String branch;
String email;
String phone;
String photo;

public StudentProfile(String regd_no,String name,String branch,String email,String phone,String photo){

this.regd_no = regd_no;
this.name = name;
this.branch = branch;
this.email = email;
this.phone = phone;
this.photo = photo;

}

public String getRegd_no(){ return regd_no; }
public String getName(){ return name; }
public String getBranch(){ return branch; }
public String getEmail(){ return email; }
public String getPhone(){ return phone; }
public String getPhoto(){ return photo; }

}