<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/groups/adm/save?${_csrf.parameterName}=${_csrf.token}" var="urlSaveGroup" />
<spring:url value="/groups/emp/groups" var="urlGroupsList" />
<%-- <spring:url value="/groups/adm/searchStudents?${_csrf.parameterName}=${_csrf.token}" var="urlSearchStudents" /> --%>
<spring:url value="/groups/adm/searchFreeStudents/" var="urlSearchFreeStudents" />
<spring:url value="/groups/adm/searchSelectedStudents?${_csrf.parameterName}=${_csrf.token}" var="urlSearchSelectedStudents" />
<spring:url value="/groups/adm/validLetter?${_csrf.parameterName}=${_csrf.token}" var="urlValidLetter" />

<div class="row-fluid">
	<h3 class="title">${title}</h3>
</div>

<div class="row-fluid">
	
	<div class="col-sm-12">
		<form:form id="groupForm" class="form-horizontal" method="post" modelAttribute="group" enctype="multipart/form-data" action="javascript:;">
			<fieldset id="filedsetForm">
				<form:input type="hidden" path="id" id="inputId"/>
				<div id="divRow0" class="form-group">
					<div id="divCourse">
						<spring:message code="group.course" var="courseIdText" text="group.course not found"/>
						<label class="col-sm-2 control-label">${courseIdText}*</label>
						<div class="col-sm-3">
							<c:choose>
								<c:when test="${isNew}">
									<form:select class="form-control" id="selectCourse" path="course.idCourse">
			      						<form:option value="" label=""/>
			      						<form:options items="${lstCourses}" itemValue="idCourse" itemLabel="name"/>
			 						</form:select>
								</c:when>
								<c:otherwise>
									<form:input type="hidden" path="course.idCourse" id="selectCourse"/>
									<form:input type="text" path="course.name" class="form-control" id="inputCourseName" disabled="true"/>
								</c:otherwise>
							</c:choose>
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
			    			<input class="selectFileInput" id="scheduleFile" type="file" name="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" >
						</div>
					</div>
				</div>
			</fieldset>
			
			<div id="divRow2" class="form-group">
				<div id="divStudentsTitle">
					<label class="col-sm-2 control-label">
						<spring:message code="group.students" text="group.students not found"/> 
					</label>
					<div class="col-sm-10"></div>
				</div>
				
				<div class="clearfix"></div>
				<div id="divStudentsBody" class="form-group row flex-parent">
					<div class="col-sm-2"></div>
					<div class="col-sm-3">
						<label class="col-sm-offset-1 control-label header-options">
							<spring:message code="group.students.free" text="group.students.free not found"/> : 
							<span id="spanFreeStudentsCount"></span>
						</label>
						<select id="selectFreeStudents" class="form-control" multiple size="15">
						</select>
					</div>
					<div class="col-sm-2 flex-child">
						<div class="text-center btn-group-vertical">
							<a id ="buttonAddAllStudent" class="btn btn-default"><span class="glyphicon glyphicon-chevron-right"/><span class="glyphicon glyphicon-chevron-right"/></a>
							<a id ="buttonAddStudent"  class="btn btn-default"><span class="glyphicon glyphicon-chevron-right"/></a>
							<a id ="buttonDeleteStudent"  class="btn btn-default"><span class="glyphicon glyphicon-chevron-left"/></a>
							<a id ="buttonDeleteAllStudent"  class="btn btn-default"><span class="glyphicon glyphicon-chevron-left"/><span class="glyphicon glyphicon-chevron-left"/></a>
						</div>
					</div>
					<div class="col-sm-3">
						<label class="col-sm-offset-1 control-label header-options">
							<spring:message code="group.students.selected" text="group.students.selected not found"/> : 
							<span id="spanSelectedStudentsCount"></span>
						</label>
						<select id="selectSelectedStudents" class="form-control" multiple="true" size="15">
						<select>
					</div>
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
	
