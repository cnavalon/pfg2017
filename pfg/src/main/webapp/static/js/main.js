function initTable(id, searchText, dataTableLang){
	var table = $(id).DataTable({
    	sDom:'<lrtip>',
        language: {
            "url": dataTableLang
        },
        initComplete: function(){
        	$(id + ' tfoot th').each( function () {
                var title = $(this).text();
                $(this).html( '<input type="text" class="filterTables" placeholder="'+searchText+' '+title+'"/>' );
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

