function initTable(id, searchText, dataTableLang, lastIndex){
	var table = $(id).DataTable({
    	sDom:'<lrtip>',
        language: {
            "url": dataTableLang
        },
        columnDefs: [ {
            "orderable": false,
            "targets": lastIndex
        } ],
        initComplete: function(){
        	$(id + ' tfoot th').each( function (index) {
        		if(index < lastIndex){
        			var title = $(this).text();
        			$(this).html( '<input type="text" class="filterTables" placeholder="'+searchText+' '+title+'"/>' );
        		}
            } );
        	
        	$(id + ' tfoot tr').insertBefore($(id + ' thead tr'));
        	table.columns().every( function () {
                var that = this;
                $( 'input', this.footer() ).on('keyup change', function () {
                    if ( that.search() !== this.value ) {
                        that.search( this.value ).draw();
                    }
                } );
            } );
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
        	'<div id="dataConfirmModal" class="modal fade" role="dialog" aria-labelledby="dataConfirmLabel" aria-hidden="true">' +
        		'<div class="modal-dialog">' +
        			'<div class="modal-content">' +
		        		'<div class="modal-header">' +
		        			'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>' +
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
        	'<div id="dataAlertModal" class="modal fade" role="dialog" aria-labelledby="dataConfirmLabel" aria-hidden="true">' +
        		'<div class="modal-dialog">' +
        			'<div class="modal-content">' +
		        		'<div class="modal-header">' +
		        			'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>' +
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