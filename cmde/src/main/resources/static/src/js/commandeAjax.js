$(document).ready(function() {
	/*  
	 * ****************************************************************** *
	 ******************* update status commande : Ajax POST ********************
	 * ****************************************************************** *
	*/
	
	$("#dateLivraison").change(function() {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		
		/* ** update date livraison ** */
		ajaxUpDatePost();
		
	});

	
	function ajaxUpDatePost() {
		// PREPARE FORM DATA
		var Data = {
			dateL : $("#dateLivraison").val(),
			numCmde : $("#numCmde").val(),
			idCmde  : $("#idCmde").val()
		}
		
		// DO POST
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "api/article/updateDL",
			data : JSON.stringify(Data),
			dataType : 'json',
			success : function(result) {
				if (result.status == "Date") {
					alert("Date Livraison mise à jour")
				} else {
					alert("Error d'ajout")
				}
				console.log(result);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
	}	
	
	
	// ***** View and edit commande *****
//	$( "#editDL" ).click(function() {
//		alert("Hello Boys");
//		// alert( "Handler for .click() called." );
//	});
	
})
	