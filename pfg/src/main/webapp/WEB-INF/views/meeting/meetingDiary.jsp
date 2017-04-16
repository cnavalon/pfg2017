<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/meetings/atp/getMeeting/" var="urlMeeting" />
<spring:url value="/meetings/atp/getActiveMeeting/" var="urlActiveMeeting" />
<spring:url value="/meetings/atp/getRequest/" var="urlRequest" />
<spring:url value="/meetings/atp/getActiveRequest/" var="urlActiveRequest" />
<spring:url value="/meetings/atp/cancelMeeting/" var="urlCancelMeeting" />
<spring:url value="/meetings/tut/responseModal/" var="urlResponseModal" />
<spring:url value="/meetings/tut/responseMeeting?${_csrf.parameterName}=${_csrf.token}" var="urlResponseMeeting" />
<spring:url value="/meetings/atp/modalAttendee/" var="urlModalAttendee" />

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

<div class="row-fluid" id="divTableRequest" hidden>
	<h4><b><spring:message code="meeting.request" text="meeting.request not found"/></b></h4>
	<div class="col-sm-12">
		<table id="tableRequest" class="stripe hover row-border" width="95%">
			<thead>
		  		<tr>
		    		<th><spring:message code="meeting.id" text="meeting.id not found" /></th>
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
		  	</thead>
		  	<tfoot>
			  	<tr>
		    		<th><spring:message code="meeting.id" text="meeting.id not found" /></th>
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

<div class="row-fluid" id="divTableMeetings" hidden>
	<h4><b><spring:message code="meeting.list" text="meeting.list not found"/></b></h4>
	<div class="col-sm-12">
		<table id="tableMeetings" class="stripe hover row-border" width="95%">
			<thead>
		  		<tr>
		    		<th><spring:message code="meeting.id" text="meeting.id not found" /></th>
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
		  	</thead>
		  	<tfoot>
			  	<tr>
		    		<th><spring:message code="meeting.id" text="meeting.id not found" /></th>
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
var urlMeeting = '${urlActiveMeeting}';
var urlRequest = '${urlActiveRequest}';
var role = '${sessionScope.sessionUserRole.idRole}';

var tableMeetings = null;
var tableRequest = null;

var commentResponse = null;

//Textos
var attendeesText = '<spring:message code="meeting.attendees" text="meeting.attendees not found" />';
var acceptText = '<spring:message code="meeting.accept" text="meeting.accept not found" />';
var rejectText = '<spring:message code="meeting.reject" text="meeting.reject not found" />';
var cancelText = '<spring:message code="meeting.cancel" text="meeting.cancel not found" />';
var typeT_Text = '<spring:message code="meeting.type.T" text="meeting.type.T not found" />'; 
var typeP_Text = '<spring:message code="meeting.type.P" text="meeting.type.P not found" />'; 
var statusP_Text = '<spring:message code="meeting.status.P" text="meeting.status.P not found" />';
var statusA_Text = '<spring:message code="meeting.status.A" text="meeting.status.A not found" />';
var statusC_Text = '<spring:message code="meeting.status.C" text="meeting.status.C not found" />';
var statusR_Text = '<spring:message code="meeting.status.R" text="meeting.status.R not found" />';

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
	if(idUser != null && idUser != ""){
		if(tableMeetings == null){
			createTables();
		}else {
			reloadTables();
		}
		
	}
};

function createTables(){
	$.fn.dataTable.moment( 'DD/MM/YYYY' );
	createTableMeetings();
	$("#divTableMeetings").show();
	createTableRequest();
	$("#divTableRequest").show();
}

function reloadTables(){
	tableMeetings.ajax.url(urlMeeting + idUser).load();
	tableRequest.ajax.url(urlRequest + idUser).load();
}

