<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/users/emp/viewUser/" var="urlViewUser" />

<div id="dataModal" class="modal fade" role="dialog" aria-labelledby="dataConfirmLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false"> 
	<div class="modal-dialog modal-xl"> 
		<div class="modal-content"> 
	 		<div class="modal-header"> 		        			
	 			<h3 id="dataModalLabel">${group.course.name} - ${group.letter}</h3> 
	 		</div> 
	 		<div id="dataModalBody" class="modal-body">
	 			<div class="container-fluid modal-padding">
		 			<div class="row">
						<h4><b><spring:message code="group.tutor" text="group.tutor not found"/>:</b> ${group.tutor.fullName}</h4>
		 			</div>
		 			
	 				<div class="row">
						<h4><b><spring:message code="group.schedule" text="group.schedule not found"/></b></h4>
						<div id="divScheduleTable">
							<jsp:include page="/WEB-INF/views/schedule/scheduleGroup.jsp" />
						</div>
		 			</div>
		 			
		 			<div class="row" style="padding-top: 20px;">
						<h4><b><spring:message code="group.students" text="group.students not found"/></b>: ${fn:length(lstStudents)}</h4>
						<div id="divStudentsTable">
							<table id="tableStudents" class="stripe hover row-border compact" width="95%">
								<thead>
							  		<tr>
							    		<th><spring:message code="user.id" text="user.id not found" /></th>
							    		<th><spring:message code="user.name" text="user.name not found" /></th>
							    		<th><spring:message code="user.surname1" text="user.surname1 not found" /></th>
							    		<th><spring:message code="user.surname2" text="user.surname2 not found" /></th>
							    		<th></th>
						    		</tr>
							  	</thead>
							  	<tbody>
							  		<c:forEach items="${lstStudents}" var="student">
								  		<tr>
								  			<td>${student.id}</td>
								  			<td>${student.name}</td>
								  			<td>${student.surname1}</td>
								  			<td>${student.surname2}</td>
								  			<td>
								  				<div class="text-center">
								  					<label id="viewUser" class="cursorPointer iconTable" onclick="redirect('${urlViewUser}${student.idUser}')"><i class="glyphicon glyphicon-info-sign" title='<spring:message code="common.view" text="common.view not found" />'> </i></label>
							  					</div>
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
	 			<a class="btn btn-primary" id="dataModalOK"><spring:message code="common.close" text="common.close not found" /></a> 
	 		</div> 
	 	</div> 
 	</div> 
</div>

<script>
var tableStudent = $('#tableStudents').DataTable( {
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
        { "width":"10%"},
        { "width":"28%"},
        { "width":"28%"},
        { "width":"28%"},
        { "width":"6%", "orderable": false }
	],
    "order": [[ 2, "asc" ]]
} );

</script>