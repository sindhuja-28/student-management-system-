<%@ page import="java.sql.*" %>
<%@ page import="com.sms.util.DBConnection" %>

<!DOCTYPE html>
<html>
<head>
<title>All Courses</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

</head>

<body class="bg-light">

<div class="container mt-5">

<h2 class="mb-4">All Available Courses</h2>

<table class="table table-bordered">

<tr>
<th>Course Name</th>
</tr>

<%
try{
    Connection con = DBConnection.getConnection();
    PreparedStatement ps = con.prepareStatement("SELECT * FROM available_courses");
    ResultSet rs = ps.executeQuery();

    boolean found = false;

    while(rs.next()){
        found = true;
%>

<tr>
<td><%= rs.getString("course_name") %></td>
</tr>

<%
    }

    if(!found){
%>
<tr>
<td class="text-center text-danger">No Courses Found</td>
</tr>
<%
    }

}catch(Exception e){
    e.printStackTrace();
}
%>

</table>

<a href="staffDashboard" class="btn btn-secondary">Back</a>

</div>

</body>
</html>