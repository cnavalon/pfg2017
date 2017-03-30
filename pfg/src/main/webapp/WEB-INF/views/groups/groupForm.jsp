<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/groups/adm/save?${_csrf.parameterName}=${_csrf.token}" var="urlSaveGroup" />
<spring:url value="/groups/adm/groups" var="urlGroupsList" />
<spring:url value="/groups/adm/searchStudents?${_csrf.parameterName}=${_csrf.token}" var="urlSearchStudents" />

<div class="row-fluid">
	<h3 class="title">${title}</h3>
</div>

<div class="row-fluid">
	
	<div class="col-sm-12">
		<form:form id="groupForm" class="form-horizontal" method="post" modelAttribute="group" enctype="multipart/form-data" action="javascript:;">
			<fieldset id="filedsetForm">
				<div id="divRow0" class="form-group">
					<div id="divCourse">
						<spring:message code="group.course" var="courseIdText" text="group.course not found"/>
						<label class="col-sm-2 control-label">${courseIdText}*</label>
						<div class="col-sm-3">
							<form:select class="form-control" id="selectCourse" path="course.idCourse">
	      						<form:option value="" label=""/>
	      						<form:options items="${lstCourses}" itemValue="idCourse" itemLabel="name"/>
	 						</form:select>
						</div>
					</div>
					
					<div id="divLetter">
						<spring:message code="group.letter" var="groupLetterText" text="group.letter not found"/>
						<label class="col-sm-2 control-label">${groupLetterText}</label>
						<div class="col-sm-3">
							<form:input path="letter" type="text" class="form-control" id="inputLetter" placeholder="${groupLetterText}" onkeypress="return isLetterKey(event)" maxlength="1"/>
						</div>
					</div>
					
				</div>
				
				<div id="divRow1" class="form-group">
				
					<div id="divTutor">
						<spring:message code="group.tutor" var="tutorIdText" text="group.tutor not found"/>
						<label class="col-sm-2 control-label">${tutorIdText}*</label>
						<div class="col-sm-3">
							<form:select path="tutor.id" class="form-control" id="selectTutor">
	      						<form:option value="" label=""/>
	      						<form:options items="${lstTeachers}" itemValue="id" itemLabel="fullName"/>
	 						</form:select>
						</div>
					</div>
						
					<div id="divSchedule">
						<spring:message code="group.schedule" var="groupScheduleText" text="group.schedule not found"/>
						<label class="col-sm-2 control-label">${groupScheduleText}</label>
						<div class="col-sm-3">
							<label id="labelSelectFile" class="form-control cursorPointer" for="scheduleFile">
					    		<span id="iconSearchPar1" class="add-on"><span class="glyphicon glyphicon-search"></span>
					    		<span id="textFile">${group.scheduleFile}</span>
					    	</label>
			    			<input class="selectFileInput" id="scheduleFile" type="file" name="scheduleFile" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" >
						</div>
					</div>
				</div>
			</fieldset>
			
			<div id="divRow2" class="form-group">
				<div id="divStudentsTitle">
					<spring:message code="group.students" var="StudentsText" text="group.students not found"/>
					<label class="col-sm-2 control-label">${StudentsText}</label>
				</div>
				<div id="divStudentsTable" class="col-sm-offset-1 col-sm-10" hidden>
					<table id="tableStudents" class="stripe hover row-border" width="100%">
						<thead>
					  		<tr>
					    		<th class="text-center"><input type="checkbox" onclick="selectAll(this)"></th>
					    		<th><spring:message code="user.id" text="user.id not found" /></th>
					    		<th><spring:message code="user.name" text="user.name not found" /></th>
					    		<th><spring:message code="user.surname1" text="user.surname1 not found" /></th>
					    		<th><spring:message code="user.surname2" text="user.surname2 not found" /></th>
				    		</tr>
					  	</thead>
					  	<tfoot>
						  	<tr>
					    		<th></th>
						  		<th><spring:message code="user.id" text="user.id not found" /></th>
					    		<th><spring:message code="user.name" text="user.name not found" /></th>
					    		<th><spring:message code="user.surname1" text="user.surname1 not found" /></th>
					    		<th><spring:message code="user.surname2" text="user.surname2 not found" /></th>
						  	</tr>
					  	</tfoot>
					  	<tbody>
					  	</tbody>
					</table>
				</div>
			</div>
			
			<div class="form-group ">
		    	<div class="col-sm-offset-4 col-sm-6 text-right">
		    		<div class="col-sm-4"></div>
	    			<div class="col-sm-4">
			      		<button id ="buttonAdd" type="button" class="btn btn-block btn-default"><spring:message code="common.save" text="common.save not found"/></button>
		    		</div>
		    		<div class="col-sm-4">
			      		<button id ="buttonCancel" type="button" class="btn btn-block btn-default" ><spring:message code="common.cancel" text="common.cancel not found"/></button>
		    		</div>
	      		</div>
      		</div>
		</form:form>
	</div>
</div>

