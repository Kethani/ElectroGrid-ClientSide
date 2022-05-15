$(document).ready(function()
{
        if ($("#alertSuccess").text().trim() == "")
        {
               $("#alertSuccess").hide();
        }
               $("#alertError").hide();
});



// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	
        // Clear status---------------
        $("#alertSuccess").text("");
        $("#alertSuccess").hide();
        $("#alertError").text("");
        $("#alertError").hide();

        // Form validation----------------
        var status = validatePaymentForm();
        if (status != true)
        {
             $("#alertError").text(status);
             $("#alertError").show();
             return;
         }
        
         // If valid----------------------
         var type = ($("#hidPayment_IDSave").val() == "") ? "POST" : "PUT";
        //$("#formPayment").submit();

        $.ajax(
        {
             url : "PaymentAPI",
             type : type,
             data : $("#formPayment").serialize(),
             dataType : "text",
             complete : function(response, status)
             {
                      onPaymentSaveComplete(response.responseText, status);
             }
        });
});



//UPDATE ========================================
$(document).on("click", ".btnUpdate", function(event)
{
        //$("#hidPayment_IDSave").val($(this).closest("tr").find('#hidPayment_IDUpdate').val());
        $("#hidPayment_IDSave").val($(this).data("paymnt_id"));
        $("#date_time").val($(this).closest("tr").find('td:eq(0)').text());
        $("#user_address").val($(this).closest("tr").find('td:eq(1)').text());
        $("#amount").val($(this).closest("tr").find('td:eq(2)').text());
        $("#payment_method").val($(this).closest("tr").find('td:eq(3)').text());
        
});


//REMOVE=======================================================================
$(document).on("click", ".btnRemove", function(event)
{
	
        $.ajax(
        {
              url : "PaymentAPI",
              type : "DELETE",
              data : "paymnt_id=" + $(this).data("paymnt_id"),
              dataType : "text",
              complete : function(response, status)
              {
                    onPaymentDeleteComplete(response.responseText, status);
              }
         });
});


//CLIENT-MODEL=================================================================
function onPaymentSaveComplete(response, status)
{
         if (status == "success")
         {
               var resultSet = JSON.parse(response);
                    if (resultSet.status.trim() == "success")
                    {
                           $("#alertSuccess").text("Successfully saved.");
                           $("#alertSuccess").show();
                           $("#divPaymentsGrid").html(resultSet.data);

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
           $("#hidPayment_IDSave").val("");
           $("#formPayment")[0].reset();
}


function onPaymentDeleteComplete(response, status)
{
	
         if (status == "success")
         {
        	 
               var resultSet = JSON.parse(response);
               if (resultSet.status.trim() == "success")
               {
            	   
                        $("#alertSuccess").text("Successfully deleted.");
                        $("#alertSuccess").show();

                        
                        $("#divPaymentGrid").html(resultSet.data);
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



function validatePaymentForm()
{
       //DATETIME
       if ($("#date_time").val().trim() == "")
       {
               return "Insert Date & Time.";
        }
       
       //ADDRESS
       if ($("#user_address").val().trim() == "")
       {
               return "Insert User Address.";
       }
       
       //AMOUNT
       if ($("#Amount").val().trim() == "")
       {
               return "Insert Amount.";
       }
       
       //PAYMENTMETHOD
       if ($("#payment_method").val().trim() == "")
       {
               return "Insert Payment Method.";
       }
       
       
       return true;

}