function createTableMeetings(){
	tableMeetings = $("#tableMeetings").DataTable({
    	sDom:'<lrtip>',
        language: {
            "url": '<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />'
        },
        columnDefs: [ {
            "orderable": false,
            "targets": [9]
        } ],
        "autoWidth": false,
        "order": [[ 1, "asc" ], [ 2, "asc" ]],
        ajax : {
   			url : urlMeeting + idUser,
   			type : "GET",
   			dataSrc : function ( json ) {
   	     		if(json != null && json.length > 0){
   		   	    	for (var i = 0; i < json.length; i++) {
   	     	    		json[i].dateStr = moment(json[i].date).format("DD/MM/YYYY");
   	     	    		json[i].typeStr = getType(json[i].type);
   		   	    		json[i].student = getStudent(json[i].student);
   		   	    		json[i].group = json[i].group.course.name + " - " + json[i].group.letter;
   		   	    		json[i].statusStr = getStatus(json[i].status);
						var person = json[i].person;
   		   	    		json[i].userName = person.surname1 + " " + person.surname2 + ", " + person.name;
   		   	    		
   		   	    		json[i].operations = '<div class="text-center">';
   		   	    		if(role === "ROLE_ADM" || role === "ROLE_TCH" || json[i].user == idUser){
   	     	    			json[i].operations = '<div class="text-center"><label class="cursorPointer iconTable" onclick="showAttendee(\''+ json[i].id + '\')"><i class="glyphicon glyphicon-info-sign" title="'+ attendeesText + '"> </i></label>';
   	     	    			if(json[i].status == "A" || json[i].status == "P")
   	     	    				json[i].operations += '<label class="cursorPointer iconTable" onclick="confirmCancel(\'' + json[i].id + '\')"><i class="glyphicon glyphicon-remove-circle" title="'+ cancelText + '"> </i></label>';
     		    		}
   	     	    		json[i].operations += '</div>';
   	     	    	}
   	     		}
   	 	      	return json;
   	 	    }
   		},
   	   	columns : [
            { "data": "id", "width":"2%"},
            { "data": "dateStr", "width":"7%"},
            { "data": "hour", "width":"8%"},
       		{ "data": "typeStr", "width":"8%" },
      		{ "data": "student", "width":"15%"},
      		{ "data": "group", "width":"7%"},
      		{ "data": "userName", "width":"15%"},	         		
      		{ "data": "statusStr", "width":"7%"},	         		
      		{ "data": "comments", "width":"16%"},	         		
      		{ "data": "operations", "width":"5%"},	         		
  		],
  		
        initComplete: function(){
        	tableMeetings.columns().every( function () {
       			 var column = this;
        		 if(this.index() != 3 && this.index() != 7 && this.index() != 9){
	       			 var that = this;
				     var title = $(column.footer()).text();
				     $(column.footer()).html( '<input type="text" class="filterTables" placeholder="'+title+'"/>' );
				     $( 'input', this.footer() ).on('keyup change', function () {
				    	 if ( that.search() !== this.value ) {
				    		 that.search( this.value ).draw();
				    	 }
				     } );
        		 }
        		 if(this.index() == 3 || this.index() == 7){
	        		 var select = $('<select style="width: 100%;"><option value=""></option></select>')
	                 .appendTo( $(column.footer()).empty() )
	                 .on( 'change', function () {
	                     var val = $.fn.dataTable.util.escapeRegex(
	                         $(this).val()
	                     );
	
	                     column
	                         .search( val ? '^'+val+'$' : '', true, false )
	                         .draw();
	                 } );
	
	        		 if(this.index() == 3){
                		select.append( '<option value="'+typeT_Text+'">'+typeT_Text+'</option>' );
                		select.append( '<option value="'+typeP_Text+'">'+typeP_Text+'</option>' );
	        		 } else {
	        			select.append( '<option value="'+statusP_Text+'">'+statusP_Text+'</option>' );
                		select.append( '<option value="'+statusA_Text+'">'+statusA_Text+'</option>' );
                		select.append( '<option value="'+statusR_Text+'">'+statusR_Text+'</option>' );
                		select.append( '<option value="'+statusC_Text+'">'+statusC_Text+'</option>' );
	        		 }
        			 
        		 }
        		 
        		 
       	  	} ); 
    		$("#tableMeetings tfoot tr").insertBefore($("#tableMeetings thead tr"));
		}
    });
}

