<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/users/adm/saveUser?${_csrf.parameterName}=${_csrf.token}" var="urlSaveUser" />
<spring:url value="/users/adm/list" var="urlListUser" />

<div class="row-fluid">
	<h3 class="title">${title}</h3>
</div>

<div class="row-fluid">
<!-- 	<div class="col-sm-2"></div> -->
	
	<div class="col-sm-12">
		<form:form id="updateForm" class="form-horizontal" method="post" modelAttribute="user" action="${urlUpdActionForm}" >
			<fieldset id="filedsetForm">
				<div id="divIdUser" class="form-group">
					<spring:message code="user.id" var="userIdText" text="user.id not found"/>
					<label class="col-sm-2 control-label">${userIdText}</label>
					<div class="col-sm-3">
						<form:input path="id" type="text" class="form-control" id="inputId" placeholder="${userIdText}"/>
					</div>
					
					<spring:message code="user.name" var="userNameText" text="user.name not found"/>
					<label class="col-sm-2 control-label">${userNameText}</label>
					<div class="col-sm-3">
						<form:input path="name" type="text" class="form-control" id="inputName" placeholder="${userNameText}"/>
					</div>
				</div>
				
				<div id="divSurnames" class="form-group">
					<spring:message code="user.surname1" var="userSurname1Text" text="user.surname1 not found"/>
					<label class="col-sm-2 control-label">${userSurname1Text}</label>
					<div class="col-sm-3">
						<form:input path="surname1" type="text" class="form-control" id="inputSurname1" placeholder="${userSurname1Text}"/>
					</div>
					
					<spring:message code="user.surname2" var="userSurname2Text" text="user.surname2 not found"/>
					<label class="col-sm-2 control-label">${userSurname2Text}</label>
					<div class="col-sm-3">
						<form:input path="surname2" type="text" class="form-control" id="inputSurname2" placeholder="${userSurname2Text}"/>
					</div>
				</div>
				
				<div id="divPassword" class="form-group">
					<spring:message code="user.password" var="userPasswordText" text="user.password not found"/>
					<label class="col-sm-2 control-label">${userPasswordText}</label>
					<div class="col-sm-3">
						<form:input path="password" type="password" class="form-control" id="inputPassword" placeholder="${userPasswordText}"/>
					</div>
					
					<spring:message code="user.confirmPassword" var="userConfirmPasswordText" text="user.confirmPassword not found"/>
					<label class="col-sm-2 control-label">${userConfirmPasswordText}</label>
					<div class="col-sm-3">
						<input type="password" class="form-control" id="inputConfirmPassword" placeholder="${userConfirmPasswordText}"/>
					</div>
					
					<label class="col-sm-2 control-label text-left">
						<span id="spanErrorPassword" class="text-danger"></span>
					</label>
				</div>
				
				<div id="divAddress" class="form-group">
					<spring:message code="user.address" var="userAddressText" text="user.address not found"/>
					<label class="col-sm-2 control-label">${userAddressText}</label>
					<div class="col-sm-8">
						<form:input path="address" type="text" class="form-control" id="inputAddress" placeholder="${userAddressText}"/>
					</div>
				</div>
				
				<div id="divCityProvince" class="form-group">
					<spring:message code="user.city" var="userCityText" text="user.city not found"/>
					<label class="col-sm-2 control-label">${userCityText}</label>
					<div class="col-sm-3">
						<form:input path="city" type="text" class="form-control" id="inputCity" placeholder="${userCityText}"/>
					</div>
					
					<spring:message code="user.province" var="userProvinceText" text="user.province not found"/>
					<label class="col-sm-2 control-label">${userProvinceText}</label>
					<div class="col-sm-3">
						<form:input path="province" type="text" class="form-control" id="inputProvince" placeholder="${userProvinceText}"/>
					</div>
				</div>
				
				<div id="divCpEmail" class="form-group">
					<spring:message code="user.cp" var="userCpText" text="user.cp not found"/>
					<label class="col-sm-2 control-label">${userCpText}</label>
					<div class="col-sm-3">
						<form:input path="cp" type="text" class="form-control" id="inputCp" placeholder="${userCpText}" onkeypress="return isNumberKey(event)"/>
					</div>
					
					<spring:message code="user.email" var="userEmailText" text="user.email not found"/>
					<label class="col-sm-2 control-label">${userEmailText}</label>
					<div class="col-sm-3">
						<form:input path="email" type="email" class="form-control" id="inputEmail" placeholder="${userEmailText}"/>
					</div>
				</div>
				
				<div id="divTelephones" class="form-group">
					<spring:message code="user.telephone1" var="userTelephone1Text" text="user.telephone1 not found"/>
					<label class="col-sm-2 control-label">${userTelephone1Text}</label>
					<div class="col-sm-3">
						<form:input path="telephone1" type="text" class="form-control" id="inputTelephone1" placeholder="${userTelephone1Text}" onkeypress="return isNumberKey(event)"/>
					</div>
					
					<spring:message code="user.telephone2" var="userTelephone2Text" text="user.telephone2 not found"/>
					<label class="col-sm-2 control-label">${userTelephone2Text}</label>
					<div class="col-sm-3">
						<form:input path="telephone2" type="text" class="form-control" id="inputTelephone2" placeholder="${userTelephone2Text}" onkeypress="return isNumberKey(event)"/>
					</div>
				</div>
						
