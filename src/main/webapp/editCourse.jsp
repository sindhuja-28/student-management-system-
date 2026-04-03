<%@ page import="java.sql.*,com.sms.util.DBConnection" %>

<%
int id = Integer.parseInt(request.getParameter("id"));

Connection con = DBConnection.getConnection();
PreparedStatement ps = con.prepareStatement(
    "SELECT * FROM nptel_courses WHERE id=?"
);

ps.setInt(1,id);

ResultSet rs = ps.executeQuery();
rs.next();
%>

<h2>Edit Course</h2>

<form action="updateCourse" method="post">

<input type="hidden" name="id" value="<%= id %>">

Course Name:
<input type="text" name="course_name" value="<%= rs.getString("course_name") %>"><br><br>

Payment:
<input type="text" name="payment_status" value="<%= rs.getString("payment_status") %>"><br><br>

<button>Update</button>

</form>