<%@ page import="com.sms.model.Student" %>

<%
Student student = (Student) request.getAttribute("student");
%>
<!DOCTYPE html>
<html>
<head>
<title>Update Student</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

</head>

<body style="background-color:#f4f6f9;">

<div class="container mt-5">

<h2 class="text-center mb-4">Update Student</h2>

<div class="card shadow p-4">

<form action="updateStudent" method="post">

<div class="mb-3">
<label class="form-label">Student ID</label>
<input type="text" name="id" value="${student.id}" class="form-control" readonly>
</div>

<div class="mb-3">
<label class="form-label">Name</label>
<input type="text" name="name" value="${student.name}" class="form-control">
</div>

<div class="mb-3">
<label class="form-label">Age</label>
<input type="number" name="age" value="${student.age}" class="form-control">
</div>

<div class="mb-3">
<label class="form-label">Branch</label>

<select name="branch" class="form-control">

<option value="CSE" <%= "CSE".equals(student.getBranch()) ? "selected" : "" %>>CSE</option>

<option value="CSIT" <%= "CSIT".equals(student.getBranch()) ? "selected" : "" %>>IT</option>

<option value="AIML" <%= "AIML".equals(student.getBranch()) ? "selected" : "" %>>AIML</option>

<option value="CSSE" <%= "CSSE".equals(student.getBranch()) ? "selected" : "" %>>CSSE</option>

<option value="ECE" <%= "ECE".equals(student.getBranch()) ? "selected" : "" %>>ECE</option>

<option value="EEE" <%= "EEE".equals(student.getBranch()) ? "selected" : "" %>>EEE</option>

<option value="MECH" <%= "MECH".equals(student.getBranch()) ? "selected" : "" %>>MECH</option>

</select>
</div>

<button type="submit" class="btn btn-primary">Update Student</button>

<a href="viewStudents" class="btn btn-secondary ms-2">Back</a>

</form>

</div>

</div>

</body>
</html>