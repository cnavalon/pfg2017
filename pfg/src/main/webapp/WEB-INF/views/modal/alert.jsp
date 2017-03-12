<%@ include file="/WEB-INF/views/common/include.jsp" %>

<div id="dataAlertModal" class="modal fade" role="dialog" aria-labelledby="dataConfirmLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
 		<div class="modal-content">
   			<div class="modal-header">
   				<h3 id="dataAlertLabel"><spring:message code="common.warning" text="common.warning not found"/></h3>
   			</div>
   		<div id="dataAlertBody" class="modal-body">${text}</div> 
   		<div class="modal-footer">
   			<a class="btn btn-primary" id="dataAlertOK"><spring:message code="common.ok" text="common.ok not found"/></a>
   		</div>
   	</div>
   </div>
</div>