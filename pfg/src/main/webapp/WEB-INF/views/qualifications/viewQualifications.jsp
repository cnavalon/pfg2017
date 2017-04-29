<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/qualifications/getQualificationsStudent/" var="urlQualifications" />


<div class="row-fluid">
	<h3 class="title"><spring:message code="qualifications.title" text="qualifications.title not found" /></h3>
</div>

<form class="form-horizontal" action="">
	<div class="row-fluid" id="divSelect">
		<div class="form-group">
			<label class="col-sm-2 control-label"><spring:message code="qualifications.student" text="qualifications.student not found"/></label>
			<div class="col-sm-3">
				<select class="form-control" id="selectStudent">
					<c:forEach items="${lstStudents}" var="student">
						<option value="${student.id}" label="${student.fullName}">${student.fullName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="col-sm-offset-1 col-sm-10">
			<table id="tableQualifications" class="stripe hover row-border" width="100%">
				<thead>
			  		<tr>
			    		<th><spring:message code="qualifications.subject" text="qualifications.subject not found" /></th>
			    		<th><spring:message code="qualifications.1T" text="qualifications.1T not found" /></th>
			    		<th><spring:message code="qualifications.2T" text="qualifications.2T not found" /></th>
			    		<th><spring:message code="qualifications.3T" text="qualifications.3T not found" /></th>
			    		<th><spring:message code="qualifications.final" text="qualifications.final not found" /></th>
		    		</tr>
			  	</thead>
			  	<tbody>
			  	</tbody>
			</table>
		</div>
	</div>
	
</form>


<script>
var tableQualifications = null;

$(document).ready(function() {
	orderAllOptions();
	$("#selectStudent").val($("#selectStudent option:first").val());
	if($("#selectStudent").val() != null && $("#selectStudent").val() != ""){
		loadTable();
	}
});

$("#selectStudent").change(function(){
	if($("#selectStudent").val() != null && $("#selectStudent").val() != ""){
		loadTable();
	} else {
		tableQualifications.clear();
	}
});

function loadTable(){
	if(tableQualifications == null)
		createTable();
	else
		reloadTable();
}

function createTable(){
	tableQualifications = $("#tableQualifications").DataTable({
    	sDom:'<lrtip>',
        language: {
            "url": '<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />'
        },
        "paging":   false,
        "searching": false,
        "info":     false,
        "fixedHeader": {
            header: true,
            footer: false
        },
        "autoWidth": false,
        "order": [[ 0, "asc" ]],
     
        ajax : {
   			url : '${urlQualifications}' + $("#selectStudent").val(),
   			type : "GET",
   			dataSrc : function ( json ) {
   	     		if(json != null && json.length > 0){
   		   	    	for (var i = 0; i < json.length; i++) {
   	     	    		json[i].name = json[i].surname1 + " " + json[i].surname2 + ", " + json[i].name;
     	    			json[i].q1 = '<input type="text" id="inputQ1" class="form-control" maxlength="20">';
     	    			json[i].q2 = '<input type="text" id="inputQ2" class="form-control" maxlength="20">';
     	    			json[i].q3 = '<input type="text" id="inputQ3" class="form-control" maxlength="20">';
     	    			json[i].qF = '<input type="text" id="inputQF" class="form-control" maxlength="20">';
   	     	    	}
   	     		}
   	 	      	return json;
   	 	    }
   		},
   	   	columns : [
            { "data": "subject.name", "width":"40%"},
            { "data": "qualification.qualification1", "width":"15%", "className": "text-center" },
       		{ "data": "qualification.qualification2", "width":"15%", "className": "text-center" },
      		{ "data": "qualification.qualification3", "width":"15%", "className": "text-center" },
      		{ "data": "qualification.qualificationF", "width":"15%", "className": "text-center" }
  		],
    });
} 

function reloadTable(){
	tableQualifications.ajax.url('${urlQualifications}' + $("#selectStudent").val()).load();
}


</script>