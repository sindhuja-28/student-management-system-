<!DOCTYPE html>
<html>
<head>

<title>Student Login</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

</head>

<body style="background:#f1f5f9">

<div class="container mt-5">

<div class="row justify-content-center">

<div class="col-md-4">

<div class="card shadow p-4">

<h4 class="text-center mb-4">Student Login</h4>

<form action="login" method="post">

<input type="hidden" name="role" value="student">

<div class="mb-3">

<label>Username</label>

<input type="text" name="username" class="form-control" required>

</div>

<div class="mb-3">

<label>Password</label>

<input type="password" name="password" class="form-control" required>

</div>

<button class="btn btn-primary w-100">Login</button>

</form>

<div class="text-center mt-3">

<a href="<%=request.getContextPath()%>/forgotPassword.jsp">
Forgot Password?
</a>

</div>

</div>

</div>

</div>

</div>

</body>
</html>