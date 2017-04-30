<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/users/emp/viewUser/" var="urlViewUser" />

<div id="dataModal" class="modal fade" role="dialog" aria-labelledby="dataConfirmLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false"> 
	<div class="modal-dialog modal-lg"> 
		<div class="modal-content"> 
	 		<div class="modal-header"> 		        			
	 			<h3 id="dataModalLabel"></h3> 
	 		</div> 
	 		<div id="dataModalBody" class="modal-body">
	 			<div class="container-fluid modal-padding">
	 				<div class="row-fluid">
						<div class="col-sm-offset-1 col-sm-10">
							<table id="tableAbsences" class="stripe hover row-border" width="100%">
								<thead>
							  		<tr>
							  			<th></th>
							    		<th><spring:message code="absences.student" text="absences.student not found" /></th>
							    		<th><spring:message code="absences.absence" text="absences.absence not found" /></th>
						    		</tr>
							  	</thead>
							  	<tbody>
							  		<c:forEach items="${lstStudents}" var="student">
							  			<tr>
							  				<td id="tdId">${student.id}</td>
							  				<td>${student.fullName}</td>
							  				<td class="text-center">
							  					<c:choose>
							  						<c:when test="${lstAbsencesStudens.indexOf(student.id) == -1 }">
							  							<input type="checkbox" id="absenceCheck">
							  						</c:when>
							  						<c:otherwise>
							  							<input type="checkbox" id="absenceCheck" checked>
							  						</c:otherwise>
							  					</c:choose>
							  				</td>
							  			</tr>
							  		</c:forEach>
							  	</tbody>
							</table>
						</div>
					</div>	
	 			</div>
	 		</div>  
	 		<div class="modal-footer"> 
	 			<button class="btn btn-primary" id="dataModalCancel"><spring:message code="common.cancel" text="common.cancel not found" /></button> 
	 			<button class="btn btn-primary" id="dataModalOK"><spring:message code="common.save" text="common.save not found" /></button> 
	 		</div> 
	 	</div> 
 	</div> 
</div>

<script>
$(document).ready(function() {
	$("#dataModalLabel").text(group + " - " + subject + " - " + moment(day).format("DD/MM/YYYY"));
});

var tableAbsences = $('#tableAbsences').DataTable( {
	"language": {
        "url": '<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />'
    },
    "paging":   false,
    "searching": false,
    "info":     false,
    "fixedHeader": {
        header: true,
        footer: false
    },
    "autoWidth": false,
    "columns" : [
        { "visible" : false},
        { "width":"60%"},
        { "width":"40%", "orderable": false }
	],
    "order": [[ 1, "asc" ]]
} );

$("#dataModalOK").click(function(){
	tableAbsences.rows().every( function ( rowIdx, tableLoop, rowLoop ) {
		var abscence = {
			schedule : {id : schedule},
			day : day
		}
		var node = this.node();
		var data = this.data();
		abscence.student = {id : data[0]};
		abscence.active = $(node).find("#absenceCheck").is(':checked');
		lstAbsences.push(abscence);
	});
});

</script>