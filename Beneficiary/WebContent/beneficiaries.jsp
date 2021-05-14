<%@ page import="com.Beneficiary"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Beneficiary Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/beneficiaries.js"></script>
<meta name="viewpoint" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}


input[type=text] {
  width: 100%;
  padding: 7px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 1px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=password]{
  width: 100%;
  padding: 7px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 1px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  color: white;
  padding: 8px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}


h1{
  font-family: Arial, Helvetica, sans-serif;
  font-size: 30px;
  text-decoration:underline;
}
.container {
  border-radius: 5px;
  padding: 30px; 
}

.formBody{
	background:#f1f1f1;
	border-radius: 15px;
	padding: 25px;
}
.form-lable{
	font-size :20px;
	margin-left: 10px;
}

</style>

</head>
<body>


<div class="container"><div class="row">
<div class="col-md-12 ">

	<h1>Inventors Management</h1> <br>

	<div class="formBody">
		<form id='formBeneficiary' name='formBeneficiary' method='post' action='beneficiaries.jsp'>
	
			<!-- Inventor Name -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
						<span class="form-lable" id="lblName">Inventor user name: </span>
						</div>
						<input type="text" id='inventorUsername' name='inventorUsername' placeholder='Please enter your first name'>
					</div>
					
			<!-- Inventor Age -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
						<span class="form-lable" id="lblName">Inventor age: </span>
						</div>
						<input type="text" id='inventorAge' name='inventorAge' placeholder='enter your age'>
					</div>
					
			<!-- Inventor Address -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
						<span class="form-lable" id="lblName">Inventor address: </span>
						</div>
						<input type="text" id='inventorAddress' name='inventorAddress' placeholder='enter your address'>
					</div>
					
			<!-- Inventor Password -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
						<span class="form-lable" id="lblName">Inventor password: </span>
						</div>
						<input type="password" id='inventorPassword' name='inventorPassword' placeholder='minimum 6 characters with a letter'>
					</div>
		
				<input id="btnSave" name="btnSave" type="button" value="Save" class='btn btn-primary'>
				<input type='hidden' id='hidInventorIDSave' name='hidInventorIDSave' value=''>
		</form>
	</div>
	
	<br>

	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	<br> <br>
	<div id="divBeneficiariesGrid">
	<%
		Beneficiary benObj = new Beneficiary();
		out.print(benObj.readInventors());
	%>

	</div>
	
</div> </div> </div>

</body>
</html>