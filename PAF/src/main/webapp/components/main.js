$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateComplaintForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidApp_IDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "ComplaintAPI",
		type : t,
		data : $("#formApprovement").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onComplaintSaveComplete(response.responseText, status);
		}
	});
}); 

function onComplaintSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidApp_IDSave").val("");
	$("#formApprovement")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidApp_IDSave").val($(this).closest("tr").find('#hidApp_IDUpdate').val());     
	$("#complaintCategory").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#complaintType").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#accountNo").val($(this).closest("tr").find('td:eq(2)').text());   
	$("#name").val($(this).closest("tr").find('td:eq(3)').text());   
	$("#mobileno").val($(this).closest("tr").find('td:eq(4)').text());     
	$("#address").val($(this).closest("tr").find('td:eq(5)').text()); 
	

});

//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "ComplaintAPI",
		type : "DELETE",
		data : "complaintId=" + $(this).data("complaintId"),
		dataType : "text",
		complete : function(response, status)
		{
			onComplaintDeletedComplete(response.responseText, status);
		}
	});
});



function onComplaintDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateComplaintForm() {  
	// app_status  
	if ($("#complaintCategory").val().trim() == "")  {   
		return "Insert complaintCategory.";  
		
	} 
	
	 // app_Details  
	if ($("#complaintType").val().trim() == "")  {   
		return "Insert complaintType.";  
		
	} 
	 
	
	 
	 // Email 
	if ($("#accountNo").val().trim() == "")  {   
		return "Insert accountNo.";  
		
	} 
	
	// endorser_type  
	if ($("#name").val().trim() == "")  {   
		return "Insert name.";  
		
	} 
	 
	 // endorser_type  
	if ($("#mobileno").val().trim() == "")  {   
		return "Insert mobileno.";  
		
	} 
	
	// endorser_type  
	if ($("#address").val().trim() == "")  {   
		return "Insert address.";  
		
	} 
 
	 
	 return true; 
	 
}