// 	var tableStudents = null;
	var selectedStudents = [];
	var freeStudents = [];
	var skipStudents = true;
	var skipSchedule = true;
	var isNew = ("${isNew}" == "true");
	var letterOriginal = null;
	
	$(document).ready(function() {
		$(document).ajaxStart(function() {blockUI();}).ajaxStop(function() {unblockUI();});
		orderOptions("#selectTutor");
		if(!isNew){
			searchFreeStudents($("#selectCourse").val());
			searchSelectedStudents($("#selectCourse").val(), $("#inputLetter").val());
			letterOriginal = $("#inputLetter").val();
		}
	} );
	
	$('#buttonCancel').click(function(event){
		confirm('<spring:message code="common.loseChanges" text="common.loseChanges not found"/>', function(){redirect("${urlGroupsList}")}, null);
	});
	
	$("#selectCourse").change(function(){
		if($("#selectCourse").val() != ""){
			$("#divCourse").removeClass("has-error");
			searchFreeStudents($("#selectCourse").val());
			validGroupLetter($("#selectCourse").val(), $("#inputLetter").val().trim());
		} else {
			cleanStudents();
			cleanLetter();
		}
	});
	
	$("#inputLetter").change(function(){
		if($("#inputLetter").val().trim() != ""){
			$("#inputLetter").val($("#inputLetter").val().toUpperCase());
		}
		if($("#selectCourse").val() != ""){
			if(isNew || $("#inputLetter").val().trim() != letterOriginal){
				validGroupLetter($("#selectCourse").val(), $("#inputLetter").val().trim());
			} else {
				$("#divLetter").removeClass("has-error");
				$("#buttonAdd").prop("disabled",false);
			}
		}
	})
	
	$("#selectTutor").change(function(){
		if($("#selectTutor").val().trim() != ""){
			$("#divTutor").removeClass("has-error");
		}
	});

	function validForm(){
		var lstValidator = [];
		if($("#selectCourse").val() === ""){
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
		
		var formData = new FormData($("#groupForm")[0]);
		formData.append("scheduleFile", $("#textFile").text());
		formData.append("lstStudents", getValues(selectedStudents));
		formData.append("skipStudents", skipStudents);
		formData.append("skipSchedule", skipSchedule);
	  			
		$.ajax({
			url : "${urlSaveGroup}",
			type:"POST", 
			data: formData,
			async: false,
			cache: false,
			contentType: false,
			processData: false,
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
	
	
	
	$("#scheduleFile").change(function() {
		$("#textFile").text($("#scheduleFile").val().split('\\').pop());
		skipSchedule = false;
	});
	
	function drawAllStudentsList(){
		drawStudents("#selectFreeStudents", freeStudents, "#spanFreeStudentsCount");
		drawStudents("#selectSelectedStudents", selectedStudents, "#spanSelectedStudentsCount");
	}
	
	function drawStudents(selectId,list,spanId){
		$(selectId).empty();
		$(spanId).text(list.length);
		list.sort(function(a,b) {
		    if (a.label > b.label) return 1;
		    if (a.label < b.label) return -1;
		    return 0
		})
		for(var i = 0; i < list.length; i++){
			$(selectId).append('<option value="' + list[i].value +'" label="' + list[i].label + '"/>');
		}
	};
	
	function moveAllStudents(from, to){
		to = to.concat(from);
		from = [];
		drawAllStudentsList();
	}
	
	function moveStudents(fromId, from, to){
		var lstValues = $("fromId").val();
		for(var i = 0; i < from.length; i++){
			if(lstValues.indexOf(from[i].value) != -1){
				to.push(from[i]);
				from.splice(i,1);
				i--;
			}
		}
		drawAllStudentsList();
	}
	
	$("#buttonAddAllStudent").click(function(){
		selectedStudents = selectedStudents.concat(freeStudents);
		freeStudents = [];
		drawAllStudentsList();
		skipStudents = false;
	});
	$("#buttonDeleteAllStudent").click(function(){
		freeStudents = freeStudents.concat(selectedStudents);
		selectedStudents = [];
		drawAllStudentsList();
		skipStudents = false;
	});
	$("#buttonAddStudent").click(function(){
		var lstValues = $("#selectFreeStudents").val();
		for(var i = 0; i < freeStudents.length; i++){
			if(lstValues.indexOf(""+freeStudents[i].value) != -1){
				selectedStudents.push(freeStudents[i]);
				freeStudents.splice(i,1);
				i--;
			}
		}
		drawAllStudentsList();
		skipStudents = false;
	});
	$("#buttonDeleteStudent").click(function(){
		var lstValues = $("#selectSelectedStudents").val();
		for(var i = 0; i < selectedStudents.length; i++){
			if(lstValues.indexOf(""+selectedStudents[i].value) != -1){
				freeStudents.push(selectedStudents[i]);
				selectedStudents.splice(i,1);
				i--;
			}
		}
		drawAllStudentsList();
		skipStudents = false;
	});
	
	function searchFreeStudents(course){
		freeStudents = [];
		selectedStudents = [];
		$.ajax({
			url : '${urlSearchFreeStudents}' + course,
			type: "GET", 
			success : function(data) {
				if (data != null && data.length > 0){
					freeStudents = data;
				} 
				drawAllStudentsList();
			},
			error: function(){
				alert('<spring:message code="group.freeStudents.error" text="group.freeStudents.error"/>',null);
				drawAllStudentsList();
			}
		});		
	}
	
	function searchSelectedStudents(course, letter){
		selectedStudents = [];
		var data = {
				course : course,
				letter : letter
			};
		$.ajax({
			url : '${urlSearchSelectedStudents}',
			type: "POST", 
			data: data,
			success : function(data) {
				if (data != null && data.length > 0){
					selectedStudents = data;
				} 
				drawAllStudentsList();
			},
			error: function(){
				alert('<spring:message code="group.selectedStudents.error" text="group.selectedStudents.error"/>',null);
				drawAllStudentsList();
			}
		});		
	}
	
	function cleanStudents(){
		freeStudents = [];
		selectedStudents = [];
		drawAllStudentsList();
	}
	
	function validGroupLetter(course, letter){
		var data = {
			course : course,
			letter : letter
		};
		$.ajax({
			url : '${urlValidLetter}',
			type: "POST", 
			data : data,
			success : function(error) {
				if(error == null || error == ""){
					$("#divLetter").removeClass("has-error");
					$("#buttonAdd").prop("disabled",false);
				} else {
					errorLetter(error);
				}
				return error;
			},
			error: function(){
				errorLetter('<spring:message code="group.exist.error" text="group.exist.error"/>');
			}
		});		
	}
	
	function cleanLetter(){
		$("#inputLetter").val("");
		$("#divLetter").removeClass("has-error");
		$("#buttonAdd").prop("disabled",false);
	};
	
	function errorLetter(text){
		$("#divLetter").addClass("has-error");
		$("#buttonAdd").prop("disabled",true);
		alert(text,null);
	};
	
</script>