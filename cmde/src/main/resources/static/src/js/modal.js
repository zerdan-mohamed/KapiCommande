$(document).ready(function() {
	
	// *** Delete client confirmation modal ***
	$('table .delBtn').on('click', function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('#delModal #delRef').attr('href', href);
		$('#delModal').modal();
	});
	
	
	// *** Delete commande confirmation modal ***
	$('table .delCmdeBtn').on('click', function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('#delCmdeModal #delCmdeRef').attr('href', href);
		$('#delCmdeModal').modal();
	});
	
	// *** Delete ligne commande confirmation modal ***
	$('table .delLCmdeBtn').on('click', function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('#delLCmdeModal #delLCmdeRef').attr('href', href);
		$('#delLCmdeModal').modal();
	});
	
	// *** select client commande ***	
	$("#selectClient").change(function() {  
		$("#infoClient #createCmde").prop('disabled', false);
		
		var idClient = $("#selectClient").val();
	    $("#idClient").val(idClient);
	});

	// *** select status commande ***	
	$("#statusSelect").change(function() {  
		var statusCmmde = $("#statusSelect").val();
	    $("#statusCmmde").val(statusCmmde);
	});
	
	
	/*
	// *** Create command ***
	$("#infoClient #createCmde").click(function(){
		//$("#infoClient #createCmde").hide();
		$("#addArticle").show();
    });
	*/
	
	// *** select article ***
	/*
	$("table #selectArticle").change(function() {  
		$('#artSelected').show();
		
		var price = $("#selectArticle").val();
		$("#prix").val(price);
		
		var id = $("#selectArticle option:selected").text();
		$("#libelArticle").val(id);
	});
	*/
		
});
