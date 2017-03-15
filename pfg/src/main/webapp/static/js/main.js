var csrf = null;

/**
 * Inicia una tabla
 * @param id nombre de la tabla
 * @param dataTableLang idioma de la tabla
 * @param noSearchIndexes array de columnas sin filtros ni ordenacion
 * @param selectFilterIndexes array de columnas con filtros por select
 * @returns la tabla
 */
function initTable(id, dataTableLang, noSearchIndexes, selectFilterIndexes){
	var table = $(id).DataTable({
    	sDom:'<lrtip>',
        language: {
            "url": dataTableLang
        },
        columnDefs: [ {
            "orderable": false,
            "targets": noSearchIndexes
        } ],
        initComplete: function(){
        	 table.columns().every( function () {
        		 if(noSearchIndexes.indexOf(this.index()) == -1){
        			 var column = this;
		       		 if (selectFilterIndexes.indexOf(this.index()) == -1) {
		       			 var that = this;
					     var title = $(column.footer()).text();
					     $(column.footer()).html( '<input type="text" class="filterTables" placeholder="'+title+'"/>' );
					     $( 'input', this.footer() ).on('keyup change', function () {
					    	 if ( that.search() !== this.value ) {
					    		 that.search( this.value ).draw();
					    	 }
					     } );
		       		 }
		       		 else {
		       	  		
		       	  		var select = $('<select><option value=""></option></select>')
		                   .appendTo( $(column.footer()).empty() )
		                   .on( 'change', function () {
		                       var val = $.fn.dataTable.util.escapeRegex(
		                           $(this).val()
		                       );
		
		                       column
		                           .search( val ? '^'+val+'$' : '', true, false )
		                           .draw();
		                   } );
		
		               	column.data().unique().sort().each( function ( d, j ) {
		                  		select.append( '<option value="'+d+'">'+d+'</option>' )
		               	} );
		       		 }
        		 }
       	  } ); 
        	
    	 $(id + ' tfoot tr').insertBefore($(id + ' thead tr'));
        	
        	
//        	$(id + ' tfoot th').each( function (index) {
//        		if(noSearchIndexes.indexOf(index) != -1){
//        			var title = $(this).text();
//        			$(this).html( '<input type="text" class="filterTables" placeholder="'+searchText+' '+title+'"/>' );
//        		}
//            } );
//        	
//        	table.columns().every( function () {
//                var that = this;
//                $( 'input', this.footer() ).on('keyup change', function () {
//                    if ( that.search() !== this.value ) {
//                        that.search( this.value ).draw();
//                    }
//                } );
//            } );
        	
        }
    });
	return table;
}

function selectLink(element){
	$("#listMenu").find('li').each(function(){
		$(this).removeClass('active');
	});
	$(element).addClass('active');
}

function blockUI() {
	$.blockUI({ message: '<img src="static/images/ajax-loader.gif" />' ,
		centerY: false,
	    centerX: false,
		css: { position: 'fixed', left: '50%', top: '50%', width: '4%', border:'0px solid #FFFFFF', cursor:'wait', backgroundColor: ''},
	  	overlayCSS:  { opacity:0.5, cursor:'wait'} 
	}); 
}

function unblockUI() {
	$.unblockUI();
}

function confirm(text, callback, callbackCancel) {
	if ($('#confirmModal').length) {
		$('#confirmModal').empty();
	}else{
		$('body').append('<div id="confirmModal"></div>');
	}
	$('#confirmModal').load("modal/confirm" + csrf,{text:text}, function(){
		$("#dataConfirmModal").find('#dataCancel').click(function(event) {
			if(callbackCancel != null){
				callbackCancel();
			}
			$('#dataConfirmModal').modal('hide');
			return true;
		});
		
		$("#dataConfirmModal").find('#dataConfirmOK').click(function(event) {
			callback();
			$('#dataConfirmModal').modal('hide');
			return true;
		});
		
		//Show modal
	    $('#dataConfirmModal').modal({show:true});
	    return false;  
    }); 
}

function modal(url, callback, callbackCancel) {
	if ($('#modal').length) {
		$('#modal').empty();
	}else{
		$('body').append('<div id="modal"></div>');
	}
	$('#modal').load(url + csrf, function(){
		$("#dataModal").find('#dataModalCancel').click(function(event) {
			if(callbackCancel != null){
				callbackCancel();
			}
			$('#dataModal').modal('hide');
			return true;
		});
		
		$("#dataModal").find('#dataModalOK').click(function(event) {
			callback();
			$('#dataModal').modal('hide');
			return true;
		});
		
		//Show modal
	    $('#dataModal').modal({show:true});
	    return false;  
    }); 
}

function alert(text, callback) {
	if ($('#alertModal').length) {
		$('#alertModal').empty();
	}else{
		$('body').append('<div id="alertModal"></div>');
	}
    $('#alertModal').load("modal/alert" + csrf,{text:text}, function(){
    	$("#dataAlertModal").find('#dataAlertOK').click(function(event) {
    		if(callback != null){
    			callback();
    		}
    		$('#dataAlertModal').modal('hide');
    	});
    	
    	//Show modal
        $('#dataAlertModal').modal({show:true});
        return false;     
    });        	
}

function reload(){
	location.reload();
}

function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    
    return true;
}

function isLetterKey(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 65 || (charCode > 90 && (charCode < 97 || charCode > 122))))
        return false;
    
    return true;
}

function isUnderscoreKey(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    return charCode === 95;
}

function isIDKey(evt){
	return isNumberKey(evt) || isLetterKey(evt) || isUnderscoreKey(evt);
}

function orderAllOptions(){
	$("select").each(function(){
		orderOptions("#" + $(this).attr('id'));
	});
}

function orderOptions(idSelect){
	var my_options = $(idSelect + " option");
	var selected = $(idSelect).val();

	my_options.sort(function(a,b) {
	    if (a.text > b.text) return 1;
	    if (a.text < b.text) return -1;
	    return 0
	})

	$(idSelect).empty().append( my_options );
	$(idSelect).val(selected);
}

function post(params) {
	var form = document.createElement("form");
	form.setAttribute("method", "post");

	for ( var key in params) {
		if (params.hasOwnProperty(key)) {
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", key);
			hiddenField.setAttribute("value", params[key]);

			form.appendChild(hiddenField);
		}
	}

	document.body.appendChild(form);
	return form;
}