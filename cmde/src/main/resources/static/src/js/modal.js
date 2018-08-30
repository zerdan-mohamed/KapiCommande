
$(document).ready(function() {
	
	// *** Delete client confirmation modal ***
	$('table .delBtn').on('click', function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('#delModal #delRef').attr('href', href);
		$('#delModal').modal();
	});
	
	
	// *** select client ***	
	$("#selectClient").change(function() {  
		$("#infoClient #createCmde").prop('disabled', false);
		
		var idClient = $("#selectClient").val();
	    $("#idClient").val(idClient);
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
