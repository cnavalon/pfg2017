<%@ include file="/WEB-INF/views/common/include.jsp" %>

<div id="dataModal" class="modal fade" role="dialog" aria-labelledby="dataConfirmLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false"> 
	<div class="modal-dialog modal-lg"> 
		<div class="modal-content"> 
	 		<div class="modal-header"> 		        			
	 			<h3 id="dataModalLabel">${group.course.name} - ${group.letter}</h3> 
	 		</div> 
	 		<div id="dataModalBody" class="modal-body">
	 			<div class="container-fluid modal-padding">
		 			<div class="row">
						<spring:message code="group.tutor" var="tutorIdText" text="group.tutor not found"/>
						<label class="col-sm-2 ">${tutorIdText}:</label>
						<div class="col-sm-10">${group.tutor.name} ${group.tutor.surname1} ${group.tutor.surname1}</div>  
		 			</div>
		 			
		 			<div class="row text-center">
		 				
		 			</div>
		 			
	 				<div class="row">
		 				<spring:message code="group.schedule" var="groupScheduleText" text="group.schedule not found"/>
						<label class="col-sm-2">${groupScheduleText}:</label>
		 			</div>
		 			
		 			<div class="row">
						<div id="divStudentsTitle">
							<spring:message code="group.students" var="StudentsText" text="group.students not found"/>
							<label class="col-sm-2">${StudentsText}:</label>
							<div class="col-sm-1">${fn:length(lstStudents)}</div>  
						</div>
						<div id="divStudentsTable">
							<table id="tableStudents" class="stripe hover row-border compact" width="95%">
								<thead>
							  		<tr>
							    		<th><spring:message code="user.id" text="user.id not found" /></th>
							    		<th><spring:message code="user.name" text="user.name not found" /></th>
							    		<th><spring:message code="user.surname1" text="user.surname1 not found" /></th>
							    		<th><spring:message code="user.surname2" text="user.surname2 not found" /></th>
						    		</tr>
							  	</thead>
							  	<tfoot>
<!-- 								  	<tr> -->
<%-- 								  		<th><spring:message code="user.id" text="user.id not found" /></th> --%>
<%-- 							    		<th><spring:message code="user.name" text="user.name not found" /></th> --%>
<%-- 							    		<th><spring:message code="user.surname1" text="user.surname1 not found" /></th> --%>
<%-- 							    		<th><spring:message code="user.surname2" text="user.surname2 not found" /></th> --%>
<!-- 								  	</tr> -->
							  	</tfoot>
							  		<c:forEach items="${lstStudents}" var="student">
								  		<tr>
								  			<td>${student.id}</td>
								  			<td>${student.name}</td>
								  			<td>${student.surname1}</td>
								  			<td>${student.surname2}</td>
								  		</tr>
							  		</c:forEach>
							  	<tbody>
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

$('#tableStudents').DataTable( {
    "paging":   false,
    "searching": false,
    "info":     false,
    "order": [[ 2, "asc" ]]
} );


</script>