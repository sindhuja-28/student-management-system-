<!DOCTYPE html>
<html>
<head>

<title>Reset Password</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

</head>

<body style="background:#f1f5f9">

<div class="container mt-5">

<div class="row justify-content-center">

<div class="col-md-4">

<div class="card shadow p-4">

<h4 class="text-center mb-4">Forgot Password</h4>

<form action="resetPassword" method="post">

<div class="mb-3">
<label>Registration No</label>
<input type="text" name="regd_no" class="form-control" required>
</div>

<div class="mb-3">
<label>Email</label>
<input type="email" name="email" class="form-control" required>
</div>

<div class="mb-3">
<label>New Password</label>
<input type="password" name="password" class="form-control" required>
</div>

<button class="btn btn-success w-100">Reset Password</button>

</form>

</div>

</div>

</div>

</div>

</body>
</html>