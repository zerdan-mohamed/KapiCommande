$(document).ready(function() {
	/*  
	 * ****************************************************************** *
	 *************** Demand article information  : Ajax POST **************
	 * ****************************************************************** *
	*/
	$("table #selectArticle").change(function() {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		
		/* ** Select article ** */
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
				if (result.status == "Article") {
					// alert("Article selected")

					$("#libelArticle").val(result.data["libelleArticle"]);
					$("#prix").val(result.data["prixHT"]);
					$("#tva").val(result.data["tva"]);
					$("#idA").val(result.data["idArticle"]);
				} else {
					alert("Error de selection")
				}
				console.log(result);

			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
	}
	
	
	/*  
	 * ****************************************************************** *
	 ******************* Add commande line : Ajax POST ********************
	 * ****************************************************************** *
	*/
	$("#lCmdeForm").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxArticlePost();
	});

	function ajaxArticlePost() {
		// PREPARE FORM DATA
		var formData = {
			quantite : $("#quantite").val(),
			remise : $("#remise").val(),
			idArticle : $("#idA").val(),
			idCmde : $("#idCmdeActuel").val()
		}
		
		// DO POST
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "api/article/saveLCmde",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {				
				if (result.status == "Commande") {
					// alert("Ligne ajouté")
					// $.each( result.data.article, function( key, value ) { alert( key + ": " + value ); }); 
					

					
					var total = parseFloat(result.data.article["prixHT"] * result.data["quantite"]);
					
					var totalRm = parseFloat((result.data["remise"] * total)/100);
					var totalHT = parseFloat(total - totalRm);
					var totalTVA = parseFloat((totalHT * result.data.article["tva"])/100);
					var totalTTC = parseFloat(totalHT + totalTVA);
					
					var trHTML = '<tr>';
						trHTML += '<td>' + result.data.article["libelleArticle"] + '</td>';
				    	trHTML += '<td>' + result.data.article["prixHT"] + '</td>';
				    	trHTML += '<td>' + result.data.article["tva"] + '</td>';
				    	trHTML += '<td>' + result.data["quantite"] + '</td>';
				    	trHTML += '<td>' + result.data["remise"] + '</td>';
				    	trHTML += '<td>' + totalTTC + '</td>';
				    	trHTML += '</tr>';
				    
				    $('#GLtable tbody').append(trHTML);
				    
				    $("#validCmde").prop('disabled', false);
				    $("#imprimerCmde").prop('disabled', false);
				    $("#infoCmdeSupp").css("display", "block");
				    
				    // set Total HT prices
				    var NTotalHT = parseFloat(document.getElementById('totalHT').value) + totalHT ;
				    $("#totalHT").prop('value', NTotalHT);
				    
				    
				    // set Total Remise prices
				    var NTotalRm = parseFloat(document.getElementById('totalRm').value) + totalRm;
				    $("#totalRm").prop('value', NTotalRm);
				    
				    // set Total TVA prices
				    var NTotalTVA = parseFloat(document.getElementById('totalTVA').value) + totalTVA;
				    $("#totalTVA").prop('value', NTotalTVA);
				    
				    // set Total TTC prices
				    var NTotalTTC = parseFloat(document.getElementById('totalTTC').value) + totalTTC;
				    $("#totalTTC").prop('value', NTotalTTC);
					
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
		// Reset FormData after Posting
		resetData();
	}
	 
	function resetData() {
		$("#quantite").val("");
		$("#remise").val("");
		$("#libelArticle").val("");
		$("#prix").val("");
		$("#tva").val("");
	}
	
})