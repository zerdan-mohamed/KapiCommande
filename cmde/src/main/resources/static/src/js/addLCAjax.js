$(document).ready(function() {
	
	
	// *** show add command line ***
	$('#showCLadd').on('click', function(){
		$("#addCmdeLine").css("display", "block");
	});
	
	// *** show edit command line ***
	/*
	$('.editLCmde').on('click', function(){
		$("#editCmdeLine").css("display", "block");		
		var idLC = $("#idLCajax").val();
		alert("ckvcxcx")
		// $("#editquantite").val(idLC);
		
	});
	*/
	
	
	/*  
	 * *************************************************************************** *
	 **************** select commande line for updated  : Ajax POST ****************
	 * *************************************************************************** *
	*/
	$('.editLCmde').on('click', function(event){
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		
		// ** Select ligne command ** 
		SelectOArtPost();
	});
	
	function SelectOArtPost() {
		var formData = {
			idLC : $("#idLCajax").val()
		}

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "api/article/selectLCommand",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if (result.status == "LCommandS") {
					// alert("Ligne cmde selected")

					$("#editCmdeLine").css("display", "block");
					
					$("#editlibelArticle").val(result.data.article["libelleArticle"]);
					$("#editprix").val(result.data.article["prixHT"]);
					$("#edittva").val(result.data.article["tva"]);
					$("#editquantite").val(result.data["quantite"]);
					$("#editremise").val(result.data["remise"]);
					
					
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
	 * *********************************************************************************** *
	 ******************* add commande line in view command : Ajax POST *********************
	 * *********************************************************************************** *
	*/
	$("#addlCmdeView").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxNArticlePost();
	});

	function ajaxNArticlePost() {
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
			url : "api/article/saveLCmdeV",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {				
				if (result.status == "CommandeV") { 
					alert("Ligne de commande ajouté")
					$("#addCmdeLine").css("display", "none");
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
	
	
	// Vider input command line 
	function resetData() {
		$("#quantite").val("");
		$("#remise").val("");
		$("#libelArticle").val("");
		$("#prix").val("");
		$("#tva").val("");
	}
	
	
	
})