<!DOCTYPE html>
<html>
<head>
<title>College Portal Dashboard</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
body{
    background-color:#f4f6f9;
}

.sidebar{
    height:100vh;
    background:#343a40;
    color:white;
    padding-top:20px;
}

.sidebar a{
    color:white;
    text-decoration:none;
    display:block;
    padding:12px;
}

.sidebar a:hover{
    background:#495057;
}

.dashboard-card{
    border:none;
}
</style>

</head>

<body>

<div class="container-fluid">

<div class="row">

<!-- Sidebar -->
<div class="col-md-2 sidebar">

<h4 class="text-center">College Portal</h4>
<hr>

<a href="dashboard">Dashboard</a>
<a href="add-student.jsp">Add Student</a>
<a href="viewStudents">View Students</a>

</div>


<!-- Main Content -->
<div class="col-md-10 p-4">

<h2 class="mb-4">Dashboard</h2>

<div class="row">

<!-- Total Students -->
<div class="col-md-4">
<div class="card dashboard-card shadow text-center">
<div class="card-body">

<h5>Total Students</h5>

<h1 class="text-primary">${studentCount}</h1>

<p>Students registered</p>

</div>
</div>
</div>

<!-- Add Student -->
<div class="col-md-4">
<div class="card dashboard-card shadow text-center">
<div class="card-body">

<h5>Add Student</h5>

<p>Register a new student</p>

<a href="add-student.jsp" class="btn btn-success">Add Student</a>

</div>
</div>
</div>

<!-- View Students -->
<div class="col-md-4">
<div class="card dashboard-card shadow text-center">
<div class="card-body">

<h5>View Students</h5>

<p>See all student records</p>

<a href="viewStudents" class="btn btn-primary">View Students</a>

</div>
</div>
</div>

</div>

</div>

</div>

</div>

</body>
</html>