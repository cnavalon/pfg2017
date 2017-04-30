<%@ include file="/WEB-INF/views/common/include.jsp" %>

<div id="dataModal" class="modal fade" role="dialog" aria-labelledby="dataConfirmLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false"> 
	<div class="modal-dialog modal-lg"> 
		<div class="modal-content"> 
	 		<div class="modal-header"> 		        			
	 			<h3 id="dataModalLabel"><spring:message code="meeting.attendees" text="meeting.attendees not found"/></h3> 
	 		</div> 
	 		<div id="dataModalBody" class="modal-body">
		 		<table id="tableAttendees" class="stripe hover row-border" width="90%">
					<thead>
				  		<tr>
				    		<th><spring:message code="user.username" text="user.username not found" /></th>
				    		<th><spring:message code="meeting.state" text="meeting.state not found" /></th>
				    		<th><spring:message code="meeting.comments" text="meeting.comments not found" /></th>
			    		</tr>
				  	</thead>
				  	<tfoot>
					  	<tr>
				    		<th><spring:message code="user.username" text="user.username not found" /></th>
				    		<th><spring:message code="meeting.state" text="meeting.state not found" /></th>
				    		<th><spring:message code="meeting.comments" text="meeting.comments not found" /></th>
			    		</tr>
				  	</tfoot>
				  	<tbody >
				  		<c:forEach items="${lstAttendee}" var="attendee">
				  		<tr>
				  			<td>${attendee.person.fullName}</td>
				  			<td>
				  				<c:if test="${attendee.status == 'P'}">
				  					<i class='glyphicon glyphicon-time'> </i> 
				  				</c:if>
				  				<c:if test="${attendee.status == 'A'}">
				  					<i class='glyphicon glyphicon-ok green'> </i> 
				  				</c:if>
				  				<c:if test="${attendee.status == 'R'}">
				  					<i class='glyphicon glyphicon-remove red'> </i> 
				  				</c:if>
				  				<c:if test="${attendee.status == 'C'}">
				  					<i class='glyphicon glyphicon-remove-circle gray'> </i> 
				  				</c:if>
				  				<spring:message code="meeting.status.${attendee.status}" text="meeting.status.${attendee.status} not found" /></td>
				  			<td>${attendee.comments}</td>
				  		</tr>
				  		</c:forEach>
				  	</tbody>
				</table>
	 		</div>  
	 		<div class="modal-footer"> 
	 			<button class="btn btn-primary" id="dataModalOK"><spring:message code="common.ok" text="common.ok not found" /></button> 
	 		</div> 
	 	</div> 
 	</div> 
</div>

<script>

var tableAttendees = $("#tableAttendees").DataTable({
	sDom:'<lrtip>',
    language: {
    	"url": '<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />'
	},
	columns : [
    	{"width":"30"},
        {"width":"15%"},
        {"width":"55%"}
	],
	"autoWidth": false,
    "order": [[ 1, "asc" ]],
    initComplete: function(){
    	tableAttendees.columns().every( function () {
  			var column = this;
   		 	if(this.index() != 1){
      			var that = this;
			    var title = $(column.footer()).text();
			    $(column.footer()).html( '<input type="text" class="filterTables" placeholder="'+title+'"/>' );
			    $( 'input', this.footer() ).on('keyup change', function () {
			    	if ( that.search() !== this.value ) {
			    		that.search( this.value ).draw();
			    	}
			    });
   		 	}
   		 	if(this.index() == 1){
       			var select = $('<select style="width: 100%;"><option value=""></option></select>')
                	.appendTo( $(column.footer()).empty() )
                	.on( 'change', function () {
                    	var val = $.fn.dataTable.util.escapeRegex(
                        	$(this).val()
                    	);

                    	column.search( val ? val+'$' : '', true, false ).draw();
                	});

       			select.append( '<option value="'+statusP_Text+'">'+statusP_Text+'</option>' );
           		select.append( '<option value="'+statusA_Text+'">'+statusA_Text+'</option>' );
           		select.append( '<option value="'+statusR_Text+'">'+statusR_Text+'</option>' );
           		select.append( '<option value="'+statusC_Text+'">'+statusC_Text+'</option>' );
       		 }
  	  	});
    	
		$("#tableAttendees tfoot tr").insertBefore($("#tableAttendees thead tr"));
	}
});

</script>