<!DOCTYPE html>
<html>
<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Add Student</title>
</head>
<body>

<nav class="navbar navbar-dark bg-dark">
  <div class="container-fluid">
    <span class="navbar-brand mb-0 h1">College Portal</span>
    <div>
      <a href="add-student.jsp" class="btn btn-success">Add Student</a>
      <a href="viewStudents" class="btn btn-primary">View Students</a>
    </div>
  </div>
</nav>

<h2>Student Management System</h2>
<h3>Add Student</h3>

<div class="container mt-5">
  <div class="card">
    <div class="card-header bg-primary text-white">
      Add Student
    </div>
    <div class="card-body">

      <form action="addStudent" method="post">

        <div class="mb-3">
          <label class="form-label">ID</label>
          <input type="text" name="id" class="form-control">
        </div>

        <div class="mb-3">
          <label class="form-label">Name</label>
          <input type="text" name="name" class="form-control">
        </div>

        <div class="mb-3">
          <label class="form-label">Age</label>
          <input type="text" name="age" class="form-control">
        </div>

        <div class="mb-3">
		<label class="form-label">Branch</label>
		
		<select name="branch" class="form-control">
		
		<option value="CSE">CSE</option>
		<option value="CSIT">CSIT</option>
		<option value="ECE">ECE</option>
		<option value="EEE">EEE</option>
		<option value="MECH">MECH</option>
		<option value="AIML">AIML</option>
		<option value="CSSE">CSSE</option>
		
		</select>

		</div>

        <button type="submit" class="btn btn-success">Save Student</button>

<a href="staffDashboard" class="btn btn-secondary ms-2">Back to Dashboard</a>
      </form>

    </div>
  </div>
</div>

</body>
</html>
