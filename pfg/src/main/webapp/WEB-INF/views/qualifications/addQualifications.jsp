<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/qualifications/emp/getSubjects/" var="urlSubjects" />
<spring:url value="/qualifications/emp/getStudents/" var="urlStudents" />
<spring:url value="/qualifications/emp/getQualificationsGroup/" var="urlQualifications" />
<spring:url value="/qualifications/emp/saveQualification?${_csrf.parameterName}=${_csrf.token}" var="urlSaveQualifications" />


<div class="row-fluid">
	<h3 class="title"><spring:message code="qualifications.title" text="qualifications.title not found" /></h3>
</div>

<form class="form-horizontal" action="">
	<div class="row-fluid" id="divSelect">
		<div class="form-group">
			<label class="col-sm-2 control-label"><spring:message code="qualifications.group" text="qualifications.group not found"/></label>
			<div class="col-sm-3">
				<select class="form-control" id="selectGroup">
					<c:forEach items="${lstGroups}" var="group">
						<option value="${group.id}" label="${group.course.name} ${group.letter}">${group.course.name} ${group.letter}</option>
					</c:forEach>
				</select>
			</div>
			<label class="col-sm-2 control-label"><spring:message code="qualifications.subject" text="qualifications.subject not found"/></label>
			<div class="col-sm-3">
				<select class="form-control" id="selecSubject">
				</select>
			</div>
		</div>
	</div>
	<fieldset id="fieldsetTable" disabled>
		<div class="row-fluid">
			<div class="col-sm-offset-1 col-sm-10">
				<table id="tableQualifications" class="stripe hover row-border" width="100%">
					<thead>
				  		<tr>
				    		<th></th>
				    		<th></th>
				    		<th><spring:message code="qualifications.student" text="qualifications.student not found" /></th>
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
	</fieldset>
	
	<div class="form-group spaced">
    	<div class="col-sm-offset-4 col-sm-7 text-right">
    		<div class="col-sm-8"></div>
   			<div class="col-sm-4">
	      		<button id ="buttonAdd" type="button" class="btn btn-block btn-default" disabled><spring:message code="common.save" text="common.save not found"/></button>
    		</div>
   		</div>
	</div>
</form>


<script>
var tableQualifications = null;
var group = $("#selectGroup").val();
var subject = null;

$(document).ready(function() {
	if(group != null && group != ""){
		loadSubjects();
		loadTable();
	}
});

$("#selectGroup").change(function(){
	group = $("#selectGroup").val();
	subject = null;
	if(group != null && group != ""){
		loadSubjects();
		loadTable();
	} else {
		tableQualifications.clear();
		$("#selecSubject").empty();
	}
	checkSave();
});

$("#selecSubject").change(function(){
	subject = $("#selecSubject").val();
	if(subject != null && subject != ""){
		getQualifications();
	}
	checkSave();
});

function loadSubjects(){
	$.ajax({
		url : "${urlSubjects}" + group,
		type:"GET", 
		success : function(lstSubjects) {
			if(lstSubjects != null && lstSubjects.length > 0){
				$("#selecSubject").empty();
				$("#selecSubject").append('<option value="" label=""></option>');
				for(var i=0; i < lstSubjects.length; i++){
					var subject = lstSubjects[i];
					var option = '<option value="' + subject.id + '" label="' + subject.name + '">' + subject.name +'</option>';
					$("#selecSubject").append(option);
				}
				orderOptions("#selecSubject");
			}
		},
		error: function(){
			alert('<spring:message code="news.subjects.error" text="news.subjects.error not found"/>', null);
		}
	});		
}

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
        "order": [[ 2, "asc" ]],
     
        ajax : {
   			url : '${urlStudents}' + $("#selectGroup").val(),
   			type : "GET",
   			dataSrc : function ( json ) {
   	     		if(json != null && json.length > 0){
   		   	    	for (var i = 0; i < json.length; i++) {
   	     	    		json[i].name = json[i].surname1 + " " + json[i].surname2 + ", " + json[i].name;
   	     	    		json[i].idQualification = json[i].surname1 + " " + json[i].surname2 + ", " + json[i].name;
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
            { "data": "id", "visible": false},
            { "data": "idQualification", "visible": false},
            { "data": "name", "width":"40%"},
            { "data": "q1", "width":"15%", "orderable": false},
       		{ "data": "q2", "width":"15%", "orderable": false},
      		{ "data": "q3", "width":"15%", "orderable": false},
      		{ "data": "qF", "width":"15%", "orderable": false}
  		],
    });
} 

