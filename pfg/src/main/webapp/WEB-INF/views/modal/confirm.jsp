<%@ include file="/WEB-INF/views/common/include.jsp" %>

<div id="dataConfirmModal" class="modal fade" role="dialog" aria-labelledby="dataConfirmLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false"> 
	<div class="modal-dialog"> 
		<div class="modal-content"> 
	 		<div class="modal-header"> 		        			
	 			<h3 id="dataConfirmLabel"><spring:message code="common.warning" text="common.warning not found"/></h3> 
	 		</div> 
	 		<div id="dataConfirmBody" class="modal-body">${text}</div>  
	 		<div class="modal-footer"> 
	 			<a class="btn btn-primary" id="dataCancel"><spring:message code="common.cancel" text="common.cancel not found" /></a> 
	 			<a class="btn btn-primary" id="dataConfirmOK"><spring:message code="common.ok" text="common.ok not found" /></a> 
	 		</div> 
	 	</div> 
 	</div> 
</div>