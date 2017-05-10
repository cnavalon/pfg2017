<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/schoolCanteen/ap/paymentData/" var="urlPaymentData" />
<spring:url value="/schoolCanteen/par/payModal/" var="urlPayModal" />
<spring:url value="/schoolCanteen/par/pay?${_csrf.parameterName}=${_csrf.token}" var="urlPay" />
<spring:url value="/schoolCanteen/par/receipt" var="urlReceipt" />


<div class="row-fluid">
	<h3 class="title"><spring:message code="payments.title" text="payments.title not found" /></h3>
</div>

<form class="form-horizontal" action="">
	<div class="row-fluid" id="divSelect">
		<div class="form-group">
			<label class="col-sm-2 control-label"><spring:message code="payments.student" text="payments.student not found"/></label>
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
			<table id="tablePayments" class="stripe hover row-border" width="100%">
				<thead>
			  		<tr>
			    		<th></th>
			    		<th><spring:message code="payments.month" text="payments.month not found" /></th>
			    		<th><spring:message code="payments.amount" text="payments.amount not found" /></th>
			    		<th><spring:message code="payments.state" text="payments.state not found" /></th>
			    		<th></th>
		    		</tr>
			  	</thead>
			  	<tbody>
			  	</tbody>
			</table>
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-sm-2 control-label"><spring:message code="payments.totalPaid" text="payments.totalPaid not found"/>: </label>
		<div id="divTotalPaid" class="col-sm-3 green aling-input"><b><span id="spanTotalPaid"></span> <spring:message code="payments.unit" text="payments.unit not found"/></b></div>
		
		<label class="col-sm-2 control-label"><spring:message code="payments.totalPending" text="payments.totalPending not found"/>: </label>
		<div id="divTotalPending" class="col-sm-3 red aling-input"><b><span id="spanTotalPending"></span> <spring:message code="payments.unit" text="payments.unit not found"/></b></div>
	</div>
	
</form>


<script>
var tablePayments = null;
moment.locale("${locale}");
var role = '${sessionScope.sessionUserRole.idRole}'
var totalPaid = 0;
var totalPending = 0;

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
		tablePayments.clear();
	}
});

function loadTable(){
	if(tablePayments == null)
		createTable();
	else
		reloadTable();
}

function createTable(){
	tablePayments = $("#tablePayments").DataTable({
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
   			url : '${urlPaymentData}' + $("#selectStudent").val(),
   			type : "GET",
   			dataSrc : function ( json ) {
   	     		if(json != null && json.length > 0){
   	     			totalPaid = 0;
   	     			totalPending = 0;
   		   	    	for (var i = 0; i < json.length; i++) {
   	     	    		json[i].order = (json[i].month + 4) % 12;
   	     	    		json[i].monthName = capitalize(moment().month(json[i].month).format("MMMM"));
     	    			json[i].amountUnit = json[i].amount + ' ' + '<spring:message code="payments.unit" text="payments.unit not found" />';
     	    			if(json[i].id == null){
     	    				json[i].state = '<span class="red"><spring:message code="payments.pending" text="payments.pending not found" /></span>';
     	    				totalPending += json[i].amount;
     	    			} else {
     	    				json[i].state = '<span class="green"><spring:message code="payments.paid" text="payments.paid not found" /></span>';
     	    				totalPaid += json[i].amount;
     	    			}
     	    			
     	    			if (role === "ROLE_PAR") {
     	    				if(json[i].id == null){
         	    				json[i].operations = '<label id="pay" class="cursorPointer iconTable" onclick="pay('+ json[i].month + ',' + json[i].amount + ')"><i class="glyphicon glyphicon-credit-card" title="'+ '<spring:message code="payments.pay" text="payments.pay not found" />' + '"> </i></label>';
         	    			} else {
         	    				json[i].operations = '<label id="receipt" class="cursorPointer iconTable" onclick="generateReceipt('+ json[i].id + ')"><i class="glyphicon glyphicon-print" title="'+ '<spring:message code="payments.receipt" text="payments.receipt not found" />' + '"> </i></label>';
         	    			}
     	    			} else {
     	    				json[i].operations = "";
     	    			}
   	     	    	}
   		   	  		$("#spanTotalPaid").text(totalPaid);
   					$("#spanTotalPending").text(totalPending);
   	     		}
   	 	      	return json;
   	 	    }
   		},
   	   	columns : [
            { "data": "order", "visible":false},
            { "data": "monthName", "orderable": false, "width":"40%" },
       		{ "data": "amountUnit", "orderable": false, "width":"20%", "className": "text-center" },
      		{ "data": "state", "orderable": false, "width":"30%", "className": "text-center" },
      		{ "data": "operations",  "orderable": false, "width":"10%", "className": "text-center" }
  		],
     
    });
} 

function reloadTable(){
	tablePayments.ajax.url('${urlPaymentData}' + $("#selectStudent").val()).load();
}


function pay(month, amount){
	modal("${urlPayModal}" + month + "/" + amount, function(){savePay(month, amount)}, null);
}

function savePay(month, amount){
	var payment = {
			student : $("#selectStudent").val(),
			month : month,
			amount : amount,
	}
	
	$.ajax({
		url : "${urlPay}",
		type:"POST", 
		data: JSON.stringify(payment),
		contentType: "application/json",
		success : function(text) {
			alert(text,loadTable);
		},
		error: function(){
			alert('<spring:message code="payments.pay.error" text="payments.pay.error not found"/>', loadTable);
		}
	});			
}

function generateReceipt(id){
	var parameters = {};
	$(parameters).attr("${_csrf.parameterName}","${_csrf.token}");
	$(parameters).attr("id",id);
	post("${urlReceipt}", parameters);
}
</script>