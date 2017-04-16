<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/meetings/tut/requestMeeting?${_csrf.parameterName}=${_csrf.token}" var="urlRequestMeeting" />
<spring:url value="/meetings/atp/diary" var="urlDiary" />


<div class="row-fluid">
	<h3 class="title"><spring:message code="meeting.request.parent.title" text="meeting.request.parent.title not found" /></h3>
</div>

<c:choose>
	<c:when test="${empty group}">
		<div class="vertical-center">
			<div class="box-center text-center">
				<span id="spanAccessDeniedText" class="alert"><spring:message code="meeting.notTutor" text="meeting.notTutor not found" /></span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="row-fluid">
			<form id="requestParentForm" class="form-horizontal" action="" >
				
				<div id="divRow1" class="form-group">
					<div id="divGroup">
						<label class="col-sm-2 control-label"><spring:message code="user.group" text="user.group not found"/></label>
						<div class="col-sm-3 control-label text-left"> ${group.course.name} - ${group.letter}</div>
					</div>
					
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
				</div>
				
				<div id="divRow2" class="form-group">
					<label class="col-sm-2 control-label"><spring:message code="meeting.startHour" text="meeting.startHour not found"/>*</label>
					<div class="col-sm-3">
						<div class="col-sm-5 noPadding">
							<input type="number" id="inputInitHour" class="form-control" min="0" max="23" onkeypress="return isNumberKey(event)" value="00">
						</div>
						<div class="col-sm-2 noPadding text-center aling-input">:</div>
						<div class="col-sm-5 noPadding">
							<input type="number" id="inputInitMinute" class="form-control" min="0" max="59" step="5" onkeypress="return isNumberKey(event)" value="00">
						</div>
					</div>
					<label class="col-sm-2 control-label"><spring:message code="meeting.endHour" text="meeting.endHour not found"/>*</label>
					<div class="col-sm-3">
						<div class="col-sm-5 noPadding">
							<input type="number" id="inputEndHour" class="form-control" min="0" max="23" onkeypress="return isNumberKey(event)" value="00">
						</div>
						<div class="col-sm-2 noPadding text-center aling-input">:</div>
						<div class="col-sm-5 noPadding">
							<input type="number" id="inputEndMinute" class="form-control" min="0" max="59" step="5" onkeypress="return isNumberKey(event)" value="00">
						</div>
					</div>
				</div>
				
				<div id="divRow3" class="form-group">
					<label class=" col-sm-2 control-label"><spring:message code="meeting.sentTo" text="meeting.sentTo not found"/> (<span id="attendeesCount"></span>)*</label>
					<div  class="col-sm-8 form-group">
						<div id="divAttendees" class="form-box">
							<c:forEach items="${mapStudentParent}" var="item">
								<c:set var="student" value="${item.key}"></c:set>
								<c:forEach items="${item.value}" var="parent">
									<div class="checkbox">
										<label>
											<input id="${parent.idUser}" type="checkbox" value="" onclick="countAttendees()" checked >
											${parent.fullName}
											(<spring:message code="role.student" text="role.student not found"/>: ${student.fullName})
										</label>
									</div>								
								</c:forEach>							
							</c:forEach>				
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

$(document).ready(function() {
	countAttendees();
});

$('#dayAction').datetimepicker({
    format: 'DD/MM/YYYY',
    locale: '${locale}',
    minDate : new Date(),
    daysOfWeekDisabled: [0,6]
});

$("#buttonCancel").click(function(){
	confirm('<spring:message code="common.loseChanges" text="common.loseChanges not found"/>', goToDiary, null);
});

$("#buttonAdd").click(function(){
	confirm('<spring:message code="meeting.request.quest" text="meeting.request.quest not found"/>', function(){requestMeeting()}, null);
});

function getHour(){
	var hour = "";
	hour += $("#inputInitHour").val();
	hour += ":";
	hour += $("#inputInitMinute").val();
	hour += " - ";
	hour += $("#inputEndHour").val();
	hour += ":";
	hour += $("#inputEndMinute").val();
	return hour;
}

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
			type : 'P',
			user : "${group.tutor.idUser}",
			date : $('#dayAction').data("DateTimePicker").date(),
			hour : getHour(),
			group : {id: "${group.id}"},
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
			alert(text, goToDiary);
		},
		error: function(){
			alert('<spring:message code="meeting.request.error" text="meeting.request.error not found"/>', goToDiary);
		}
	});		
}

function validForm(){
	if(
		$("#inputDay").val() != "" &&
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

$("#inputInitHour").change(function(){
	var value = $("#inputInitHour").val();
	if(value == ""){
		$("#inputInitHour").val("00");
	} else if(value.length < 2){
		$("#inputInitHour").val("0" + value);
	} else if(value > 23){
		$("#inputInitHour").val("23");
	} else if(value.length > 2){
		$("#inputInitHour").val(value.slice(-2));
	}
})
$("#inputEndHour").change(function(){
	var value = $("#inputEndHour").val();
	if(value == ""){
		$("#inputEndHour").val("00");
	} else if(value.length < 2){
		$("#inputEndHour").val("0" + value);
	} else if(value > 23){
		$("#inputEndHour").val("23");
	} else if(value.length > 2){
		$("#inputEndHour").val(value.slice(-2));
	}
})
$("#inputInitMinute").change(function(){
	var value = $("#inputInitMinute").val();
	if(value == ""){
		$("#inputInitMinute").val("00");
	} else if(value.length < 2){
		$("#inputInitMinute").val("0" + value);
	} else if(value > 59){
		$("#inputInitMinute").val("59");
	} else if(value.length > 2){
		$("#inputInitMinute").val(value.slice(-2));
	}
})

$("#inputEndMinute").change(function(){
	var value = $("#inputEndMinute").val();
	if(value == ""){
		$("#inputEndMinute").val("00");
	} else if(value.length < 2){
		$("#inputEndMinute").val("0" + value);
	} else if(value > 59){
		$("#inputEndMinute").val("59");
	} else if(value.length > 2){
		$("#inputEndMinute").val(value.slice(-2));
	}
})

$('#dayAction').on("dp.change",function(){
	validForm();
});

function goToDiary(){
	redirect("${urlDiary}");
}

</script>
