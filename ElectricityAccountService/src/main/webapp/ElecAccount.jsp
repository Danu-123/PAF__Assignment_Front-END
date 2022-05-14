<%@page import="model.ElecAccount"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.6.0.min.js"></script>
        <script src="Components/ElecAccount.js"></script>
<title>Electricity Account</title>


</head>
<body>

<div class="container">
			<div class="row">
				<div class="col">
					<h1>Electricity Account Management</h1>
					<form id="formItem" name="formItem" method="POST" action="ElecAccount.jsp">
						Account Number: 
						<input 
							id="Account_number" 
							name="Account_number" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						Account Name: 
						<input 
							id="Account_name"
							name="Account_name" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						Premises ID: 
						<input 
							id="Premises_id" 
							name="Premises_id" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						Customer ID: 
						<input 
							id="Cus_id" 
							name="Cus_id" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						<input 
							id="btnSave" 
							name="btnSave" 
							type="button" 
							value="Save" 
							class="btn btn-primary"
						>

						<input type="hidden" name="hidAccIDSave" id="hidAccIDSave" value="">
						<br><br>
					</form>
				
					<br>
					<div id="alertSuccess" class="alert alert-success">
						
					</div>
					<div id="alertError" class="alert alert-danger"></div>
					<br>
					<div id="divItemGrid">
					<%
						ElecAccount elec = new ElecAccount(); 
						out.print(elec.readElecAccounts());
					%>
				</div>
			</div>
		</div>
</div>
</body>
</html>