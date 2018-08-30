/**
 * Ajax POST add article
 */

/*
	$("#infoClient #createCmde").prop('disabled', false);
	var idClient = $("#selectClient").val();
	$("#idClient").val(idClient);
	
	quantite, remise, idArticle, idCmdeAct 
*/

$( document ).ready(function() {
	
	/* ** Select article ** */
	$("table #selectArticle").change(function() {  
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxSelectPost();
	});
	
	function ajaxSelectPost() { 	
	    // PREPARE FORM DATA
	   	var formData = {
	   		idArticle : $("#selectArticle").val()
    	}
	    	
	   	// DO POST
	   	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "api/article/selectArticle",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "Article"){
					// alert("Article selected")
					
					$("#libelArticle").val(result.data["libelleArticle"]);
					$("#prix").val(result.data["prixHT"]);
					$("#tva").val(result.data["tva"]);
					$("#idA").val(result.data["idArticle"]);
				}else{
					alert("Error de selection")
				}
				console.log(result);
				
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
	   	
	   	// Hide commande input hideDivCmde();
	   	// $('#artSelected').show();
	}
	
	
	/* *** Add ligne commande (article) *** */
    $("#lCmdeForm").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxArticlePost();
	});
    
    function ajaxArticlePost(){
    	// PREPARE FORM DATA
    	var formData = {
    		quantite : $("#quantite").val(),
    		remise :  $("#remise").val(),
    		idArticle :  $("#idA").val(),
    		idCmde :  $("#idCmdeActuel").val()
    		
    		// idArticle :  $("#libelArticle").val(),
    	}
    	
      	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "api/article/saveLCmde",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "Done"){
					alert("Ligne ajouté")
				}else{
					alert("Error d'ajout")
				}
				console.log(result);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    	
    	// Reset FormData after Posting
    	resetData();
    }
    
    function resetData(){
    	$("#quantite").val("");
		$("#remise").val("");
		$("#libelArticle").val("");
		$("#prix").val("");
    }
})