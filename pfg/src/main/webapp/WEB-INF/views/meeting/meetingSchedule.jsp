<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/groups/emp/getTeacherSchedule/" var="urlTeacherSchedule" />
<spring:url value="/meetings/emp/getTeacherScheduleMeeting?${_csrf.parameterName}=${_csrf.token}" var="urlTeacherScheduleMeeting" />
<spring:url value="/meetings/emp/saveScheduleMeeting?${_csrf.parameterName}=${_csrf.token}" var="urlSaveScheduleMeeting" />
<spring:url value="/meetings/emp/deleteScheduleMeeting/" var="urlDeleteScheduleMeeting" />

<div class="row-fluid">
	<h3 class="title"><spring:message code="meeting.schedule.title" text="meeting.schedule.title not found" /></h3>
</div>

<div class="row-fluid" id="divTeachers">
	<form class="form-horizontal" action="">
		<div class="form-group">
			<label class="col-sm-1 control-label"><spring:message code="user.name" text="user.name not found"/></label>
			<div class="col-sm-3">
				<select class="form-control" id="selectUser">
					<c:forEach items="${lstTeachers}" var="teacher">
						<option value="${teacher.id}" label="${teacher.surname1} ${teacher.surname1}, ${teacher.name}">${teacher.surname1} ${teacher.surname1}, ${teacher.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</form>
</div>

<div class="row-fluid">
	<div id="divTable" class="col-sm-6">
		<div class="col-sm-offset-1 col-sm-10">
			<table id="tableScheduleMeetings" class="stripe hover row-border" width="90%">
				<thead>
			  		<tr>
			    		<th><spring:message code="meeting.schedule.day" text="meeting.schedule.day not found" /></th>
			    		<th><spring:message code="meeting.schedule.startHour" text="meeting.schedule.startHour not found" /></th>
			    		<th><spring:message code="meeting.schedule.endHour" text="meeting.schedule.endHour not found" /></th>
			    		<th></th>
			  	</thead>
			  	<tbody>
			  	</tbody>
			</table>
		</div>
	</div>
	<div id="divForm" class="col-sm-6">
		<form class="form-horizontal" action="">
			<div class="form-group">
				<label class="col-sm-2 control-label"><spring:message code="meeting.schedule.day" text="meeting.schedule.day not found"/></label>
				<div class="col-sm-3">
					<select class="form-control" id="selectDay">
						<option value="0" label='<spring:message code="common.monday" text="common.monday not found" />'><spring:message code="common.monday" text="common.monday not found" /></option>
						<option value="1" label='<spring:message code="common.tuesday" text="common.tuesday not found" />'><spring:message code="common.monday" text="common.monday not found" /></option>
						<option value="2" label='<spring:message code="common.wednesday" text="common.wednesday not found" />'><spring:message code="common.monday" text="common.monday not found" /></option>
						<option value="3" label='<spring:message code="common.thursday" text="common.thursday not found" />'><spring:message code="common.monday" text="common.monday not found" /></option>
						<option value="4" label='<spring:message code="common.friday" text="common.friday not found" />'><spring:message code="common.monday" text="common.monday not found" /></option>
					</select>
				</div>
				<div class="col-sm-5 text-right">
		    		<div class="col-sm-6"></div>
	    			<div class="col-sm-6">
			      		<button id ="buttonAdd" type="button" class="btn btn-block btn-primary"><i class="glyphicon glyphicon-plus" rel="tooltip"></i> <spring:message code="common.add" text="common.add not found"/></button>
		    		</div>
	      		</div>	
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"><spring:message code="meeting.schedule.startHour" text="meeting.schedule.startHour not found"/></label>
				<div class="col-sm-3">
					<div class="col-sm-5 noPadding">
						<input type="number" id="inputInitHour" class="form-control" min="0" max="23" onkeypress="return isNumberKey(event)" value="00">
					</div>
					<div class="col-sm-2 noPadding text-center aling-input">:</div>
					<div class="col-sm-5 noPadding">
						<input type="number" id="inputInitMinute" class="form-control" min="0" max="59" step="5" onkeypress="return isNumberKey(event)" value="00">
					</div>
				</div>
				<label class="col-sm-2 control-label"><spring:message code="meeting.schedule.endHour" text="meeting.schedule.endHour not found"/></label>
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
		</form>
	</div>
</div>
<div class="clearfix "></div>
<br>
<div class="row-fluid">
	<div class="form-group col-sm-12">
		<label class="col-sm-1 control-label"><spring:message code="schedule.title" text="schedule.title not found"/></label>
	</div>
	<div id="divSchedule" class="col-sm-offset-1 col-sm-10">
	</div>
</div>

<script>
var user = null;
var tableScheduleMeetings = null;

$(document).ready(function() {
	orderOptions("#selectUser");
	$("#selectUser").val($("#selectUser option:first").val());
	loadData();
});

function loadData(){
	$("#divSchedule").empty();
	if(user != $("#selectUser").val().trim() && $("#selectUser").val().trim() != ""){
		user = $("#selectUser").val().trim();
		if(tableScheduleMeetings == null){
			createTable();
		} else {
			reloadTable();
		}
		loadTeacherSchedule(user);
	}
}

function loadTeacherSchedule(user){
	$("#divSchedule").load('${urlTeacherSchedule}' + user);
}

$("#selectUser").change(function(){
	loadData();
});

function createTable(){
	var deleteText = '<spring:message code="common.delete" text="common.delete not found" />';
	tableScheduleMeetings = $("#tableScheduleMeetings").DataTable({
        language: {
            "url": '<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />'
        },
        columnDefs: [ {
            "orderable": false,
            "targets": [3]
        } ],     
        paging: false,
        info: false,
        searching: false,
        fixedHeader: {
            header: true,
            footer: false
        },
        autoWidth: false,
        ajax : {
   			url : '${urlTeacherScheduleMeeting}',
   			type : "POST",
   			data : function(d){
   				d.teacher = user;
   				d.day = "";
   				d.endHour = "";
   				d.initHour = "";
   			},
   			dataSrc : function ( json ) {
   	     		if(json != null && json.length > 0){
   		   	    	for (var i = 0; i < json.length; i++) {
   		   	    		json[i].dayName = getDayName(json[i].day);
   	     	    		json[i].operations = '<div class="text-center"><label id="deleteUser" class="cursorPointer iconTable" onclick="deleteScheduleMeeting(\'' + json[i].id + '\')"><i class="glyphicon glyphicon-trash" title="'+ deleteText + '"> </i></label></div>';
   	     	    	}
   	     		}
   	 	      	return json;
   	 	    }
   		},
   	   	columns : [
            { "data": "dayName", "width":"40"},
            { "data": "initHour", "width":"25%"},
            { "data": "endHour", "width":"25%"},
       		{ "data": "operations", "width":"10%" }
  		],
    });
}

function reloadTable(){
	tableScheduleMeetings.ajax.reload();
}

function getDayName(day){
	switch (day) {
	case 0:
		return '<spring:message code="common.monday" text="common.monday not found" />';
	case 1:
		return '<spring:message code="common.tuesday" text="common.tuesday not found" />';
	case 2:
		return '<spring:message code="common.wednesday" text="common.wednesday not found" />';
	case 3:
		return '<spring:message code="common.thursday" text="common.thursday not found" />';
	case 4:
		return '<spring:message code="common.friday" text="common.friday not found" />';
	default:
		return "";
	}
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

$("#buttonAdd").click(function(){
	var data = {
			day : $("#selectDay").val(),
			teacher : $("#selectUser").val(),
			initHour : $("#inputInitHour").val() + ":" + $("#inputInitMinute").val(),
			endHour : $("#inputEndHour").val() + ":" + $("#inputEndMinute").val()
	}
  			
	$.ajax({
		url : "${urlSaveScheduleMeeting}",
		type:"POST", 
		data: JSON.stringify(data),
		contentType: "application/json",
		success : function() {
			reloadTable();
		},
		error: function(){
			alert('<spring:message code="meeting.schedule.save.error" text="meeting.schedule.save.error not found"/>', null);
		}
	});			
})

function deleteScheduleMeeting(id){
	$.ajax({
		url : "${urlDeleteScheduleMeeting}" + id,
		type:"GET", 
		success : function() {
			reloadTable();
		},
		error: function(){
			alert('<spring:message code="meeting.schedule.delete.error" text="meeting.schedule.delete.error not found"/>', null);
		}
	});
}
</script>