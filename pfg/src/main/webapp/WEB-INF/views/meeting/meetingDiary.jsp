<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/meetings/atp/getMeeting/" var="urlMeeting" />
<spring:url value="/meetings/atp/getAttendee/" var="urlAttendee" />
<spring:url value="/meetings/tut/cancelMeeting/" var="urlCancelMeeting" />
<spring:url value="/meetings/tut/responseMeeting?${_csrf.parameterName}=${_csrf.token}" var="urlResponseMeeting" />

<div class="row-fluid">
	<h3 class="title"><spring:message code="meeting.diary.title" text="meeting.diary.title not found" /></h3>
</div>

<div class="row-fluid">
	<form id="requestMeetingForm" class="form-horizontal" action="" >
	
		<div id="divRow1" class="form-group">
			<c:if test="${pageContext.request.isUserInRole('ADM') || pageContext.request.isUserInRole('TCH')}">
				<div id="divTeacher">
					<label class="col-sm-2 control-label"><spring:message code="role.teacher" text="role.teacher not found"/></label>
					<div class="col-sm-3">
						<select class="form-control" id="selectTeacher">
							<c:if test="${pageContext.request.isUserInRole('ADM')}">
								<option value="" label=""></option>
							</c:if>
							<c:forEach items="${lstTeacher}" var="teacher">
								<option value="${teacher.idUser}" label="${teacher.fullName}">${teacher.fullName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</c:if>
			
			<c:if test="${pageContext.request.isUserInRole('ADM') || pageContext.request.isUserInRole('PAR')}">
				<div id="divParent">
					<label class="col-sm-2 control-label"><spring:message code="role.parent" text="role.parent not found"/></label>
					<div class="col-sm-3">
						<select class="form-control" id="selectParent">
							<c:if test="${pageContext.request.isUserInRole('ADM')}">
								<option value="" label=""></option>
							</c:if>
							<c:forEach items="${lstParent}" var="parent">
								<option value="${parent.idUser}" label="${parent.fullName}">${parent.fullName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</c:if>
		</div>
	</form>
</div>

<div class="row-fluid" id="divTable" hidden>
	<div class="col-sm-offset-1 col-sm-10">
		<table id="tableMeetings" class="stripe hover row-border" width="100%">
			<thead>
		  		<tr>
		    		<th><spring:message code="meeting.meeting" text="meeting.meeting not found" /></th>
		    		<th><spring:message code="meeting.day" text="meeting.day not found" /></th>
		    		<th><spring:message code="meeting.hour" text="meeting.hour not found" /></th>
		    		<th><spring:message code="meeting.type" text="meeting.type not found" /></th>
		    		<th><spring:message code="role.student" text="role.student not found" /></th>
		    		<th><spring:message code="user.group" text="user.group not found" /></th>
		    		<th><spring:message code="meeting.create" text="meeting.create not found" /></th>
		    		<th><spring:message code="meeting.state" text="meeting.state not found" /></th>
		    		<th><spring:message code="meeting.comments" text="meeting.comments not found" /></th>
		    		<th></th>
		  	</thead>
		  	<tfoot>
			  	<tr>
		    		<th><spring:message code="meeting.meeting" text="meeting.meeting not found" /></th>
			  		<th><spring:message code="meeting.day" text="meeting.day not found" /></th>
		    		<th><spring:message code="meeting.hour" text="meeting.hour not found" /></th>
		    		<th><spring:message code="meeting.type" text="meeting.type not found" /></th>
		    		<th><spring:message code="role.student" text="role.student not found" /></th>
		    		<th><spring:message code="user.group" text="user.group not found" /></th>
		    		<th><spring:message code="meeting.create" text="meeting.create not found" /></th>
		    		<th><spring:message code="meeting.state" text="meeting.state not found" /></th>
		    		<th><spring:message code="meeting.comments" text="meeting.comments not found" /></th>
		    		<th></th>
			  	</tr>
		  	</tfoot>
		  	<tbody>
		  	</tbody>
		</table>
	</div>
</div>


<script>

var idUser = null;
var role = '${sessionScope.sessionUserRole.idRole}';
var attendeesText = '<spring:message code="meeting.attendees" text="meeting.attendees not found" />';
var acceptText = '<spring:message code="meeting.accept" text="meeting.accept not found" />';
var rejectText = '<spring:message code="meeting.reject" text="meeting.reject not found" />';
var cancelText = '<spring:message code="meeting.cancel" text="meeting.cancel not found" />';

$(document).ready(function() {
	orderAllOptions();
	if($("#selectTeacher").val()!= null && $("#selectTeacher").val() != ""){
		loadData($("#selectTeacher").val());
	} else if($("#selectParent").val()!= null && $("#selectParent").val() != ""){
		loadData($("#selectParent").val());
	}
});

$("#selectTeacher").change(function(){
	if($("#selectTeacher").val()!= null && $("#selectTeacher").val() != ""){
		if($("#selectParent").val()!= null && $("#selectParent").val() != ""){
			$("#selectParent").val("")
		}
		loadData($("#selectTeacher").val());
	}
});
$("#selectParent").change(function(){
	if($("#selectParent").val()!= null && $("#selectParent").val() != ""){
		if($("#selectTeacher").val()!= null && $("#selectTeacher").val() != ""){
			$("#selectTeacher").val("")
		}
		loadData($("#selectParent").val());
	}
});

function loadData(id){
	idUser = id;
};

function createTableMeetings(){
	table = $("#tableMeetings").DataTable({
    	sDom:'<lrtip>',
        language: {
            "url": '<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />'
        },
        columnDefs: [ {
            "orderable": false,
            "targets": [7]
        } ],
     
        ajax : {
   			url : '${urlSearchUsers}' + idUser,
   			type : "GET",
   			dataSrc : function ( json ) {
   	     		if(json != null && json.length > 0){
   		   	    	for (var i = 0; i < json.length; i++) {
   	     	    		json[i].date = moment(json[i].date).format("DD/MM/YYYY");
   	     	    		json[i].type = '<spring:message code="meeting.type.' + json[i].type + '" text="meeting.type.' + json[i].type + ' not found" />';
   		   	    		json[i].student = json[i].student.surname1 + " " + json[i].student.surname2 + ", " + json[i].student.name;
   		   	    		json[i].group = json[i].group.course.name + " - " + json[i].group.letter;
   		   	    		json[i].status = '<spring:message code="meeting.status.' + json[i].status + '" text="meeting.status.' + json[i].status + ' not found" />';

   		   	    		json[i].operations = '<div class="text-center">';
   	     	    		if(json[i].user == idUser){
   	     	    			json[i].operations = '<div class="text-center"><label class="cursorPointer iconTable" onclick="showAttendee(\''+ json[i].id + '\')"><i class="glyphicon glyphicon-info-sign" title="'+ attendeesText + '"> </i></label>';
	   	     	    		if(role != "ROLE_ADM"){
	   	     	    			json[i].operations += '<label class="cursorPointer iconTable" onclick="confirmCancel(\'' + json[i].id + '\')"><i class="glyphicon glyphicon-remove-circle" title="'+ cancelText + '"> </i></label>';
   		     	    		}
     		    		}
   	     	    		json[i].operations += '</div>';
   	     	    	}
   	     		}
   	 	      	return json;
   	 	    }
   		},
   	   	columns : [
            { "data": "id", "width":"5%"},
            { "data": "date", "width":"10%"},
            { "data": "hour", "width":"10%"},
       		{ "data": "type", "width":"5%" },
      		{ "data": "student", "width":"10%"},
      		{ "data": "group", "width":"10%"},
      		{ "data": "user", "width":"10%"},	         		
      		{ "data": "status", "width":"5%"},	         		
      		{ "data": "comments", "width":"20%"},	         		
      		{ "data": "operations", "width":"5%"},	         		
  		],
        initComplete: function(){
        	 table.columns().every( function () {
        		 if(this.index() != 9){
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
}

function showAttendee(meeting){
	
}

function confirmCancel(meeting){
	
}

function response(meeting, state){
	
}

} else {
		json[i].operations += '<label class="cursorPointer iconTable green" onclick="response(\'' + json[i].id + '\',\'A\')"><i class="glyphicon glyphicon-ok" title="'+ acceptText + '"> </i></label>';
		json[i].operations += '<label class="cursorPointer iconTable red" onclick="response(\'' + json[i].id + '\',\'A\')"><i class="glyphicon glyphicon-remove" title="'+ rejectText + '"> </i></label>';
		
	}

</script>
