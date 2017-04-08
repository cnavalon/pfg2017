<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/groups/getStudentSchedule/" var="urlStudentSchedule" />
<spring:url value="/groups/emp/getTeacherSchedule/" var="urlTeacherSchedule" />

<div class="row-fluid">
	<h3 class="title"><spring:message code="schedule.title" text="schedule.title not found" /></h3>
</div>

<div class="row-fluid" id="divUsers">
	<form class="form-horizontal" action="">
		<div class="form-group">
			<label class="col-sm-2 control-label"><spring:message code="user.name" text="user.name not found"/></label>
			<div class="col-sm-3">
				<select class="form-control" id="selectUser">
					<c:forEach items="${lstUsers}" var="user">
						<option value="${user.id}#${user.idRole}" label="${user.fullName}">${user.fullName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</form>
</div>

<div class="row-fluid">
	<div id="divTable" class="col-sm-offset-1 col-sm-10">
	</div>
</div>

<script>
var lastUser = null;

$(document).ready(function() {
	orderAllOptions();
	$("#selectUser").val($("#selectUser option:first").val());
	getSchedule();
});

function getSchedule(){
	$("#divTable").empty();
	if(lastUser != $("#selectUser").val().trim()){
		lastUser = $("#selectUser").val().trim();
		var userData = lastUser.split("#");
		if(userData != null && userData.length == 2){
			if(userData[1] == "ROLE_STD"){
				loadStudentSchedule(userData[0]);
				return;
			} else if(userData[1] == "ROLE_TCH"){
				loadTeacherSchedule(userData[0]);
				return;
			}
		}
		alert('<spring:message code="schedule.user.incorrect" text="schedule.user.incorrect not found" />', null);
	}
}

function loadStudentSchedule(user){
	$("#divTable").load('${urlStudentSchedule}' + user);
}

function loadTeacherSchedule(user){
	$("#divTable").load('${urlTeacherSchedule}' + user);
}

$("#selectUser").change(function(){
	getSchedule();
});

</script>