function reloadTable(){
	tableQualifications.ajax.url('${urlStudents}' + $("#selectGroup").val()).load();
}

function getQualifications(){
	$.ajax({
		url : "${urlQualifications}" + group + "/" + subject,
		type:"GET", 
		success : function(lstQualifications) {
			if(lstQualifications != null && lstQualifications.length > 0){
				for(var i = 0; i < lstQualifications.length; i++){
					updateStudentQualifications(lstQualifications[i]);
				}
			} else {
				clearQualifications();
			}
		},
		error: function(){
			alert('<spring:message code="news.subjects.error" text="news.subjects.error not found"/>', reload);
		}
	});		
}

function updateStudentQualifications(qualification){
	tableQualifications.rows().every( function ( rowIdx, tableLoop, rowLoop ) {
		if(this.data().id == qualification.student){
			this.data().idQualification = qualification.id;
			var node = this.node();
			$(node).find("#inputQ1").val(qualification.qualification1);
			$(node).find("#inputQ2").val(qualification.qualification2);
			$(node).find("#inputQ3").val(qualification.qualification3);
			$(node).find("#inputQF").val(qualification.qualificationF);
			return;
		}
	});
}

function clearQualifications(){
	tableQualifications.rows().every( function ( rowIdx, tableLoop, rowLoop ) {
			var node = this.node();
			this.data().idQualification = "";
			$(node).find("#inputQ1").val("");
			$(node).find("#inputQ2").val("");
			$(node).find("#inputQ3").val("");
			$(node).find("#inputQF").val("");
	});
}

function checkSave(){
	if(group != null && group != "" && subject != null && subject != ""){
		$("#fieldsetTable").removeAttr("disabled");
		$("#buttonAdd").removeAttr("disabled");
	} else {
		$("#fieldsetTable").attr("disabled",true);
		$("#buttonAdd").attr("disabled",true);
	}
}

$("#buttonAdd").click(function(){
	confirm('<spring:message code="qualifications.save.quest" text="qualifications.save.quest not found"/>', saveQualifications, null);
});
	
function saveQualifications(){
	var lstQualifications = [];
	tableQualifications.rows().every( function ( rowIdx, tableLoop, rowLoop ) {
	    var data = this.data();
	    var node = this.node();
	    var qualification = {};
	    qualification.group = parseInt(group);
	    qualification.subject = parseInt(subject);
	    qualification.student = data.id;
    	qualification.qualification1 = $(node).find("#inputQ1").val();
    	qualification.qualification2 = $(node).find("#inputQ2").val();
    	qualification.qualification3 = $(node).find("#inputQ3").val();
    	qualification.qualificationF = $(node).find("#inputQF").val();
    	if(data.idQualification != null && data.idQualification != "")
    		qualification.id = data.idQualification;
    	lstQualifications.push(qualification);
	} );
	
	$.ajax({
		url : "${urlSaveQualifications}",
		type:"POST", 
		data: JSON.stringify(lstQualifications),
		contentType: "application/json",
		success : function(text) {
			alert(text,reload);
		},
		error: function(){
			alert('<spring:message code="absences.save.error" text="absences.save.error not found"/>', reload);
		}
	});			
}	
	

	



</script>