function createTableRequest(){
	tableRequest = $("#tableRequest").DataTable({
    	sDom:'<lrtip>',
        language: {
            "url": '<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />'
        },
        columnDefs: [ {
            "orderable": false,
            "targets": [9]
        } ],
        "autoWidth": false,
        "order": [[ 1, "asc" ], [ 2, "asc" ]],
        ajax : {
   			url : urlRequest + idUser,
   			type : "GET",
   			dataSrc : function ( json ) {
   	     		if(json != null && json.length > 0){
   		   	    	for (var i = 0; i < json.length; i++) {
   	     	    		json[i].dateStr = moment(json[i].date).format("DD/MM/YYYY");
   	     	    		json[i].type = getType(json[i].type);
   		   	    		json[i].student = getStudent(json[i].student);
   		   	    		json[i].group = json[i].group.course.name + " - " + json[i].group.letter;
   		   	    		json[i].statusStr = getStatus(json[i].status);
						var person = json[i].person;
   		   	    		json[i].userName = person.surname1 + " " + person.surname2 + ", " + person.name;
   		   	    		
   		   	    		json[i].operations = '<div class="text-center">';
   		   	    		if(role != "ROLE_ADM"){
   		   	    			if(moment(json[i].date) >= moment()){
	   		   	    			json[i].operations += '<label class="cursorPointer iconTable green" onclick="response(\'' + json[i].id + '\',\'A\')"><i class="glyphicon glyphicon-ok" title="'+ acceptText + '"> </i></label>';
	   		   					json[i].operations += '<label class="cursorPointer iconTable red" onclick="response(\'' + json[i].id + '\',\'R\')"><i class="glyphicon glyphicon-remove" title="'+ rejectText + '"> </i></label>';
   		   	    			}
     		    		}
   	     	    		json[i].operations += '</div>';
   	     	    	}
   	     		}
   	 	      	return json;
   	 	    }
   		},
   	   	columns : [
            { "data": "id", "width":"2%"},
            { "data": "dateStr", "width":"7%"},
            { "data": "hour", "width":"8%"},
       		{ "data": "type", "width":"8%" },
      		{ "data": "student", "width":"15%"},
      		{ "data": "group", "width":"7%"},
      		{ "data": "userName", "width":"15%"},	         		
      		{ "data": "statusStr", "width":"7%"},	         		
      		{ "data": "comments", "width":"16%"},	         		
      		{ "data": "operations", "width":"5%"},	         		
  		],
  		
        initComplete: function(){
        	tableRequest.columns().every( function () {
       			 var column = this;
        		 if(this.index() != 3 && this.index() != 7 && this.index() != 9){
	       			 var that = this;
				     var title = $(column.footer()).text();
				     $(column.footer()).html( '<input type="text" class="filterTables" placeholder="'+title+'"/>' );
				     $( 'input', this.footer() ).on('keyup change', function () {
				    	 if ( that.search() !== this.value ) {
				    		 that.search( this.value ).draw();
				    	 }
				     } );
        		 }
        		 if(this.index() == 3 || this.index() == 7){
	        		 var select = $('<select style="width: 100%;"><option value=""></option></select>')
	                 .appendTo( $(column.footer()).empty() )
	                 .on( 'change', function () {
	                     var val = $.fn.dataTable.util.escapeRegex(
	                         $(this).val()
	                     );
	
	                     column
	                         .search( val ? '^'+val+'$' : '', true, false )
	                         .draw();
	                 } );
					
	        		 if(this.index() == 3){
                		select.append( '<option value="'+typeT_Text+'">'+typeT_Text+'</option>' );
                		select.append( '<option value="'+typeP_Text+'">'+typeP_Text+'</option>' );
	        		 } else {
	        			select.append( '<option value="'+statusP_Text+'">'+statusP_Text+'</option>' );
                		select.append( '<option value="'+statusA_Text+'">'+statusA_Text+'</option>' );
                		select.append( '<option value="'+statusR_Text+'">'+statusR_Text+'</option>' );
                		select.append( '<option value="'+statusC_Text+'">'+statusC_Text+'</option>' );
	        		 }
        			 
        		 }
        		 
        		 
       	  	} ); 
    		$("#tableRequest tfoot tr").insertBefore($("#tableRequest thead tr"));
		}
    });
}

function getType(type){
	if(type === 'T'){
		return typeT_Text;
	} else if(type === 'P'){
		return typeP_Text;
	} else {
		return "";
	}
}

function getStatus(status){
	if(status === 'P'){
		return '<i class="glyphicon glyphicon-time"> </i> ' + statusP_Text;
	} else if(status === 'A'){
		return '<i class="glyphicon glyphicon-ok green"> </i> ' + statusA_Text;
	} else if(status === 'R'){
		return '<i class="glyphicon glyphicon-remove red"> </i> ' + statusR_Text;
	} else if(status === 'C'){
		return '<i class="glyphicon glyphicon-remove-circle"> </i> ' + statusC_Text;
	} else {
		return "";
	}
}

function getStudent(student){
	if(student == null || student == ""){
		return "";
	} else {
		return student.surname1 + " " + student.surname2 + ", " + student.name;
	}
}

function showAttendee(meeting){
	modal("${urlModalAttendee}" + meeting,null,null);
}

function confirmCancel(meeting){
	confirm('<spring:message code="meeting.cancel.quest" text="meeting.cancel.quest"/>', function(){cancel(meeting);}, null);
}

function cancel(meeting){
	$.ajax({
		url : '${urlCancelMeeting}' + meeting,
		type: "GET", 
		success : function(text) {
			alert(text, reload);
		},
		error: function(){
			alert('<spring:message code="meeting.cancel.error" text="meeting.cancel.error"/>',reload);
		}
	});		
}

function response(meeting, state){
	commentResponse = null;
	modal('${urlResponseModal}' + state, function(){sendResponse(meeting, state);}, null);
}

function sendResponse(meeting, state){
	data = {
			meeting : meeting,
			state : state,
			comment : commentResponse
	}
	$.ajax({
		url : '${urlResponseMeeting}',
		type: "POST", 
		data : data,
		success : function(text) {
			alert(text, reload);
		},
		error: function(){
			alert('<spring:message code="meeting.response.error" text="meeting.response.error"/>',reload);
		}
	});		
}



</script>
