<!DOCTYPE html>
<html>
<head>

<title>College Portal Login</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>

body{
background:#f1f5f9;
font-family:Segoe UI;
height:100vh;
display:flex;
align-items:center;
justify-content:center;
}

.login-box{
width:350px;
}

</style>

</head>

<body>

<div class="login-box">

<div class="card shadow p-4">

<h3 class="text-center mb-4">College Portal</h3>

<!-- LOGIN DROPDOWN -->

<div class="dropdown text-center">

<button class="btn btn-primary dropdown-toggle w-100" data-bs-toggle="dropdown">
Login
</button>

<ul class="dropdown-menu w-100">

<li>
<a class="dropdown-item text-center" href="studentLogin.jsp">
Student Login
</a>
</li>

<li>
<a class="dropdown-item text-center" href="staffLogin.jsp">
Staff Login
</a>
</li>

</ul>

</div>

</div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>