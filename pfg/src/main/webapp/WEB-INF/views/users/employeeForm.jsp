<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/users/adm/addUser?${_csrf.parameterName}=${_csrf.token}" var="urlAddUser" />
<spring:url value="/users/adm/updateUser?${_csrf.parameterName}=${_csrf.token}" var="urlUpdateUser" />
<spring:url value="/users/adm/list" var="urlListUser" />

<div class="row-fluid">
	<h3 class="title">${title}</h3>
</div>

<div class="row-fluid">
<!-- 	<div class="col-sm-2"></div> -->
	
	<div class="col-sm-12">
		<form:form id="userForm" class="form-horizontal" method="post" modelAttribute="user" enctype="form-data" action="" >
			<fieldset id="filedsetForm">
				<div id="divIdName" class="form-group">
					<div id="divId">
						<spring:message code="user.id" var="userIdText" text="user.id not found"/>
						<label class="col-sm-2 control-label">${userIdText}*</label>
						<div class="col-sm-3">
							<form:input path="id" type="text" class="form-control" id="inputId" placeholder="${userIdText}"/>
						</div>
					</div>
					<div id="divName">
						<spring:message code="user.name" var="userNameText" text="user.name not found"/>
						<label class="col-sm-2 control-label">${userNameText}*</label>
						<div class="col-sm-3">
							<form:input path="name" type="text" class="form-control" id="inputName" placeholder="${userNameText}"/>
						</div>
					</div>
				</div>
				
				<div id="divSurnames" class="form-group">
					<div id="divSurname1">
						<spring:message code="user.surname1" var="userSurname1Text" text="user.surname1 not found"/>
						<label class="col-sm-2 control-label">${userSurname1Text}*</label>
						<div class="col-sm-3">
							<form:input path="surname1" type="text" class="form-control" id="inputSurname1" placeholder="${userSurname1Text}"/>
						</div>
					</div>
					
					<div id="divSurname2">
						<spring:message code="user.surname2" var="userSurname2Text" text="user.surname2 not found"/>
						<label class="col-sm-2 control-label">${userSurname2Text}</label>
						<div class="col-sm-3">
							<form:input path="surname2" type="text" class="form-control" id="inputSurname2" placeholder="${userSurname2Text}"/>
						</div>
					</div>
				</div>
				
				<div id="divPasswords" class="form-group">
					<div id="divPassword">
						<spring:message code="user.password" var="userPasswordText" text="user.password not found"/>
						<label class="col-sm-2 control-label">${userPasswordText}*</label>
						<div class="col-sm-3">
							<form:input path="password" type="password" class="form-control" id="inputPassword" placeholder="${userPasswordText}"/>
						</div>
					</div>
					
					<div id="divConfirmPassword">
						<spring:message code="user.confirmPassword" var="userConfirmPasswordText" text="user.confirmPassword not found"/>
						<label class="col-sm-2 control-label">${userConfirmPasswordText}*</label>
						<div class="col-sm-3">
							<input type="password" class="form-control" id="inputConfirmPassword" placeholder="${userConfirmPasswordText}"/>
						</div>
					</div>
					
					<label class="col-sm-2 control-label text-left">
						<span id="spanErrorPassword" class="text-danger"></span>
					</label>
				</div>
				
				<div id="divRoleSex" class="form-group">
					<div id="divRole">
						<spring:message code="user.role" var="userRoleText" text="user.role not found"/>
						<label class="col-sm-2 control-label">${userRoleText}*</label>
						<div class="col-sm-3">
							<form:select path="role" class="form-control" id="selectRole">
	      						<form:option value="" label=""/>
	      						<c:forEach items="${lstRoles}" var="role" varStatus="loop">
	      							<spring:message code="${role.name}" var="roleText" text="${role.name} not found"/>
	      							<form:option value="${role.role}" label="${roleText}"/>
	      						</c:forEach>
	 						</form:select>
						</div>
					</div>
					
					<div id="divSex">
						<spring:message code="user.sex" var="userSexText" text="user.sex not found"/>
						<label class="col-sm-2 control-label">${userSexText}*</label>
						<div class="col-sm-3">
	  						<spring:message code="user.sex.male" var="sexMaleText" text="user.sex.male not found"/>
	  						<spring:message code="user.sex.female" var="sexFemaleText" text="user.sex.female not found"/>
	  						<spring:message code="user.sex.other" var="sexOtherText" text="user.sex.other not found"/>
							<form:select path="sex" class="form-control" id="selectSex">
	      						<form:option value="" label=""/>
	      						<form:option value="F" label="${sexFemaleText}"/>
	      						<form:option value="M" label="${sexMaleText}"/>
	      						<form:option value="O" label="${sexOtherText}"/>
	 						</form:select>
						</div>
					</div>
					
				</div>
				
				
				<div id="divEmailBitrhDate" class="form-group">
					<div id="divEmail">
						<spring:message code="user.email" var="userEmailText" text="user.email not found"/>
						<label class="col-sm-2 control-label">${userEmailText}</label>
						<div class="col-sm-3">
							<form:input path="email" type="email" class="form-control" id="inputEmail" placeholder="${userEmailText}"/>
						</div>
					</div>
					
					<div id="divBirthdate">
						<spring:message code="user.birthdate" var="userBirthdateText" text="birthdate not found"/>
						<label class="col-sm-2 control-label">${userBirthdateText}*</label>
						<div class="col-sm-3 date">
							<div id="birthdateAction" class="input-group input-append date">
							<form:input path="birthdate" type='text' class="form-control" id="inputBirthdate" onkeypress="return false" />
								<span id="instalDateCalendarIcon" class="input-group-addon add-on">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
					</div>
				</div>
				
				<div id="divAddressCp" class="form-group">
					<div id="divAddress">
						<spring:message code="user.address" var="userAddressText" text="user.address not found"/>
						<label class="col-sm-2 control-label">${userAddressText}</label>
						<div class="col-sm-3">
							<form:input path="address" type="text" class="form-control" id="inputAddress" placeholder="${userAddressText}"/>
						</div>
					</div>
					
					<div id="divCp">
						<spring:message code="user.cp" var="userCpText" text="user.cp not found"/>
						<label class="col-sm-2 control-label">${userCpText}</label>
						<div class="col-sm-3">
							<form:input path="cp" type="text" class="form-control" id="inputCp" placeholder="${userCpText}" onkeypress="return isNumberKey(event)" maxlength="5"/>
						</div>
					</div>
				</div>
				
				<div id="divCityProvince" class="form-group">
					<div id="divCity">
						<spring:message code="user.city" var="userCityText" text="user.city not found"/>
						<label class="col-sm-2 control-label">${userCityText}</label>
						<div class="col-sm-3">
							<form:input path="city" type="text" class="form-control" id="inputCity" placeholder="${userCityText}"/>
						</div>
					</div>
					
					<div id="divProvince">
						<spring:message code="user.province" var="userProvinceText" text="user.province not found"/>
						<label class="col-sm-2 control-label">${userProvinceText}</label>
						<div class="col-sm-3">
							<form:input path="province" type="text" class="form-control" id="inputProvince" placeholder="${userProvinceText}"/>
						</div>
					</div>
				</div>
				
				<div id="divTelephones" class="form-group">
					<div id="divTelephone1">
						<spring:message code="user.telephone1" var="userTelephone1Text" text="user.telephone1 not found"/>
						<label class="col-sm-2 control-label">${userTelephone1Text}</label>
						<div class="col-sm-3">
							<form:input path="telephone1" type="text" class="form-control" id="inputTelephone1" placeholder="${userTelephone1Text}" onkeypress="return isNumberKey(event)" maxlength="9"/>
						</div>
					</div>
					
					<div id=divTelephone2>
						<spring:message code="user.telephone2" var="userTelephone2Text" text="user.telephone2 not found"/>
						<label class="col-sm-2 control-label">${userTelephone2Text}</label>
						<div class="col-sm-3">
							<form:input path="telephone2" type="text" class="form-control" id="inputTelephone2" placeholder="${userTelephone2Text}" onkeypress="return isNumberKey(event)" maxlength="9"/>
						</div>
					</div>
				</div>
						
			</fieldset>
			
			<div class="form-group">
				<span class="col-sm-offset-2 col-sm-3"><small>* Campos obligatorios</small></span>
			</div>
			
			<div class="form-group ">
		    	<div class="col-sm-offset-4 col-sm-6 text-right">
		    		<div class="col-sm-4"></div>
		    		<div class="col-sm-4">
			      		<button id ="buttonAdd" type="button" class="btn btn-block btn-default"><spring:message code="common.save" text="common.save not found"/></button>
		    		</div>
		    		<div class="col-sm-4">
			      		<button id ="buttonCancel" type="button" class="btn btn-block btn-default" ><spring:message code="common.cancel" text="common.cancel not found"/></button>
		    		</div>
