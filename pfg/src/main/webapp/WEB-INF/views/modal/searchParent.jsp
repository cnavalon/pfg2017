<%@ include file="/WEB-INF/views/common/include.jsp" %>

<div id="dataModal" class="modal fade" role="dialog" aria-labelledby="dataConfirmLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false"> 
	<div class="modal-dialog"> 
		<div class="modal-content"> 
	 		<div class="modal-header"> 		        			
	 			<h3 id="dataModalLabel"><spring:message code="parent.search.title" text="parent.search.title not found"/></h3> 
	 		</div> 
	 		<div id="dataModalBody" class="modal-body">
	 		<table id="tableParents" class="stripe hover row-border" width="100%">
				<thead>
			  		<tr>
			    		<th><spring:message code="user.id" text="user.id not found" /></th>
			    		<th><spring:message code="user.username" text="user.username not found" /></th>
			    		<th><spring:message code="user.name" text="user.name not found" /></th>
			    		<th><spring:message code="user.surname1" text="user.surname1 not found" /></th>
			    		<th><spring:message code="user.surname2" text="user.surname2 not found" /></th>
			  	</thead>
			  	<tfoot>
				  	<tr>
			    		<th><spring:message code="user.id" text="user.id not found" /></th>
			    		<th><spring:message code="user.username" text="user.username not found" /></th>
			    		<th><spring:message code="user.name" text="user.name not found" /></th>
			    		<th><spring:message code="user.surname1" text="user.surname1 not found" /></th>
			    		<th><spring:message code="user.surname2" text="user.surname2 not found" /></th>
				  	</tr>
			  	</tfoot>
			  	<tbody class="cursorPointer">
			  		<c:forEach items="${lstParents}" var="parent">
			  		<tr>
			  			<td>${parent.id}</td>
			  			<td>${parent.idUser}</td>
			  			<td>${parent.name}</td>
			  			<td>${parent.surname1}</td>
			  			<td>${parent.surname2}</td>
			  		</tr>
			  		</c:forEach>
			  	</tbody>
			</table>
	 		
	 		
	 		
	 		</div>  
	 		<div class="modal-footer"> 
	 			<a class="btn btn-primary" id="dataModalCancel"><spring:message code="common.cancel" text="common.cancel not found" /></a> 
	 			<a class="btn btn-primary" id="dataModalOK"><spring:message code="common.ok" text="common.ok not found" /></a> 
	 		</div> 
	 	</div> 
 	</div> 
</div>

<script>

var table = initTable("#tableParents",'<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />',[],[]);

$('#tableParents tbody').on( 'click', 'tr', function () {
    if ( $(this).hasClass('selected') ) {
        $(this).removeClass('selected');
        clearParent();
    }
    else {
        table.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');
        setParent(table.row('.selected').data());
    }
} );

function clearParent(){
	parent = null;
}

function setParent(data){
	parent = {};
	parent.id = data[0];
	parent.idUser = data[1];
	parent.name = data[2];
	parent.surname1 = data[3];
	parent.surname2 = data[4];
}
</script>