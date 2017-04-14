<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/meetings/tut/getMeetingParents/" var="urlMeetingParents" />
<spring:url value="/meetings/tut/getMeetingTeacher/" var="urlMeetingTeacher" />
<spring:url value="/meetings/tut/requestMeeting?${_csrf.parameterName}=${_csrf.token}" var="urlRequestMeeting" />

<div class="row-fluid">
	<h3 class="title"><spring:message code="meeting.request.title" text="meeting.request.title not found" /></h3>
</div>

<c:choose>
	<c:when test="${empty lstStudent}">
		<div class="vertical-center">
			<div class="box-center text-center">
				<span id="spanAccessDeniedText" class="alert"><spring:message code="meeting.notTutor" text="meeting.notTutor not found" /></span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="row-fluid">
			<form id="requestMeetingForm" class="form-horizontal" action="" >
			
				<div id="divRow1" class="form-group">
					<div id="divStudent">
						<label class="col-sm-2 control-label"><spring:message code="role.student" text="role.student not found"/>*</label>
						<div class="col-sm-3">
							<select class="form-control" id="selectStudent">
								<c:forEach items="${lstStudent}" var="student">
									<option value="${student.id}#${student.group}" label="${student.fullName}">${student.fullName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				
				<div id="divRow2" class="form-group">
					<div id="divDay">
						<label class="col-sm-2 control-label"><spring:message code="meeting.day"  text="meeting.day not found"/>*</label>
						<div class="col-sm-3 date">
							<div id="dayAction" class="input-group input-append date">
								<input type='text' class="form-control" id="inputDay" >
								<span id="spanDay" class="input-group-addon add-on">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
					</div>
					
					<div id="divHour">
						<label class="col-sm-2 control-label"><spring:message code="meeting.hour" text="meeting.hour not found"/>*</label>
						<div class="col-sm-3">
							<select class="form-control" id="selectHour">
							</select>
						</div>
					</div>
				</div>
				
				<div id="divRow3" class="form-group">
					<label class=" col-sm-2 control-label"><spring:message code="meeting.sentTo" text="meeting.sentTo not found"/> (<span id="attendeesCount"></span>)*</label>
					<div  class="col-sm-8 form-group">
						<div id="divAttendees" class="form-box">				
						</div>
					</div>
				</div>
				
				<div id="divRow4" class="form-group">
					<div id="divComments">
						<label class="col-sm-2 control-label"><spring:message code="meeting.comments" text="meeting.comments not found"/></label>
						<div class="col-sm-8">
							<textarea id="comments" class="form-control" rows="3" maxlength="255"></textarea>
						</div>
					</div>
				</div>
				
				<div id="divRow5" class="form-group">
					<span class="col-sm-offset-2 col-sm-3"><small>* <spring:message code="common.mandatoryFields"  text="common.mandatoryFields not found"/></small></span>
				</div>
				
				<div  id="divRow6" class="form-group ">
			    	<div class="col-sm-offset-4 col-sm-6 text-right">
			    		<div class="col-sm-4"></div>
			   			<div class="col-sm-4">
				      		<button id ="buttonAdd" type="button" class="btn btn-block btn-default"><spring:message code="common.request" text="common.request not found"/></button>
			    		</div>
			    		<div class="col-sm-4">
				      		<button id ="buttonCancel" type="button" class="btn btn-block btn-default" ><spring:message code="common.cancel" text="common.cancel not found"/></button>
			    		</div>
			   		</div>
				</div>
			</form>
		</div>
	</c:otherwise>
</c:choose>

<script>
var isTeacher = ("${isTeacher}" == "true");
var teacher = null;
var mapDayHour = null;
var student = null;
var parents = [];
var me = null;
var meId = "${sessionScope.sessionUserId}";
var group = null;

$(document).ready(function() {
	<c:if test="${not empty lstStudent}">
		orderAllOptions();
		$("#selectStudent").val($("#selectStudent option:first").val());
		var infoStudent = $("#selectStudent").val().split("#");
		student = infoStudent[0];
		if(infoStudent.length > 1){
			group = infoStudent[1];
		}
		if(isTeacher){
			loadTeacher(student);
		}
		loadData();
	</c:if>

});

$("#selectStudent").change(function(){
	loadData();
});

function loadData(){
	$("#buttonAdd").prop("disabled",true);
	
	$("#divAttendees").empty();
	$("#comments").val("");
	var infoStudent = $("#selectStudent").val().split("#");
	student = infoStudent[0];
	if(infoStudent.length > 1){
		group = infoStudent[1];
	}
	if(!isTeacher){
		$("#inputDay").val("");
		$("#selectHour").empty();
		loadTeacher(student);
	}
	loadParent(student);
};

function loadTeacher(student){
	teacher = null;
	$.ajax({
		url : "${urlMeetingTeacher}" + student,
		type:"GET", 
		success : function(response) {
			if(response == null || response == ""){
				alert('<spring:message code="meeting.request.group.error" text="meeting.request.group.error not found"/>', null);
			} else {
				teacher = response.teacher;
				mapDayHour = response.mapDayHour;
				var disableDays = [0,6];
				
				for(var i=0; i<5; i++){
					if(!mapDayHour.hasOwnProperty(i)){
						disableDays.push(i+1);
					}
				}
				if(disableDays.length == 7){
					disableDays = [];
				}
				$('#dayAction').data("DateTimePicker").daysOfWeekDisabled(disableDays);
				if(isTeacher){
					me = teacher;
				} else {
					$("#divAttendees").prepend(getCheckbox(teacher, '<spring:message code="role.teacher" text="role.teacher not found"/>', true));
				}
				countAttendees();
			}
		},
		error: function(){
			alert('<spring:message code="meeting.request.teacher.error" text="meeting.request.teacher.error not found"/>', null);
			validForm();
		}
	});
}

function loadParent(student){
	parents = [];
	$.ajax({
		url : "${urlMeetingParents}" + student,
		type:"GET", 
		success : function(response) {
			if(response == null || response == ""){
				alert('<spring:message code="meeting.request.parent.error" text="meeting.request.parent.error not found"/>', null);
			} else {
				response;
				for(var i=0; i<response.length; i++){
					if(!isTeacher && response[i].id == meId){
						me = response[i];
					}else {
						parents.push(response[i]);
						$("#divAttendees").append(getCheckbox(response[i], '<spring:message code="role.parent" text="role.parent not found"/>', false));
					}
					countAttendees();
				}
			}
		},
		error: function(){
			alert('<spring:message code="meeting.request.parent.error" text="meeting.request.parent.error not found"/>', null);
			validForm();
		}
	});
}

$('#dayAction').datetimepicker({
    format: 'DD/MM/YYYY',
    locale: '${locale}',
    minDate : new Date()
});

$('#dayAction').on("dp.change",function(){
	$("#selectHour").empty();
	if($("#inputDay").val() != ""){
		var day = $('#dayAction').data("DateTimePicker").date().day() - 1;
		if(mapDayHour.hasOwnProperty(day)){
			for(var i=0; i < mapDayHour[day].length; i++){
				var hour = mapDayHour[day][i];
				$("#selectHour").append('<option value="' + hour + '" label="' + hour + '">' + hour + '</option>');
			}
		}
		orderOptions("#selectHour");
	}
	validForm();
});

function getCheckbox(person, role , disabled){
	var checkbox =  '<div class="checkbox"><label><input id="' + person.idUser + '" type="checkbox" value="" onclick="countAttendees()" checked ';
	if(disabled){
		checkbox += 'disabled ';
	}
	checkbox += '>' + person.surname1 + ' ' + person.surname2 + ', ' + person.name + ' (' + role + ')' + '</label></div>';
	return checkbox;
}

$("#buttonCancel").click(function(){
	confirm('<spring:message code="common.loseChanges" text="common.loseChanges not found"/>', function(){reload()}, null);
});

$("#buttonAdd").click(function(){
	confirm('<spring:message code="meeting.request.quest" text="meeting.request.quest not found"/>', function(){requestMeeting()}, null);
});

function requestMeeting(){
	
	var lstAttendee = [];
	$("input[type=checkbox]:checked").each(function() {
		var attende = {
				user : $(this).prop("id"),
				active : true,
				status : 'P'
		}
		lstAttendee.push(attende);
	});
	
	data = {
			type : 'T',
			user : me.idUser,
			date : $('#dayAction').data("DateTimePicker").date(),
			hour : $("#selectHour").val(),
			student : {id: student},
			group : {id: group},
			status : 'P',
			comments : $("#comments").val(),
			active : true,
			lstAttendee : lstAttendee
	}
	
	$.ajax({
		url : "${urlRequestMeeting}",
		type:"POST", 
		data: JSON.stringify(data),
		contentType: "application/json",
		success : function(text) {
			if(text == null || text == "" )
				text = '<spring:message code="meeting.request.error" text="meeting.request.error not found"/>';
			alert(text, reload);
		},
		error: function(){
			alert('<spring:message code="meeting.request.error" text="meeting.request.error not found"/>', reload);
		}
	});		
}

function validForm(){
	if(
		$("#selectStudent").val() != "" &&
		$("#selectStudent").val() != null &&
		$("#inputDay").val() != "" &&
		$("#selectHour").val() != "" &&
		$("#selectHour").val() != null &&
		$("input[type=checkbox]:checked").length > 0
	){
		$("#buttonAdd").prop("disabled",false);
	} else {
		$("#buttonAdd").prop("disabled",true);
		
	}
}

function countAttendees(){
	$("#attendeesCount").text("" +$("input[type=checkbox]:checked").length);
	validForm();
}


</script>