<!-- 		    		<div class="col-sm-4"> -->
<%-- 			      		<button id ="buttonRestore" type="reset" class="btn btn-block btn-default"><spring:message code="common.restore" text="common.restore not found"/></button> --%>
<!-- 		    		</div> -->
	      		</div>
      		</div>
			
		</form:form>
	</div>
</div>

<script>
	
	var urlAction = '${urlAddUser}';
	
	$(document).ready(function() {
		$(document).ajaxStart(function() {blockUI();}).ajaxStop(function() {unblockUI();});
		orderAllOptions();
		
		if($("#inputPassword").val() != ""){
			$("#inputConfirmPassword").val($("#inputPassword").val());
		}
		
		if($("#inputId").val() != ""){
			$("#inputId").prop('readonly', true);
			urlAction = '${urlUpdateUser}';
		}
	} );
	
	$("#inputPassword").change(function(){
		if($("#inputConfirmPassword").val() !== "" || $(this).val() === "")
			checkPass($(this).val(),$("#inputConfirmPassword").val());
	});
	
	$("#inputConfirmPassword").change(function(){
		checkPass($("#inputPassword").val(),$(this).val());
	});
	
	$('#birthdateAction').datetimepicker({
	    format: 'DD/MM/YYYY',
	    locale: '${locale}'
	});
	
	$('#buttonCancel').click(function(event){
		confirm('<spring:message code="user.form.cancel" text="user.form.cancel not found"/>', goToListUsers, null);
	});
	
	function goToListUsers(){
		location.href = "${urlListUser}";
	}
	
	function checkPass(pass, confirmPass){
		if(pass === confirmPass){
			$("#spanErrorPassword").text("");
			$("#divPassword").removeClass("has-error");
			$("#divConfirmPassword").removeClass("has-error");
		} else {
			$("#spanErrorPassword").text('<spring:message code="user.error.password" text="user.error.password not found"/>');
			$("#divPassword").addClass("has-error");
			$("#divConfirmPassword").addClass("has-error");
		}
	}
	
	$("#inputId").change(function(){
		if($("#inputId").val() != ""){
			$("#divId").removeClass("has-error");
		}
	});
	
	$("#inputName").change(function(){
		if($("#inputName").val() != ""){
			$("#divName").removeClass("has-error");
		}
	});
	
	$("#inputSurname1").change(function(){
		if($("#inputSurname1").val() != ""){
			$("#divSurname1").removeClass("has-error");
		}
	});
	
	$("#selectRole").change(function(){
		if($("#selectRole").val() != ""){
			$("#divRole").removeClass("has-error");
		}
	});

	$("#selectSex").change(function(){
		if($("#selectSex").val() != ""){
			$("#divSex").removeClass("has-error");
		}
	});
	
	$("#inputBirthdate").change(function(){
		if($("#inputBirthdate").val() != ""){
			$("#divBirthdate").removeClass("has-error");
		}
	});
	
	function validForm(){
		var lstValidator = [];
		if($("#inputId").val() === ""){
			lstValidator.push($("#inputId").attr('placeholder'));
			$("#divId").addClass("has-error");	
		}
		if($("#inputName").val() === ""){
			lstValidator.push($("#inputName").attr('placeholder'));
			$("#divName").addClass("has-error");			
		}
		if($("#inputSurname1").val() === ""){
			lstValidator.push($("#inputSurname1").attr('placeholder'));
			$("#divSurname1").addClass("has-error");				
		}
		if($("#inputPassword").val() === ""){
			lstValidator.push($("#inputPassword").attr('placeholder'));
			$("#divPassword").addClass("has-error");				
		}
		if($("#inputConfirmPassword").val() === ""){
			lstValidator.push($("#inputConfirmPassword").attr('placeholder'));
			$("#divConfirmPassword").addClass("has-error");				
		}
		if($("#selectRole").val() === ""){
			lstValidator.push('${userRoleText}');
			$("#divRole").addClass("has-error");	
		}
		if($("#selectSex").val() === ""){
			lstValidator.push('${userSexText}');
			$("#divSex").addClass("has-error");				
		}
		return lstValidator;
	}
	
	$("#buttonAdd").click(function(){
		lstValidator = validForm();
		if(lstValidator.length > 0){
			var message = '<spring:message code="user.error.validator" text="user.error.validator not found"/>';
			for(var i=0; i < lstValidator.length; i++){
				message += "<br>- " + lstValidator[i];
			}
			alert(message,null);
		} else {
			var userId = $("#inputId").val();
			confirm('<spring:message code="user.save.quest" text="user.delete.quest not found" />',addUser,null);
		}
	});

function addUser(){
// 	var formData = new FormData($('form')[0]);
	var formData = $('form').serialize();
	$.ajax({
		url : urlAction,
		type:"POST", 
		data: formData,
		async: true,
		cache: false,
	    contentType: " application/x-www-form-urlencoded",
		success : function(code) {
			if (code == 0){
				alert('<spring:message code="user.saved" text="user.saved not found"/>',goToListUsers);
			} 
			else if (code == 2){
				$("#inputId").addClass("has-error");	
				alert('<spring:message code="user.save.error.id" text="user.save.error.id not found"/>', null);
			} 
			else {
				alert('<spring:message code="user.save.error" text="user.save.error not found"/>', null);
			}
		},
		error: function(){
			alert('<spring:message code="user.save.error" text="user.save.error not found"/>', null);
		}
	});			
	
		
}
</script>