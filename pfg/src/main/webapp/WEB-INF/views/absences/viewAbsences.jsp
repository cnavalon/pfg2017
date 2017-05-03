<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/absences/getAbsencesStudent/" var="urlAbsences" />


<div class="row-fluid">
	<h3 class="title"><spring:message code="absences.title" text="absences.title not found" /></h3>
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
			<table id="tableAbsences" class="stripe hover row-border" width="100%">
				<thead>
			  		<tr>
			    		<th><spring:message code="absences.day" text="absences.day not found" /></th>
			    		<th><spring:message code="absences.hour" text="absences.hour not found" /></th>
			    		<th><spring:message code="absences.subject" text="absences.subject not found" /></th>
		    		</tr>
			  	</thead>
			  	<tbody>
			  	</tbody>
			  	<tfoot>
			  		<tr>
			    		<th><spring:message code="absences.day" text="absences.day not found" /></th>
			    		<th><spring:message code="absences.hour" text="absences.hour not found" /></th>
			    		<th><spring:message code="absences.subject" text="absences.subject not found" /></th>
		    		</tr>
			  	</tfoot>
			</table>
		</div>
	</div>
	
</form>


<script>
var tableAbsences = null;

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
		tableAbsences.clear();
	}
});

function loadTable(){
	if(tableAbsences == null)
		createTable();
	else
		reloadTable();
}

function createTable(){
	$.fn.dataTable.moment( 'DD/MM/YYYY' );
	$.fn.dataTable.moment( 'H:mm' );
	tableAbsences = $("#tableAbsences").DataTable({
    	sDom:'<lrtip>',
        language: {
            "url": '<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />'
        },
        "order": [[ 0, "desc" ]],
        ajax : {
   			url : "${urlAbsences}" + $("#selectStudent").val(),
   			type : "GET",
   			dataSrc : function ( json ) {
   	     		if(json != null && json.length > 0){
   		   	    	for (var i = 0; i < json.length; i++) {
   		   	    		json[i].showHour = json[i].schedule.initHour + " - " + json[i].schedule.endHour; 
   	     	    		json[i].day = moment(json[i].day).format("DD/MM/YYYY");
   	     	    	}
   	     		}
   	 	      	return json;
   	 	    }
   		},
   		"autoWidth": false,
   	   	columns : [
            { "data": "day", "width":"30%"},
            { "data": "showHour", "width":"30%"},
            { "data": "subject.name", "width":"40%"},
  		],
        initComplete: function(){
        	tableAbsences.columns().every( function () {
       			 var column = this;
       			 var that = this;
			     var title = $(column.footer()).text();
			     $(column.footer()).html( '<input type="text" class="filterTables" placeholder="'+title+'"/>' );
			     $( 'input', this.footer() ).on('keyup change', function () {
			    	 if ( that.search() !== this.value ) {
			    		 that.search( this.value ).draw();
			    	 }
			     } );
       	  	} ); 
    		$("#tableAbsences tfoot tr").insertBefore($("#tableAbsences thead tr"));
		}
    });
} 

function reloadTable(){
	tableAbsences.ajax.url('${urlAbsences}' + $("#selectStudent").val()).load();
}


</script>