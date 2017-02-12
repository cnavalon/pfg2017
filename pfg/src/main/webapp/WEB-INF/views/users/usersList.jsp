<%@ include file="/WEB-INF/views/common/include.jsp" %>

<div class="col-md-offset-11 col-sm-1">
	<button id="buttonAddUser" class="btn btn-primary btn-block" onclick="addUser()" title="<spring:message code="icon.add" text="icon.add not found" />">
		<span class="glyphicon glyphicon-plus"></span>
		<span class="glyphicon glyphicon-user"></span>
	</button>
</div>

<table id="tableUsers" class="table table-striped table-bordered" width="100%">
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
			    <td>
			    	<c:forEach var="role" varStatus="status" items="${user.roles}">
			    		<spring:message code="${role.name}" text="${role.name} not found" />
			    		<c:if test="${not status.last}">- </c:if>
			    	</c:forEach>
			    </td>
			    <td class="text-center">
			    	<label id="consultCluster" class="cursorPointer" onclick="consultUser('${user.id}')">
						<i class="glyphicon glyphicon-search"  title="<spring:message code="icon.consult" text="icon.consult not found" />"> </i>
					</label>
			    	<label id="editCluster" class="cursorPointer" onclick="editUser('${user.id}')">
						<i class="glyphicon glyphicon-pencil"  title="<spring:message code="icon.edit" text="icon.edit not found" />"> </i>
					</label>
					<label id="deleteCluster" class="cursorPointer" onclick="deleteUser('${user.id}')">
						<i class="glyphicon glyphicon-trash"  title="<spring:message code="icon.delete" text="icon.delete not found" />"> </i>
					</label>
				</td>
		  	</tr>
	  	</c:forEach>
  	</tbody>
</table>

<script>
$(document).ready(function() {
	
	
	
//     var table = $('#tableUsers').DataTable({
//     	sDom:'<lrtip>',
//         language: {
//             "url": '<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />'
//         },
//         initComplete: function(){
//         	$('#tableUsers tfoot th').each( function () {
//                 var title = $(this).text();
//                 $(this).html( '<input type="text" class="filterTables" placeholder="'+'<spring:message code="table.search" text="table.search not found" /> '+title+'"/>' );
//             } );
        	
//         	$('#tableUsers tfoot tr').insertBefore($('#tableUsers thead tr'));
//         	table.columns().every( function () {
//                 var that = this;
//                 $( 'input', this.footer() ).on('keyup change', function () {
//                     if ( that.search() !== this.value ) {
//                         that.search( this.value ).draw();
//                     }
//                 } );
//             } );
//         }
//     });
	var table = initTable('#tableUsers','<spring:message code="table.search" text="table.search not found" />','<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />', 6);
} );

</script>
