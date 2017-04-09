<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/groups/adm/modalSubject" var="urlModalSubject" />
<spring:url value="/groups/adm/deleteSubject/" var="urlDeleteSubject" />
<spring:url value="/groups/adm/saveSubject?${_csrf.parameterName}=${_csrf.token}" var="urlSaveSubject" />

<div class="row-fluid">
	<h3 class="title"><spring:message code="subject.title" text="subject.title not found" /></h3>
</div>

<div class="row-fluid">
	<div class="col-sm-offset-10 col-sm-1">
		<button class="btn btn-primary btn-lg btn-block btn-sm" id="addSubject">
			<i class="glyphicon glyphicon-plus" rel="tooltip"></i> <spring:message code="common.add" text="common.add not found" />
		</button>
	</div>
</div>


<div class="row-fluid" id="divTable">
	<div class="col-sm-offset-1 col-sm-10">
		<table id="tableSubjects" class="stripe hover row-border" width="100%">
			<thead>
		  		<tr>
		    		<th><spring:message code="subject.id" text="subject.id not found" /></th>
		    		<th><spring:message code="subject.code" text="subject.code not found" /></th>
		    		<th><spring:message code="subject.name" text="subject.name not found" /></th>
		    		<th></th>
	    		</tr>
		  	</thead>
		  	<tfoot>
			  	<tr>
			  		<th><spring:message code="subject.id" text="subject.id not found" /></th>
		    		<th><spring:message code="subject.code" text="subject.code not found" /></th>
		    		<th><spring:message code="subject.name" text="subject.name not found" /></th>
		    		<th></th>
			  	</tr>
		  	</tfoot>
		  	<tbody>
		  		<c:forEach items="${lstSubjects}" var="subject">
			  		<tr>
				  		<td>${subject.id}</td>
				  		<td>${subject.code}</td>
				  		<td>${subject.name}</td>
			    		<td>
			    			<div class="text-center">
			    				<label id=editSubject class="cursorPointer iconTable" onclick="editSubject(${subject.id})"><i class="glyphicon glyphicon-pencil" title='<spring:message code="common.edit" text="common.edit not found" />'></i></label>
			    				<label id="deleteSubject" class="cursorPointer iconTable" onclick="confirmDeleteSubject(${subject.id})"><i class="glyphicon glyphicon-trash" title='<spring:message code="common.delete" text="common.delete not found" />'></i></label>
		    				</div>
			    		</td>
				  	</tr>
		  		</c:forEach>
		  	</tbody>
		</table>
	</div>
</div>

<script>
var subject = null;
var tableSubjects = initTable("#tableSubjects", '<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />', [3], [], []);

function editSubject(id){
	modal("${urlModalSubject}" + "/" + id, saveSubject, null);
}

function confirmDeleteSubject(id){
	confirm('<spring:message code="subject.delete.quest" text="subject.delete.quest not found" />', function(){deleteSubject(id);}, null)
}

function deleteSubject(id){
	$.ajax({
		url : "${urlDeleteSubject}" + id,
		type:"GET", 
		success : function(text) {
			alert(text,reload);
		},
		error: function(){
			alert('<spring:message code="subject.delete.error" text="subject.delete.error not found"/>', reload);
		}
	});		
}

$("#addSubject").click(function(){
	modal("${urlModalSubject}", saveSubject, null);
});

function saveSubject(){
	$.ajax({
		url : "${urlSaveSubject}",
		type:"POST", 
		data: JSON.stringify(subject),
		contentType: "application/json",
		success : function(text) {
			alert(text,reload);
		},
		error: function(){
			alert('<spring:message code="subject.save.error" text="subject.save.error not found"/>', reload);
		}
	});			
}

</script>