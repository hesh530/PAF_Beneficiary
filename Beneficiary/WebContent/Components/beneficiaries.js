$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

//Save--------------------------------
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateBeneficiaryForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var type = ($("#hidInventorIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "BeneficiariesAPI",
		type : type,
		data : $("#formBeneficiary").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onBeneficiarySaveComplete(response.responseText, status);
		}
		});
	
});

function onBeneficiarySaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divBeneficiariesGrid").html(resultSet.data);
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
	$("#hidInventorIDSave").val("");
	$("#formBeneficiary")[0].reset();
}

//remove
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "BeneficiariesAPI",
		type : "DELETE",
		data : "inventorID=" + $(this).data("inventorid"),
		dataType : "text",
		complete : function(response, status)
		{
			onBeneficiaryDeleteComplete(response.responseText, status);
		}
	});
});

function onBeneficiaryDeleteComplete(response, status)
{
	if (status == "success")
	{
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
		$("#alertSuccess").text("Successfully deleted.");
		$("#alertSuccess").show();
		$("#divBeneficiariesGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
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



//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidInventorIDSave").val($(this).closest("tr").find('#hidInventorIDUpdate').val());
	$("#inventorUsername").val($(this).closest("tr").find('td:eq(0)').text());
	$("#inventorAge").val($(this).closest("tr").find('td:eq(1)').text());
	$("#inventorAddress").val($(this).closest("tr").find('td:eq(2)').text());
	$("#inventorPassword").val($(this).closest("tr").find('td:eq(3)').text());
});


//CLIENT-MODEL================================================================
function validateBeneficiaryForm()
{
	// CODE
	if ($("#inventorUsername").val().trim() == "")
	{
		return "Insert Inventor Username.";
	}
	
	// NAME
	if ($("#inventorAge").val().trim() == "")
	{
		return "Insert Inventor Age.";
	}
	
	// PRICE-------------------------------
	if ($("#inventorAddress").val().trim() == "")
	{
		return "Insert Inventor Address.";
	}
	
	// DESCRIPTION------------------------
	if ($("#inventorPassword").val().trim() == "")
	{
		return "Insert Inventor Password.";
	}
	
	return true;
}