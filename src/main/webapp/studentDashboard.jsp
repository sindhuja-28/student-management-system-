<%@ page import="java.util.*,com.sms.model.Course" %>
<%@ page import="com.sms.dao.CourseDAO" %>

<%
String regd = (String) session.getAttribute("regd_no");
List<Course> list = CourseDAO.getCoursesByStudent(regd);
String msg = (String) session.getAttribute("msg");
%>

<!DOCTYPE html>
<html>
<head>
<title>Student Dashboard</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

</head>

<body class="bg-light">

<div class="container mt-5">

<h2>My NPTEL Courses</h2>

<!-- ✅ SUCCESS MESSAGE -->
<%
if(msg != null){
%>
<div class="alert alert-success alert-dismissible fade show mt-3">
    <%= msg %>
    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
</div>
<%
session.removeAttribute("msg");
}
%>




<table class="table table-bordered mt-3">

<tr>
<th>Course</th>
<th>Payment</th>
<th>Start</th>
<th>End</th>
<th>Result</th>
</tr>

<%
if(list != null && !list.isEmpty()){
    for(Course c : list){
%>

<tr>
<td><%= c.getCourse_name() %></td>
<td><%= c.getPayment_status() %></td>
<td><%= c.getStart_date() %></td>
<td><%= c.getEnd_date() %></td>
<td>
<%
if(c.getResult() == null){
%>
<span class="badge bg-warning">In Progress</span>
<%
}else if("Pass".equals(c.getResult())){
%>
<span class="badge bg-success">Pass</span>
<%
}else{
%>
<span class="badge bg-danger">Fail</span>
<%
}
%>
</td>
</tr>

<%
    }
}else{
%>

<tr>
<td colspan="5" class="text-center text-danger">No Courses Found</td>
</tr>

<%
}
%>

</table>

<a href="addCourse.jsp" class="btn btn-primary">Add Course</a>

</div>

</body>
<script>
setTimeout(() => {
    let alertBox = document.querySelector('.alert');
    if(alertBox){
        alertBox.style.display = 'none';
    }
}, 3000);
</script>
</html>