<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/users/adm/newUser" var="urlNewUser" />
<spring:url value="/users/adm/editUser/" var="urlEditUser" />
<spring:url value="/users/adm/deleteUser/" var="urlDeleteUser" />

<div class="row-fluid">
	<h3 class="title"><spring:message code="user.title.list" text="user.title.list not found" /></h3>
</div>

<div class="row-fluid">
	<div class="text-right">
		<button id="buttonAddUser" class="btn btn-default" onclick="addUser()" title="<spring:message code="common.add" text="common.add not found" />">
			<span class="glyphicon glyphicon-plus"></span>
			<span class="glyphicon glyphicon-user"></span>
		</button>
	</div>
</div>

<div class="row-fluid">
	<table id="tableUsers" class="display" width="100%">
		<thead>
	  		<tr>
	    		<th><spring:message code="user.id" text="user.id not found" /></th>
	    		<th><spring:message code="user.name" text="user.name not found" /></th>
	    		<th><spring:message code="user.surname1" text="user.surname1 not found" /></th>
	    		<th><spring:message code="user.surname2" text="user.surname2 not found" /></th>
	    		<th><spring:message code="user.email" text="user.email not found" /></th>
	    		<th><spring:message code="user.role" text="user.role not found" /></th>
	    		<th></th>
	  	</thead>
	  	<tfoot>
		  	<tr>
			    <th><spring:message code="user.id" text="user.id not found" /></th>
	    		<th><spring:message code="user.name" text="user.name not found" /></th>
	    		<th><spring:message code="user.surname1" text="user.surname1 not found" /></th>
	    		<th><spring:message code="user.surname2" text="user.surname2 not found" /></th>
	    		<th><spring:message code="user.email" text="user.email not found" /></th>
	    		<th><spring:message code="user.role" text="user.role not found" /></th>
	    		<th></th>
		  	</tr>
	  	</tfoot>
	  	<tbody>
	  		<c:forEach var="user" items="${lstUsers}">
		  		<tr>
				    <td>${user.id}</td>
				    <td>${user.name}</td>
				    <td>${user.surname1}</td>
				    <td>${user.surname2}</td>
				    <td>${user.email}</td>
				    <td><spring:message code="${role.name}" text="${role.name} not found" /></td>
				    <td class="text-center">
				    	<label id="consultCluster" class="cursorPointer" onclick="consultUser('${user.id}')">
							<i class="glyphicon glyphicon-search"  title="<spring:message code="common.consult" text="common.consult not found" />"> </i>
						</label>
				    	<label id="editCluster" class="cursorPointer" onclick="editUser('${user.id}')">
							<i class="glyphicon glyphicon-pencil"  title="<spring:message code="common.edit" text="common.edit not found" />"> </i>
						</label>
						<label id="deleteCluster" class="cursorPointer" onclick="confirmDeleteUser('${user.id}')">
							<i class="glyphicon glyphicon-trash"  title="<spring:message code="common.delete" text="common.delete not found" />"> </i>
						</label>
					</td>
			  	</tr>
		  	</c:forEach>
	  	</tbody>
	</table>
</div>

<script>
var user = null;

$(document).ready(function() {
	$(document).ajaxStart(function() {blockUI();}).ajaxStop(function() {unblockUI();});
	var table = initTable('#tableUsers','<spring:message code="table.search" text="table.search not found" />','<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />', 6);
} );

function addUser(){
	location.href = "${urlNewUser}"
}

function editUser(userId){
	location.href = "${urlNewUser}" + urlEditUser;
}

function confirmDeleteUser(userId){
	confirm(userId, '<spring:message code="user.delete.quest" text="user.delete.quest not found" />', deleteUser, null);
	user = userId;
}

function deleteUser(){
	$.ajax({
		dataType : "text",
		type:"GET", 
		url : "${urlDeleteUser}" + user,
		success : function(response) {
			alert(user, response, reload);
		},
		error: function(){
			alert(user, '<spring:message code="user.delete.error" text="user.delete.error not found" />', reload);
		}
	});			
}

</script>
