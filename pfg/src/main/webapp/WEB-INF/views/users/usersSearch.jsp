<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/users/adm/editUser/" var="urlEditUser" />
<spring:url value="/users/adm/deleteUser/" var="urlDeleteUser" />
<spring:url value="/users/emp/viewUser/" var="urlViewUser" />
<spring:url value="/users/emp/usersSearch?${_csrf.parameterName}=${_csrf.token}" var="urlSearchUsers" />


<div class="row-fluid">
	<h3 class="title"><spring:message code="user.title.search" text="user.title.search not found" /></h3>
</div>

<div class="row-fluid">
	<form id="searchUsersForm" class="form-horizontal" action="" >
		<div id=divSearch class="col-sm-offset-2 col-sm-8">
			<div id="divRoleName" class="form-group">
				<span id="spanRole">
					<spring:message code="user.role" var="userRoleText" text="user.role not found"/>
					<label class="col-sm-2 control-label">${userRoleText}</label>
					<div class="col-sm-3">
						<select class="form-control" id="selectRole">
							<option value="" label=""/>
							<c:forEach items="${lstRoles}" var="role" varStatus="loop">
								<spring:message code="${role.name}" var="roleText" text="${role.name} not found"/>
								<option value="${role.idRole}" label="${roleText}">${roleText}</option>
							</c:forEach>
						</select>
					</div>
				</span>
				
				<span id="spanName">
					<spring:message code="user.name" var="userNameText" text="user.name not found"/>
					<label class="col-sm-3 control-label">${userNameText}</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="inputName" placeholder="${userNameText}"/>
					</div>
				</span>
			</div>
			
			<div id="divSurnames" class="form-group">
				<span id="spanSurname1" >
					<spring:message code="user.surname1" var="userSurname1Text" text="user.surname1 not found"/>
					<label class="col-sm-2 control-label">${userSurname1Text}</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="inputSurname1" placeholder="${userSurname1Text}"/>
					</div>
				</span>
				
				<span id="spanSurname2">
					<spring:message code="user.surname2" var="userSurname2Text" text="user.surname2 not found"/>
					<label class="col-sm-3 control-label">${userSurname2Text}</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="inputSurname2" placeholder="${userSurname2Text}"/>
					</div>
				</span>
			</div>
			
			<div id="divButton" class="form-group text-center">
				<button type="button" class="btn btn-default" id="buttonSearch"><spring:message code="common.search" text="common.search not found"/></button>
			</div>
		</div>
		
	</form>
</div>

<div class="row-fluid" id="divTable" hidden>
	<div class="col-sm-offset-1 col-sm-10">
		<table id="tableUsers" class="stripe hover row-border" width="100%">
			<thead>
		  		<tr>
		    		<th><spring:message code="user.name" text="user.name not found" /></th>
		    		<th><spring:message code="user.surname1" text="user.surname1 not found" /></th>
		    		<th><spring:message code="user.surname2" text="user.surname2 not found" /></th>
		    		<th><spring:message code="user.role" text="user.role not found" /></th>
		    		<th></th>
		  	</thead>
		  	<tfoot>
			  	<tr>
		    		<th><spring:message code="user.name" text="user.name not found" /></th>
		    		<th><spring:message code="user.surname1" text="user.surname1 not found" /></th>
		    		<th><spring:message code="user.surname2" text="user.surname2 not found" /></th>
		    		<th><spring:message code="user.role" text="user.role not found" /></th>
		    		<th></th>
			  	</tr>
		  	</tfoot>
		  	<tbody>
		  	</tbody>
		</table>
	</div>
</div>

