$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertError").hide();
});


// SAVE ============================================

$(document).on("click", "#btnSave", function(event)
{

// Clear alerts---------------------
$("#alertSuccess").text("");
$("#alertSuccess").hide();
$("#alertError").text("");
$("#alertError").hide();

// Form validation-------------------

var status = validateItemForm();
if (status != true)
{
$("#alertError").text(status);
$("#alertError").show();
return;
}

// If valid------------------------


var type = ($("#hidAccIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "ElecAccountAPI", 
 type : type, 
 data : $("#formItem").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onAccountSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onAccountSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
$("#hidAccIDSave").val(""); 
$("#formItem")[0].reset(); 
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidAccIDSave").val($(this).data("accid"));
$("#Account_number").val($(this).closest("tr").find('td:eq(0)').text());
$("#Account_name").val($(this).closest("tr").find('td:eq(1)').text());
$("#Premises_id").val($(this).closest("tr").find('td:eq(2)').text());
$("#Cus_id").val($(this).closest("tr").find('td:eq(3)').text());
});

//DELETE

$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "ElecAccountAPI", 
		 type : "DELETE", 
		 data : "AccID=" + $(this).data("accid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onAccountDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
function onAccountDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 
 $("#divItemsGrid").html(resultSet.data); 
 } 
 else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}

// CLIENT-MODEL================================================================
function validateItemForm()
{
// Account Number
if ($("#Account_number").val().trim() == "")
{
return "Insert Account Number.";
}


// is numerical value
var tmpAccNo = $("#Account_number").val().trim();
if (!$.isNumeric(tmpAccNo))
{
return "Insert a numerical value for Account Number.";
}
// convert to integer
$("#Account_number").val(parseFloat(tmpAccNo));



// NAME
if ($("#Account_name").val().trim() == "")
{
return "Insert Account Name.";
}
9
// Premises id
if ($("#Premises_id").val().trim() == "")
{
return "Insert Premises ID.";
}



// Cus id
if ($("#Cus_id").val().trim() == "")
{
return "Insert Customer ID.";
}
return true;
}