<script>
	
	var tableStudents = null;
	var selectedStudents = {};
	
	$(document).ready(function() {
		$(document).ajaxStart(function() {blockUI();}).ajaxStop(function() {unblockUI();});
		orderOptions("#selectTutor");
	} );
	
	$('#buttonCancel').click(function(event){
		confirm('<spring:message code="common.loseChanges" text="common.loseChanges not found"/>', redirect("${urlGroupsList}"), null);
	});
	
	$("#selectCourse").change(function(){
		if($("#selectCourse").val().trim() != ""){
			$("#divCourse").removeClass("has-error");
			searchStudents();
		} else {
			cleanStudents();
		}
	});
	
	$("#inputLetter").change(function(){
		if($("#inputLetter").val() != ""){
			$("#inputLetter").val($("#inputLetter").val().toUpperCase());
		}
		if($("#selectCourse").val().trim() != ""){
			searchStudents();
		}
	})
	
	$("#selectTutor").change(function(){
		if($("#selectTutor").val().trim() != ""){
			$("#divTutor").removeClass("has-error");
		}
	});

	function validForm(){
		var lstValidator = [];
		if($("#selectCourse").val().trim() === ""){
			lstValidator.push("${courseIdText}");
			$("#divCourse").addClass("has-error");	
		}
		if($("#selectTutor").val().trim() === ""){
			lstValidator.push('${tutorIdText}');
			$("#divTutor").addClass("has-error");	
		} 
		
		return lstValidator;
	}
	
	$("#buttonAdd").click(function(){
		lstValidator = validForm();
		if(lstValidator.length > 0){
			var message = '<spring:message code="common.error.validator" text="common.error.validator not found"/>';
			for(var i=0; i < lstValidator.length; i++){
				message += "<br>- " + lstValidator[i];
			}
			alert(message,null);
		} else {
			confirm('<spring:message code="group.save.quest" text="group.save.quest not found" />',addGroup,null);
		}
	});

	function addGroup(){
		
		var data = {
			corse :{
				idCourse : $("#selectCourse").val().trim()
			},
			letter : $("#inputLetter").val().trim(),
			tutor : {
				id : $("#selectTutor").val().trim()
			}
		}
		var formData = new FormData();
		formData.append('course',$("#selectCourse").val().trim());
		formData.append('letter',$("#inputLetter").val().trim());
		formData.append('tutor',$("#selectTutor").val().trim());
		
		
		
		$.ajax({
			url : "${urlSaveGroup}",
			type:"POST", 
			cache: false,
			contentType: false,
			processData: false,
			data: formData,
// 			contentType: "application/json",
			success : function(error) {
				if (error == null || error == ""){
					alert('<spring:message code="group.saved" text="group.saved not found"/>',reload);
				} 
				else {
					alert(error, null);
				}
			},
			error: function(){
				alert('<spring:message code="group.save.error" text="group.save.error not found"/>', null);
			}
		});			
	}
	
	function searchStudents(){
		if(tableStudents == null){
			createTable();
		} else {
			reloadTable();
		}
		selectedStudents = {};
		$("#divStudentsTable").show();
	}
	
	function cleanStudents(){
		selectedStudents = {};
		$("#divStudentsTable").hide();
	}
	
	function createTable(){
		tableStudents = $("#tableStudents").DataTable({
	    	sDom:'<lrtip>',
	        language: {
	            "url": '<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />'
	        },
	        ajax : {
	   			url : '${urlSearchStudents}',
	   			type : "POST",
	   			data : function(d){
	   				d.course = $("#selectCourse").val().trim();
	   				d.letter = $("#inputLetter").val().trim();
	   			},
	   			dataSrc : function ( json ) {
	   	     		if(json != null && json.length > 0){
	   		   	    	for (var i = 0; i < json.length; i++) {
	   	     	    		json[i].check = '<div class="text-center"><input type="checkbox" onChange="checkStudent(' + json[i].id + ')" ';
	   	     	    		selectedStudents[json[i].id] = false;
	   	     	    		if (json[i].checked) {
	   	     	    			json[i].check += 'checked';
	   	     	    			selectedStudents[json[i].id] = true;
	   	     	    		}
	   	     	    		json[i].check += '></div>';
	   	     	    		
	   	     	    	}
	   	     		}
	   	 	      	return json;
	   	 	    }
	   		},
	   	   	columns : [
	            { "data": "check", "width":"10%", "orderable": false},
	            { "data": "id", "visible":false},
	            { "data": "name", "width":"30%"},
	       		{ "data": "surname1", "width":"30%" },
	      		{ "data": "surname2", "width":"30%"},
	  		],
	  		order : [[3,"asc"]],
	        initComplete: function(){
	        	tableStudents.columns().every( function () {
	        		 if(this.index() != 0){
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
	    		$("#tableStudents tfoot tr").insertBefore($("#tableStudents thead tr"));
			}
	    });
			
	}
	
	function reloadTable(){
		tableStudents.ajax.reload();
	}
	function checkStudent(id){
		selectedStudents[id] = !selectedStudents[id];
	}
	
	function selectAll(cb){
		$(":checkbox", $("#tableStudents").dataTable().fnGetNodes()).each(function () { 
			  $(this).prop("checked", cb.checked);
		}); 
		for (var student in selectedStudents) {
		    if (selectedStudents.hasOwnProperty(student)) {
		    	selectedStudents[student] = cb.checked;
		    }
		}
	}
	
	$("#scheduleFile").change(function() {
	   		$("#textFile").text($("#scheduleFile").val().split('\\').pop());
	    });
	
</script>