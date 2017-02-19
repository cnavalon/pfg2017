function initTable(id, searchText, dataTableLang, noSearchIndexes, selectFilterIndexes){
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
					     $(column.footer()).html( '<input type="text" class="filterTables" placeholder="'+searchText+' '+title+'"/>' );
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

function confirmModal(heading, question, callback, callbackCancel, okButtonTxt, cancelButtonTxt) {
	//Create modal if it does not exist
	if (!$('#dataConfirmModal').length) {
        $('body').append(
        	'<div id="dataConfirmModal" class="modal fade" role="dialog" aria-labelledby="dataConfirmLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">' +
        		'<div class="modal-dialog">' +
        			'<div class="modal-content">' +
		        		'<div class="modal-header">' +		        			
		        			'<h3 id="dataConfirmLabel">' + heading + '</h3>' +
		        		'</div>' +
		        		'<div id="dataConfirmBody" class="modal-body">' +
		        			question +
		        		'</div>' + 
		        		'<div class="modal-footer">' +
		        			'<a class="btn btn-primary" id="dataCancel">' + cancelButtonTxt + '</a>' +
		        			'<a class="btn btn-primary" id="dataConfirmOK">' + okButtonTxt + '</a>' +
		        		'</div>' +
		        	'</div>' +
		        '</div>' +
        	'</div>');
    } 
	else {
		$("#dataConfirmModal").find('#dataConfirmOK').unbind();
		$("#dataConfirmModal").find('#dataCancel').unbind();
		$("#dataConfirmLabel").html(heading);
		$("#dataConfirmBody").html(question);
		$("#dataConfirmCancel").html(cancelButtonTxt);
		$("#dataConfirmOK").html(okButtonTxt);
	}
	
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
}

/**
 * Function to show a alert dialog
 * 
 * @param heading
 * @param question
 * @param okButtonTxt
 * @param callback : can be null
 */
function alertModal(heading, question, callback, okButtonTxt) {
	//Create modal if it does not exist
	if (!$('#dataAlertModal').length) {
        $('body').append(
        	'<div id="dataAlertModal" class="modal fade" role="dialog" aria-labelledby="dataConfirmLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">' +
        		'<div class="modal-dialog">' +
        			'<div class="modal-content">' +
		        		'<div class="modal-header">' +
		        			'<h3 id="dataAlertLabel">' + heading + '</h3>' +
		        		'</div>' +
		        		'<div id="dataAlertBody" class="modal-body">' +
		        			question +
		        		'</div>' + 
		        		'<div class="modal-footer">' +
		        			'<a class="btn btn-primary" id="dataAlertOK">' + okButtonTxt + '</a>' +
		        		'</div>' +
		        	'</div>' +
		        '</div>' +
        	'</div>');
    } 
	else {
		$("#dataAlertModal").find('#dataConfirmOK').unbind();
		$("#dataAlertLabel").html(heading);
		$("#dataAlertBody").html(question);
		$("#dataAlertOK").html(okButtonTxt);
	}
	
	$("#dataAlertModal").find('#dataAlertOK').click(function(event) {
		if(callback != null){
			callback();
		}
		$('#dataAlertModal').modal('hide');
	});
	
	//Show modal
    $('#dataAlertModal').modal({show:true});
    return false;     
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