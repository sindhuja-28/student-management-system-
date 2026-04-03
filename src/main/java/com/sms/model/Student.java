package com.sms.model;

public class Student {

private String id;
private String name;
private int age;
private String branch;
private String photo;   // NEW FIELD

// Constructor
public Student(String id,String name,int age,String branch){

this.id=id;
this.name=name;
this.age=age;
this.branch=branch;

}

// ===== GETTERS =====

public String getId(){
return id;
}

public String getName(){
return name;
}

public int getAge(){
return age;
}

public String getBranch(){
return branch;
}

public String getPhoto(){
return photo;
}


// ===== SETTERS =====

public void setId(String id){
this.id=id;
}

public void setName(String name){
this.name=name;
}

public void setAge(int age){
this.age=age;
}

public void setBranch(String branch){
this.branch=branch;
}

public void setPhoto(String photo){
this.photo=photo;
}

}