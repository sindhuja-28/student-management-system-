<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sms.model.Student" %>

<%
Student s = (Student) request.getAttribute("student");

if(s == null){
out.println("Profile not found");
return;
}
%>

<!DOCTYPE html>
<html>
<head>

<title>Student Profile</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">

<style>

body{
background:#f4f6f9;
font-family:Segoe UI;
}

.profile-card{
border:none;
border-radius:12px;
}

.profile-photo{
width:180px;
height:180px;
object-fit:cover;
border-radius:50%;
border:5px solid #e2e8f0;
}

.edit-icon{
position:absolute;
bottom:15px;
right:15px;
background:#2563eb;
color:white;
padding:8px 10px;
border-radius:50%;
cursor:pointer;
font-size:16px;
}

.edit-icon:hover{
background:#1d4ed8;
}

/* hide file input */

#photoUpload{
display:none;
}

</style>

</head>

<body>

<div class="container mt-5">

<h2 class="mb-4">Student Profile</h2>

<div class="card shadow profile-card p-4">

<div class="row">

<!-- PHOTO SECTION -->

<div class="col-md-4 text-center">

<div class="position-relative d-inline-block">

<%
String photo = s.getPhoto();

if(photo != null && !photo.trim().equals("")){
%>

<a href="photos/<%=s.getPhoto()%>" target="_blank">

<img src="photos/<%=s.getPhoto()%>" 
class="profile-photo mb-3"
style="cursor:pointer">

</a>

<%
}else{
%>

<img src="https://via.placeholder.com/180" class="profile-photo">

<%
}
%>

<!-- EDIT ICON -->

<label for="photoUpload" class="edit-icon">
<i class="bi bi-pencil"></i>
</label>

</div>

<!-- AUTO UPLOAD FORM -->

<form id="photoForm" action="uploadPhoto" method="post" enctype="multipart/form-data">

<input type="hidden" name="regd_no" value="<%=s.getId()%>">

<input id="photoUpload" type="file" name="photo" onchange="document.getElementById('photoForm').submit();">

</form>

</div>


<!-- STUDENT DETAILS -->

<div class="col-md-8">

<h4 class="mb-3">Student Details</h4>

<table class="table table-borderless">

<tr>
<th width="200">Registration No</th>
<td><%=s.getId()%></td>
</tr>

<tr>
<th>Name</th>
<td><%=s.getName()%></td>
</tr>

<tr>
<th>Branch</th>
<td><%=s.getBranch()%></td>
</tr>

<tr>
<th>Age</th>
<td><%=s.getAge()%></td>
</tr>

</table>

<a href="editProfile" class="btn btn-primary mt-3">
Edit Profile
</a>

<a href="studentDashboard" class="btn btn-secondary mt-3">Back to Dashboard</a>

</div>

</div>

</div>

</div>

</body>
</html>