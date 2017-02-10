<%@ include file="/WEB-INF/views/common/include.jsp" %>

<table id="tableUsers" class="table table-striped table-bordered" width="100%">
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
	var table = initTable('#tableUsers','<spring:message code="table.search" text="table.search not found" />','<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />');
} );

</script>
