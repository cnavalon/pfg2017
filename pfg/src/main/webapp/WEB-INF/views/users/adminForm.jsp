<%@ include file="/WEB-INF/views/common/include.jsp" %>
<spring:url value="/users/adm/upsertAdmin?${_csrf.parameterName}=${_csrf.token}" var="urlUpsert" />

<fieldset id="filedsetPerson">
	<div id="divPerRow0" class="form-group">
		
		<input type="hidden" value="${person.id}" id="inputIdPerson">
		
		<div id="divName">
			<spring:message code="user.name" var="userNameText" text="user.name not found"/>
			<label class="col-sm-2 control-label">${userNameText}*</label>
			<div class="col-sm-3">
				<input type="text" value="${person.name}" class="form-control" id="inputName" placeholder="${userNameText}">
			</div>
		</div>
		
		
		<div id="divPername1">
			<spring:message code="user.surname1" var="userSurname1Text" text="user.surname1 not found"/>
			<label class="col-sm-2 control-label">${userSurname1Text}*</label>
			<div class="col-sm-3">
				<input type="text" value="${person.surname1}"  class="form-control" id="inputSurname1" placeholder="${userSurname1Text}">
			</div>
		</div>
	</div>
				
	<div id="divPerRow1" class="form-group">
		<div id="divSurname2">
			<spring:message code="user.surname2" var="userSurname2Text" text="user.surname2 not found"/>
			<label class="col-sm-2 control-label">${userSurname2Text}</label>
			<div class="col-sm-3">
				<input type="text" value="${person.surname2}"  class="form-control" id="inputSurname2" placeholder="${userSurname2Text}">
			</div>
		</div>
		
		<div id="divEmail">
			<spring:message code="user.email" var="userEmailText" text="user.email not found"/>
			<label class="col-sm-2 control-label">${userEmailText}</label>
			<div class="col-sm-3">
				<input type="email" value="${person.email}" class="form-control" id="inputEmail" placeholder="${userEmailText}">
			</div>
		</div>
	</div>
	
	<div id="divPerRow2" class="form-group">
		<div id="divTelephone1">
			<spring:message code="user.telephone1" var="userTelephone1Text" text="user.telephone1 not found"/>
			<label class="col-sm-2 control-label">${userTelephone1Text}</label>
			<div class="col-sm-3">
				<input type="text" value="${person.telephone1}"class="form-control" id="inputTelephone1" placeholder="${userTelephone1Text}" onkeypress="return isNumberKey(event)" maxlength="9">
			</div>
		</div>
		
		<div id=divTelephone2>
			<spring:message code="user.telephone2" var="userTelephone2Text" text="user.telephone2 not found"/>
			<label class="col-sm-2 control-label">${userTelephone2Text}</label>
			<div class="col-sm-3">
				<input type="text" value="${person.telephone2}"class="form-control" id="inputTelephone2" placeholder="${userTelephone2Text}" onkeypress="return isNumberKey(event)" maxlength="9">
			</div>
		</div>
	</div>
</fieldset>

<script>
	
	function validFormPerson(){
		var lstValidatorPerson = [];
		if($("#inputName").val() === ""){
			lstValidatorPerson.push($("#inputName").attr('placeholder'));
			$("#divName").addClass("has-error");			
		}
		if($("#inputSurname1").val() === ""){
			lstValidatorPerson.push($("#inputSurname1").attr('placeholder'));
			$("#divSurname1").addClass("has-error");				
		}
		return lstValidatorPerson;
	}
	
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
	
	function getPerson(){
		return {
		 	id : $("#inputIdPerson").val(),
			idUser : $("#inputId").val(),
			name : $("#inputName").val(),
			surname1 : $("#inputSurname1").val(),
			surname2 : $("#inputSurname2").val(),
			email : $("#inputEmail").val(),
			telephone1 : $("#inputTelephone1").val(),
			telephone2 :  $("#inputTelephone2").val(),
			enabled : true,
			user : getUser()
		}
	}
	
	function getUrl(){
		return "${urlUpsert}"
	}
	
	function checkSavePerson(){
		return true;
	}
</script>