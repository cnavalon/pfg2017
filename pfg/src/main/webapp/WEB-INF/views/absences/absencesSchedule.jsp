<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/absences/emp/getTeacherSchedule/" var="urlSchedule" />
<spring:url value="/absences/emp/modalAbsences/" var="urlModalAbsences" />
<spring:url value="/absences/emp/saveAbsences?${_csrf.parameterName}=${_csrf.token}" var="urlSaveAbsences" />


<div class="row-fluid">
	<h3 class="title"><spring:message code="absences.title" text="absences.title not found" /></h3>
</div>

<form class="form-horizontal" action="">
	<div class="row-fluid" id="divSelect">
		<div class="form-group">
			<label class="col-sm-2 control-label"><spring:message code="absences.teacher" text="absences.teacher not found"/></label>
			<div class="col-sm-3">
				<select class="form-control" id="selectTeacher">
					<c:forEach items="${lstTeacher}" var="teacher">
						<option value="${teacher.id}" label="${teacher.fullName}">${teacher.fullName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	
	<div class="row-fluid">
		<div class="col-sm-offset-1 col-sm-10">
			<div class="col-sm-12 noPadding">
				<div class="col-sm-2 noPadding">
		      		<button type="button" id ="buttonPrevious" class="form-control btn btn-default"><i class="glyphicon glyphicon-chevron-left" rel="tooltip"></i> <spring:message code="common.previous" text="common.previous not found"/></button>
	    		</div>
	    		<div class="col-sm-8 noPadding"></div>
	    		<div class="col-sm-2 noPadding">
		      		<button type="button" id ="buttonNext" class="form-control btn btn-default" disabled><spring:message code="common.next" text="common.next not found"/> <i class="glyphicon glyphicon-chevron-right" rel="tooltip"></i></button>
	    		</div>
			</div>
			<div id="divDays" class="dates-schedule" style="width:100%;display: table;">
			</div>
			
		</div>
	</div>
	
	<div class="row-fluid">
		<div id="divAbsenceSchedule" class="col-sm-offset-1 col-sm-10" >
		</div>
	</div>
	
</form>


<script>
var teacher = null;
var count = 0;
var date = moment().startOf('week').add(1,'d');

var day = null;
var schedule = null;
var subject = null;
var group = null;
var lstAbsences =[];

$(document).ready(function() {
	orderAllOptions();
	$("#selectTeacher").val($("#selectTeacher option:first").val());
	teacher = $("#selectTeacher").val();
	if(teacher != null && teacher != ""){
		loadSchedule();
	}
	printDates();
});

$("#selectTeacher").change(function(){
	$("#divAbsenceSchedule").empty();
	teacher = $("#selectTeacher").val();
	if(teacher != null && teacher != ""){
		loadSchedule();
	} 
});

$("#buttonPrevious").click(function(e){
	count++;
	date = moment(date).subtract(7,'d');
	checkCount();
	printDates();
});

$("#buttonNext").click(function(e){
	count--;
	date = moment(date).add(7,'d');
	checkCount();
	printDates();
});

function checkCount(){
	if(count > 0){
		$("#buttonNext").removeAttr("disabled");
	} else {
		$("#buttonNext").attr("disabled", true);
	}
}

function printDates(){
	$("#divDays").empty();
	var aDate = moment(date);
	$("#divDays").append('<div style="width:5%;display: table-cell;"></div>');
	for(var i = 0; i < 5; i++){
		var div = '<div style="width:19%;display: table-cell;" class="text-center">' + moment(aDate).format("DD/MM/YYYY") + '</div>';
		aDate = moment(aDate).add(1,'d');
		$("#divDays").append(div);
	}
}

function loadSchedule(){
	$("#divAbsenceSchedule").load("${urlSchedule}" + teacher);
}

function actionCell(idSchedule, idGroup, dayNumber, subjectName, groupName){
	lstAbsences =[];
	day = moment(date).add(dayNumber,'d');
	schedule = idSchedule;
	subject = subjectName;
	group = groupName;
	
	modal("${urlModalAbsences}" + idGroup + "/" + schedule + "/" + day, saveAbsences, null);
	
}
	
function saveAbsences(){
	$.ajax({
		url : "${urlSaveAbsences}",
		type:"POST", 
		data: JSON.stringify(lstAbsences),
		contentType: "application/json",
		success : function(text) {
			alert(text,null);
		},
		error: function(){
			alert('<spring:message code="qualifications.save.error" text="qualifications.save.error not found"/>', null);
		}
	});			
}


</script>