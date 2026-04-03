<!DOCTYPE html>
<html>
<head>

<title>Add NPTEL Course</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<script>

/* ===== CALCULATE END DATE ===== */
function calculateEndDate(){

    let start = document.getElementById("start_date").value;
    let weeks = document.getElementById("duration").value;

    if(start && weeks){

        let startDate = new Date(start);

        startDate.setDate(startDate.getDate() + (weeks * 7));

        let end = startDate.toISOString().split("T")[0];

        document.getElementById("end_date").value = end;

        checkCompletion();
    }
}

/* ===== CHECK COURSE COMPLETION ===== */
function checkCompletion(){

    let end = document.getElementById("end_date").value;

    if(!end) return;

    let today = new Date();
    let endDate = new Date(end);

    if(today >= endDate){
        document.getElementById("resultDiv").style.display = "block";
    }else{
        document.getElementById("resultDiv").style.display = "none";
        document.getElementById("certificateDiv").style.display = "none";
    }
}

/* ===== SHOW CERTIFICATE ONLY IF PASS ===== */
function checkResult(){

    let result = document.getElementById("result").value;
    let certInput = document.querySelector("input[name='certificate']");

    if(result === "Pass"){
        document.getElementById("certificateDiv").style.display = "block";
        certInput.setAttribute("required", "required");
    }else{
        document.getElementById("certificateDiv").style.display = "none";
        certInput.removeAttribute("required");
    }
}

window.onload = function(){
    checkCompletion();
};

</script>

</head>

<body style="background:#f4f6f9">

<div class="container mt-5">

<h2 class="mb-4">Add NPTEL Course</h2>

<div class="card shadow p-4">

<form action="addCourse" method="post" enctype="multipart/form-data">

<!-- ✅ COURSE NAME (MANUAL INPUT) -->
<div class="mb-3">
<label class="form-label">Course Name</label>
<input type="text" name="course_name" class="form-control" placeholder="Enter course name" required>
</div>

<!-- Payment -->
<div class="mb-3">
<label class="form-label">Payment Status</label>
<select name="payment_status" class="form-control">
<option value="Paid">Paid</option>
<option value="Not Paid">Not Paid</option>
</select>
</div>

<!-- Start Date -->
<div class="mb-3">
<label class="form-label">Start Date</label>
<input type="date" id="start_date" name="start_date" class="form-control" onchange="calculateEndDate()" required>
</div>

<!-- Duration -->
<div class="mb-3">
<label class="form-label">Duration (Weeks)</label>
<input type="number" id="duration" name="duration_weeks" class="form-control" oninput="calculateEndDate()" required>
</div>

<!-- End Date -->
<div class="mb-3">
<label class="form-label">End Date (Auto)</label>
<input type="date" id="end_date" name="end_date" class="form-control" readonly>
</div>

<!-- RESULT -->
<div class="mb-3" id="resultDiv" style="display:none">
<label class="form-label">Result</label>
<select name="result" id="result" class="form-control" onchange="checkResult()">
<option value="">Select</option>
<option value="Pass">Pass</option>
<option value="Fail">Fail</option>
</select>
</div>

<!-- CERTIFICATE -->
<div class="mb-3" id="certificateDiv" style="display:none">
<label class="form-label">Upload Certificate</label>
<input type="file" name="certificate" class="form-control">
</div>

<button class="btn btn-success">Save Course</button>

<a href="studentDashboard" class="btn btn-secondary">Back</a>

</form>

</div>

</div>

</body>
</html>