package com.sms.model;

public class Course {

private int id; // ✅ NEW (IMPORTANT)

private String regd_no;
private String course_name;
private String payment_status;
private String start_date;
private String end_date;
private int duration_weeks;
private String result;
private String certificate_path;

// ✅ CONSTRUCTOR
public Course(int id, String regd_no, String course_name, String payment_status,
              String start_date, String end_date,
              int duration_weeks, String result, String certificate_path) {

    this.id = id; // ✅ NEW
    this.regd_no = regd_no;
    this.course_name = course_name;
    this.payment_status = payment_status;
    this.start_date = start_date;
    this.end_date = end_date;
    this.duration_weeks = duration_weeks;
    this.result = result;
    this.certificate_path = certificate_path;
    
}
public Course(String regd_no, String course_name, String payment_status,
        String start_date, String end_date,
        int duration_weeks, String result, String certificate_path) {

this.regd_no = regd_no;
this.course_name = course_name;
this.payment_status = payment_status;
this.start_date = start_date;
this.end_date = end_date;
this.duration_weeks = duration_weeks;
this.result = result;
this.certificate_path = certificate_path;
}
// ✅ GETTERS

public int getId(){ return id; } // ✅ NEW

public String getRegd_no(){ return regd_no; }

public String getCourse_name(){ return course_name; }

public String getPayment_status(){ return payment_status; }

public String getStart_date(){ return start_date; }

public String getEnd_date(){ return end_date; }

public int getDuration_weeks(){ return duration_weeks; }

public String getResult(){ return result; }

public String getCertificate_path(){ return certificate_path; }

// ✅ SETTER (IMPORTANT)

public void setId(int id){
    this.id = id;
}
}