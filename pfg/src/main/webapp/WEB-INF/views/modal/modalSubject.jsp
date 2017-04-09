<%@ include file="/WEB-INF/views/common/include.jsp" %>

<div id="dataModal" class="modal fade" role="dialog" aria-labelledby="dataConfirmLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false"> 
	<div class="modal-dialog"> 
		<div class="modal-content"> 
	 		<div class="modal-header"> 		        			
	 			<h3 id="dataModalLabel"><spring:message code="subject.modal.title" text="subject.modal.title not found"/></h3> 
	 		</div> 
	 		<div id="dataModalBody" class="modal-body">
	 			<form id="subjectForm" class="form-horizontal" method="post" enctype="form-data" action="" >
	 				<input type="hidden" value="${subject.id}" id="inputId">
					<div id="divRow0" class="form-group">
						<div id="divCode">
							<spring:message code="subject.code" var="subjectCodeText" text="subject.code not found"/>
							<label class="col-sm-3 control-label">${subjectCodeText}</label>
							<div class="col-sm-7">
								<c:choose>
									<c:when test="${empty subject.id}">
										<input type="text" value="${subject.code}" class="form-control" id="inputCode" placeholder="${subjectCodeText}" onkeypress="return isIDKey(event)" maxlength="20">
									</c:when>
									<c:otherwise>
										<input type="text" value="${subject.code}" class="form-control" id="inputCode" disabled>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div id="divRow1" class="form-group">
						<div id="divName">
							<spring:message code="subject.name" var="subjectNameText" text="subject.name not found"/>
							<label class="col-sm-3 control-label">${subjectNameText}</label>
							<div class="col-sm-7">
								<input type="text" value="${subject.name}" class="form-control" id="inputName" placeholder="${subjectNameText}">
							</div>
						</div>
					</div>
				</form>	 		
	 		</div>  
	 		<div class="modal-footer"> 
	 			<button type="button" class="btn btn-primary" id="dataModalCancel"><spring:message code="common.cancel" text="common.cancel not found" /></button> 
	 			<button type="button" class="btn btn-primary" id="dataModalOK"><spring:message code="common.ok" text="common.ok not found" /></button> 
	 		</div> 
	 	</div> 
 	</div> 
</div>

<script>

$(document).ready(function() {
	checkAdd();
});

function checkAdd(){
	if($("#inputCode").val().trim() != "" && $("#inputName").val().trim() != "")
		$("#dataModalOK").prop("disabled", false);
	else
		$("#dataModalOK").prop("disabled",true);
}

$("#inputCode").change(function(){
	checkAdd();
});
$("#inputName").change(function(){
	checkAdd();
});

$("#dataModalOK").click(function(){
	subject = {
			id : $("#inputId").val(),
			code : $("#inputCode").val(),
			name: $("#inputName").val()
	}
});


</script>