<script>
	var user = null;
	var role = '${sessionScope.sessionUserRole.idRole}'
	var table = null;
	var mapRoles = {};
	
	var consultText = '<spring:message code="common.consult" text="common.consult not found" />';
	var editText = '<spring:message code="common.edit" text="common.edit not found" />';
	var deleteText = '<spring:message code="common.delete" text="common.delete not found" />';
	$(document).ready(function() {
		$(document).ajaxStart(function() {blockUI();}).ajaxStop(function() {unblockUI();});
		orderAllOptions();
		
		$("#selectRole option").each(function()
		{
			mapRoles[$(this).val()] = $(this).text();
		});
	} );
	
	$(document).keypress(function(e) {
	    if(e.which == 13) {
	    	search();
			$("#divTable").show();
	    }
	});
	
	$("#buttonSearch").click(function(){
		search();
		$("#divTable").show();
	});
	
	function search(){
		if(table == null){
			table = $("#tableUsers").DataTable({
		    	sDom:'<lrtip>',
		        language: {
		            "url": '<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />'
		        },
		        columnDefs: [ {
		            "orderable": false,
		            "targets": [4]
		        } ],
		     
		        ajax : {
		   			url : '${urlSearchUsers}',
		   			type : "POST",
		   			data : function(d){
		   				d.idRole = $("#selectRole").val();
		   				d.name = $("#inputName").val();
		   				d.surname1 = $("#inputSurname1").val();
		   				d.surname2 = $("#inputSurname2").val();
		   			},
		   			dataSrc : function ( json ) {
		   	     		if(json != null && json.length > 0){
		   		   	    	for (var i = 0; i < json.length; i++) {
		   	     	    		json[i].operations = '<div class="text-center"><label id="viewUser" class="cursorPointer iconTable" onclick="viewUser(' + json[i].id + ',\'' + json[i].idRole + '\')"><i class="glyphicon glyphicon-search" title="'+ consultText + '"> </i></label>';
		   	     	    		if (role === "ROLE_ADM") {
		   	     	    			json[i].operations += '<label id="editUser" class="cursorPointer iconTable" onclick="editUser(\'' + json[i].idUser + '\')"><i class="glyphicon glyphicon-pencil" title="'+ editText + '"> </i></label>';
		   	     	    			json[i].operations += '<label id="deleteUser" class="cursorPointer iconTable" onclick="confirmDeleteUser(' + json[i].id + ',\'' + json[i].idRole + '\')"><i class="glyphicon glyphicon-trash" title="'+ deleteText + '"> </i></label>';
		   	     	    		}
		   	     	    		json[i].operations += '</div>';
		   	     	    		json[i].roleName = mapRoles[json[i].idRole];
		   	     	    	}
		   	     		}
		   	 	      	return json;
		   	 	    }
		   		},
		   	   	columns : [
		            { "data": "name", "width":"25%"},
		       		{ "data": "surname1", "width":"25%" },
		      		{ "data": "surname2", "width":"25%"},
		      		{ "data": "roleName", "width":"15%"},
		      		{ "data": "operations", "width":"10%"}	         		
		  		],
		        initComplete: function(){
		        	 table.columns().every( function () {
		        		 if(this.index() != 4){
		        			 var column = this;
			       			 var that = this;
						     var title = $(column.footer()).text();
						     $(column.footer()).html( '<input type="text" class="filterTables" placeholder="'+title+'"/>' );
						     $( 'input', this.footer() ).on('keyup change', function () {
						    	 if ( that.search() !== this.value ) {
						    		 that.search( this.value ).draw();
						    	 }
						     } );
		        		 }
		       	  	} ); 
		    		$("#tableUsers tfoot tr").insertBefore($("#tableUsers thead tr"));
				}
		    });
		} else {
			reloadTable();
		}
		table.order([1, 'asc']).draw();
	}
	
	function editUser(idUser){
		location.href = "${urlEditUser}" + idUser ;
	}
	
	function confirmDeleteUser(id, idRole){
		confirm('<spring:message code="user.delete.quest" text="user.delete.quest not found" />', deleteUser, null);
		user = {
			id: id,
			idRole : idRole
		}
	}
	
	function deleteUser(){
		$.ajax({
			dataType : "text",
			type:"GET", 
			url : "${urlDeleteUser}" + user.id + "/" + user.idRole,
			success : function(response) {
				alert(response, reloadTable);
			},
			error: function(){
				alert('<spring:message code="user.delete.error" text="user.delete.error not found" />', reloadTable);
			} 
		});			
	}
	
	function reloadTable(){
		table.ajax.reload();
	}

</script>