<!-- 			<div class="form-group"> -->
<%-- 				<spring:message code="user.role" var="userRoleText" text="user.role not found"/> --%>
<%-- 				<label class="col-sm-2 control-label">${userRoleText}</label> --%>
<!-- 				<div class="col-sm-8"> -->
<%-- 					<form:select path="roles.role" multiple="true" items="${lstRoles}" itemLabel='<spring:message code="name" text="name not found"/>' itemValue="role"/></td> --%>
<!-- 				</div> -->
<!-- 			</div> -->
			</fieldset>
			<div class="form-group">
		    	<div class="col-sm-offset-4 col-sm-6">
		    		<div class="col-sm-4">
			      		<button id ="buttonAdd" type="submit" class="btn btn-block btn-default"><spring:message code="common.add" text="common.add not found"/></button>
		    		</div>
		    		<div class="col-sm-4">
			      		<button type="reset" class="btn btn-block btn-default"><spring:message code="common.restore" text="common.restore not found"/></button>
		    		</div>
		    		<div class="col-sm-4">
			      		<button class="btn btn-block btn-default"><spring:message code="common.cancel" text="common.cancel not found"/></button>
		    		</div>
	      		</div>
      		</div>
			
		</form:form>
	</div>
</div>

<script>
	
	var roles = {
	    <c:forEach var="role" items="${lstRoles}" varStatus="loop">
	    	'${role.role}': '${role.name}'
	    	${not loop.last ? ',' : ''}
		</c:forEach>
	};
	
	
	$(document).ready(function() {
		$(document).ajaxStart(function() {blockUI();}).ajaxStop(function() {unblockUI();});
		
	} );
	
	$("#inputPassword").change(function(){
		if($("#inputConfirmPassword").val() !== "" || $(this).val() === "")
			checkPass($(this).val(),$("#inputConfirmPassword").val());
	});

	$("#inputConfirmPassword").change(function(){
		checkPass($("#inputPassword").val(),$(this).val());
	});
	
	
	
	function checkPass(pass, confirmPass){
		if(pass === confirmPass){
			$("#spanErrorPassword").text("");
			$("#divPassword").removeClass("has-error");
		} else {
			$("#spanErrorPassword").text('<spring:message code="user.error.password" text="user.error.password not found"/>');
			$("#divPassword").addClass("has-error");
		}
	}

function addUser(){
	var formData = new FormData($('form')[0]);
	formData.append("serialNumber", $("#meterId1").val().trim() + $("#meterId2").val().trim());

	if ($("#instalDate").val().trim() != '')
		formData.append("fechaAlta", $('#instalDateAction').data("DateTimePicker").date());
	
	$.ajax({
		url : "${urlAddActionForm}",
		type:"POST", 
		data: formData,
		async: true,
		cache: false,
		contentType: false,
		processData: false,
		success : function(message) {
			if (message != ''){
					alert("ERROR", message, '<spring:message code="button.ok" text="button.ok not found"/>');
				}
				else{
					createSingleNode()
					alert('<spring:message code="newNode.title" text="newNode.title not found"/>', '<spring:message code="newNode.addOK" text="newNode.addOK not found"/>', '<spring:message code="button.ok" text="button.ok not found"/>', redirect);
				} 
		},
		error: function(){
			alert("ERROR", 'No se ha podido dar de alta el nodo', '<spring:message code="button.ok" text="button.ok not found"/>');
		}
	});			
	
		
}
</script>