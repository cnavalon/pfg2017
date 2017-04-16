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
				  					<i class="glyphicon glyphicon-time"> </i> 
				  				</c:if>
				  				<c:if test="${attendee.status == 'A'}">
				  					<i class="glyphicon glyphicon-ok green"> </i> 
				  				</c:if>
				  				<c:if test="${attendee.status == 'R'}">
				  					<i class="glyphicon glyphicon-remove red"> </i> 
				  				</c:if>
				  				<c:if test="${attendee.status == 'C'}">
				  					<i class="glyphicon glyphicon-remove-circle"> </i> 
				  				</c:if>
				  				<spring:message code="meeting.status.${attendee.status}" text="meeting.status.${attendee.status} not found" /></td>
				  			<td>${attendee.comments}</td>
				  		</tr>
				  		</c:forEach>
				  	</tbody>
				</table>
	 		</div>  
	 		<div class="modal-footer"> 
	 			<a class="btn btn-primary" id="dataModalOK"><spring:message code="common.ok" text="common.ok not found" /></a> 
	 		</div> 
	 	</div> 
 	</div> 
</div>

<script>

var tableAttendees = initTable("#tableAttendees",'<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />',[],[1],[]);

</script>