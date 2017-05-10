<%@ include file="/WEB-INF/views/common/include.jsp" %>

<div id="dataModal" class="modal fade" role="dialog" aria-labelledby="dataConfirmLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false"> 
	<div class="modal-dialog"> 
		<div class="modal-content"> 
	 		<div class="modal-header"> 		        			
	 			<h3 id="dataModalLabel"></h3> 
	 		</div> 
	 		<div id="dataModalBody" class="modal-body">
	 			<form id="payForm" class="form-horizontal" method="post" enctype="form-data" action="" >
					
					<div id="divRow0" class="form-group">
						<div id="divHolder">
							<label class="col-sm-5 control-label"><spring:message code="payments.holder" text="payments.holder not found"/>*</label>
							<div class="col-sm-6">
								<input type="text" id="inputHolder" value="${parent.fullName}" class="form-control" onchange="checkSave()">
							</div>
						</div>
					</div>
					
					<div id="divRow1" class="form-group">
						<div id="divCreditCard">
							<label class="col-sm-5 control-label"><spring:message code="payments.creditCard" text="payments.creditCard not found"/>*</label>
							<div class="col-sm-6">
								<input type="text" id="inputCreditCard" class="form-control" onchange="checkSave()" onkeypress="return isNumberKey(event)" maxlength="19">
							</div>
						</div>
					</div>
					
					<div id="divRow2" class="form-group">
						<div id="divValidDate">
							<label class="col-sm-5 control-label"><spring:message code="payments.validDate" text="payments.validDate not found"/>*</label>
							<div class="col-sm-6">
								<div id="validDateAction" class="input-group input-append date">
									<input type='text' class="form-control" id="inputValidDate" onchange="checkSave()">
									<span id="spanValidDate" class="input-group-addon add-on">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
					</div>
					
					<div id="divRow3" class="form-group">
						<div id="divCVC">
							<label class="col-sm-5 control-label"><spring:message code="payments.cvc" text="payments.cvc not found"/>*</label>
							<div class="col-sm-2">
								<input type="text" id="inputCvc" class="form-control" onchange="checkSave()" onkeypress="return isNumberKey(event)" maxlength="3">
							</div>
						</div>
					</div>
					
					<div id="divRow4" class="form-group">
						<div id="divAmount">
							<label class="col-sm-5 control-label"><spring:message code="payments.amount" text="payments.amount not found"/></label>
							<div class="col-sm-6">
								<spring:message code="payments.unit" text="payments.unit not found" var="unitText"/>
								<input type="text" id="inputAmount" value="${amount} ${unitText}"  class="form-control" readonly>
							</div>
						</div>
					</div>
						
				</form>	 		
	 		</div>  
	 		<div class="modal-footer"> 
	 			<button type="button" class="btn btn-primary" id="dataModalCancel"><spring:message code="common.cancel" text="common.cancel not found" /></button> 
	 			<button type="button" class="btn btn-primary" id="dataModalOK" disabled><spring:message code="common.save" text="common.save not found" /></button> 
	 		</div> 
	 	</div> 
 	</div> 
</div>

<script>
$(document).ready(function() {
	var title = '<spring:message code="payments.pay" text="payments.pay not found"/>' + " " + capitalize(moment().month("${month}").format("MMMM"));
	$("#dataModalLabel").text(title);
});

$('#validDateAction').datetimepicker({
    format: 'MM/YYYY',
    locale: '${locale}',
   	minDate: moment()
});

function checkSave(){
	if($("#inputHolder").val().trim() != "" && $("#inputCreditCard").val().trim() != "" && $("#inputValidDate").val().trim() != "" && $("#inputCvc").val().trim() != ""){
		$("#dataModalOK").prop("disabled",false);
	} else {
		$("#dataModalOK").prop("disabled",true);
	}
}

</script>
