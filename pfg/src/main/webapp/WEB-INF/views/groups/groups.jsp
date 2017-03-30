<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/users/adm/editUser/" var="urlEditUser" />


<div class="row-fluid">
	<h3 class="title"><spring:message code="group.title.list" text="group.title.list not found" /></h3>
</div>

<div class="row-fluid" id="divTable">
	<div class="col-sm-offset-1 col-sm-10">
		<table id="tableGroups" class="stripe hover row-border" width="100%">
			<thead>
		  		<tr>
		    		<th><spring:message code="group.course" text="group.course not found" /></th>
		    		<th><spring:message code="group.letter" text="group.letter not found" /></th>
		    		<th><spring:message code="group.tutor" text="group.tutor not found" /></th>
		    		<th><spring:message code="group.students" text="group.students not found" /></th>
		    		<th></th>
		  	</thead>
		  	<tfoot>
			  	<tr>
			  		<th><spring:message code="group.course" text="group.course not found" /></th>
		    		<th><spring:message code="group.letter" text="group.letter not found" /></th>
		    		<th><spring:message code="group.tutor" text="group.tutor not found" /></th>
		    		<th><spring:message code="group.students" text="group.students not found" /></th>
		    		<th></th>
			  	</tr>
		  	</tfoot>
		  	<tbody>
		  		<c:forEach items="${lstGroups}" var="group">
			  		<tr>
				  		<td>${group.course.name}</td>
				  		<c:choose>
				  			<c:when test="${empty group.letter}"><td>-</td></c:when>
				  			<c:otherwise><td>${group.letter}</td></c:otherwise>
				  		</c:choose>
			    		<td>${group.tutor.name} ${group.tutor.surname1} ${group.tutor.surname2}</td>
			    		<td class="text-center">${mapGroupCount[group.id]}</td>
			    		<td></td>
				  	</tr>
		  		</c:forEach>
		  	</tbody>
		</table>
	</div>
</div>

<script>
	var tableGroups = null;
	$(document).ready(function() {
		$(document).ajaxStart(function() {blockUI();}).ajaxStop(function() {unblockUI();});
		tableGroups = initTable("#tableGroups",'<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />',[4],[0,1]);
	} );
	

</script>
