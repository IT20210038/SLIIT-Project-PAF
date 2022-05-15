<%@page import="model.Complaint"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Complaint Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="components/jquery-3.6.0.min.js"></script>
<script src="components/main.js"></script>
</head>
<body style="background-color:lightblue;">

<div class="container"> 
		<div class="row">  
		
			<div class="col-8">       
				<h1 class="m-3">Complaint Management</h1>        
				
				<form id="formApprovement" name="formApprovement" method="post" action="Complaint.jsp">  
					Complaint category:  
					<input id="complaintCategory" name="complaintCategory" type="text" class="form-control form-control-sm">  
					
					<br> 
					Complaint Type:  
					<input id="complaintType" name="complaintType" type="text" class="form-control form-control-sm">  
					
					<br>
					 Account no:  
					 <input id="accountNo" name="accountNo" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 name:  
					 <input id="name" name="name" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 mobile number:  
					 <input id="mobileno" name="mobileno" type="text" class="form-control form-control-sm">  
					 
					 <br>
					  address:  
					 <input id="address" name="address" type="text" class="form-control form-control-sm">  
					 
					 
					 
					 <br>  
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					 <input type="hidden" id="hidApp_IDSave" name="hidApp_IDSave" value=""> 
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>  
				<div id="divItemsGrid">   
					<%
   					Complaint appObj = new Complaint();
   									out.print(appObj.readComplaint());
   					%>  
					
				</div> 
				  
 			</div>
 		 
 		</div>    
 		
 
	</div> 

</body>
</html>