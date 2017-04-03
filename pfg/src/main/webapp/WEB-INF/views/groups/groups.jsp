<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/groups/emp/viewGroup/" var="urlViewGroup" />
<spring:url value="/groups/adm/editGroup/" var="urlEditGroup" />
<spring:url value="/groups/adm/deleteGroup/" var="urlDeleteGroup" />


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
			    		<td>
			    			<div class="text-center">
			    				<label id="viewGroup" class="cursorPointer iconTable" onclick="modal('${urlViewGroup}${group.id}',null)"><i class="glyphicon glyphicon-info-sign" title='<spring:message code="common.view" text="common.view not found" />'></i></label>
			    				 <c:if test="${pageContext.request.isUserInRole('ADM')}">
				    				<label id="editGroup" class="cursorPointer iconTable" onclick="redirect('${urlEditGroup}${group.id}')"><i class="glyphicon glyphicon-pencil" title='<spring:message code="common.edit" text="common.edit not found" />'></i></label>
				    				<label id="viewGroup" class="cursorPointer iconTable" onclick="confirmDeleteGroup('${group.id}')"><i class="glyphicon glyphicon-trash" title='<spring:message code="common.delete" text="common.delete not found" />'></i></label>
			    				</c:if>
		    				</div>
			    		</td>
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
		tableGroups = initTable("#tableGroups",'<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />',[4],[1]);
	} );
	
	function confirmDeleteGroup(group){
		confirm('<spring:message code="group.delete.quest" text="group.delete.quest not found" />', function(){deleteGroup(group)}, null)
	}
	
	function deleteGroup(group){
		$.ajax({
			dataType : "text",
			type:"GET", 
			url : "${urlDeleteGroup}" + group,
			success : function(response) {
				alert(response, reload);
			},
			error: function(){
				alert('<spring:message code="group.delete.error" text="group.delete.error not found" />', reload);
			} 
		});			
	}
	

</script>
