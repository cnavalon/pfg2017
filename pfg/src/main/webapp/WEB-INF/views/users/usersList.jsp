<%@ include file="/WEB-INF/views/common/include.jsp" %>

<table id="tableUsers" class="display">
	<thead>
  		<tr>
    		<th><spring:message code="user.id" text="user.id not found" /></th>
    		<th><spring:message code="user.name" text="user.name not found" /></th>
    		<th><spring:message code="user.surname1" text="user.surname1 not found" /></th>
    		<th><spring:message code="user.surname2" text="user.surname2 not found" /></th>
    		<th><spring:message code="user.email" text="user.email not found" /></th>
    		<th><spring:message code="user.role" text="user.role not found" /></th>
  	</thead>
  	<tfoot>
	  	<tr>
		    <th><spring:message code="user.id" text="user.id not found" /></th>
    		<th><spring:message code="user.name" text="user.name not found" /></th>
    		<th><spring:message code="user.surname1" text="user.surname1 not found" /></th>
    		<th><spring:message code="user.surname2" text="user.surname2 not found" /></th>
    		<th><spring:message code="user.email" text="user.email not found" /></th>
    		<th><spring:message code="user.role" text="user.role not found" /></th>
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
			    <td>${user.telephone1}</td>
		  	</tr>
	  	</c:forEach>
  	</tbody>
</table>

<script>
$(document).ready(function() {
    $('#tableUsers').DataTable();
} );

</script>
