<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.sms.model.Student" %>

<%
Student s = (Student) request.getAttribute("student");
%>

<!DOCTYPE html>
<html>
<head>

<title>Edit Profile</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>

body{
background:#f4f6f9;
font-family:Segoe UI;
}

.profile-photo{
width:160px;
height:160px;
border-radius:50%;
object-fit:cover;
border:5px solid #e2e8f0;
}

.card{
border:none;
border-radius:12px;
}

</style>

</head>

<body>

<div class="container mt-5">

<div class="card shadow p-4">

<h3 class="mb-4">Edit Profile</h3>

<form action="updateProfile" method="post">

<input type="hidden" name="id" value="<%=s.getId()%>">

<div class="row">

<!-- PHOTO SECTION -->

<div class="col-md-4 text-center">

<%
String photo = s.getPhoto();

if(photo!=null && !photo.equals("")){
%>

<img src="photos/<%=photo%>" class="profile-photo">

<%
}else{
%>

<img src="https://via.placeholder.com/160" class="profile-photo">

<%
}
%>

<p class="text-muted mt-3">Profile Photo</p>

</div>

<!-- EDIT DETAILS -->

<div class="col-md-8">

<div class="mb-3">
<label class="form-label">Name</label>
<input type="text" name="name" value="<%=s.getName()%>" class="form-control">
</div>

<div class="mb-3">
<label class="form-label">Age</label>
<input type="number" name="age" value="<%=s.getAge()%>" class="form-control">
</div>

<div class="mb-3">
<label class="form-label">Branch</label>

<select name="branch" class="form-control">

<option <%=s.getBranch().equals("CSE")?"selected":""%>>CSE</option>
<option <%=s.getBranch().equals("CSIT")?"selected":""%>>CSIT</option>
<option <%=s.getBranch().equals("CSSE")?"selected":""%>>CSSE</option>
<option <%=s.getBranch().equals("AIML")?"selected":""%>>AIML</option>
<option <%=s.getBranch().equals("ECE")?"selected":""%>>ECE</option>
<option <%=s.getBranch().equals("EEE")?"selected":""%>>EEE</option>
<option <%=s.getBranch().equals("MECH")?"selected":""%>>MECH</option>

</select>

</div>

<button class="btn btn-success">Update Profile</button>

<a href="studentProfile" class="btn btn-secondary">Cancel</a>

</div>

</div>

</form>

</div>

</div>

</body>
</html>