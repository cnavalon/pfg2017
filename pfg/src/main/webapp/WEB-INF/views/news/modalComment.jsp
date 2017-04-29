<%@ include file="/WEB-INF/views/common/include.jsp" %>

<div id="dataModal" class="modal fade" role="dialog" aria-labelledby="dataConfirmLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false"> 
	<div class="modal-dialog"> 
		<div class="modal-content"> 
	 		<div class="modal-header"> 		        			
	 			<h3 id="dataModalLabel">${title}</h3> 
	 		</div> 
	 		<div id="dataModalBody" class="modal-body">
	 			<form id="newsForm" class="form-horizontal" method="post" enctype="form-data" action="" >
					<div id="divRow0" class="form-group">
						<div id="divText">
							<spring:message code="news.text" var="textText" text="news.text not found"/>
							<label class="col-sm-3 control-label">${textText}</label>
							<div class="col-sm-7">
								<textarea  rows="10" class="form-control" id="inputText">${comment.text}</textarea>
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

$("#dataModalOK").click(function(){
	comment.text = $("#inputText").val();
});


</script>
