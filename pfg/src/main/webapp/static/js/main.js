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


