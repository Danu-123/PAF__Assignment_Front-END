/**
 * 
 */
$(document).ready(function() 
{ 
 $("#alertSuccess").hide(); 
 $("#alertError").hide(); 
}); 

$(document).on("click", "#btnSave", function(event) 
{ 
	
	// Clear status msges---------------------
$("#alertSuccess").text(""); 
$("#alertSuccess").hide(); 
$("#alertError").text(""); 
$("#alertError").hide();
});