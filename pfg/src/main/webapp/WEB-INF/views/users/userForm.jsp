<%@ include file="/WEB-INF/views/common/include.jsp" %>


<spring:url value="/users/emp/users" var="urlUsersSearch" />
<spring:url value="/users/adm/checkUser/" var="urlCheckUser" />
<spring:url value="/users/adm/newPerson/" var="urlNewPerson" />
<spring:url value="/users/emp/updatePerson/" var="urlUpdatePerson" />


<div class="row-fluid">
	<h3 class="title">${title}</h3>
</div>

<div class="row-fluid">
	
	<div class="col-sm-12">
		<form id="userForm" class="form-horizontal" method="post" enctype="form-data" action="" >
			<fieldset id="filedsetForm">
				<div id="divRow0" class="form-group">
					<div id="divId">
						<spring:message code="user.id" var="userIdText" text="user.id not found"/>
						<label class="col-sm-2 control-label">${userIdText}*</label>
						<div class="col-sm-3">
							<input type="text" value="${user.idUser}" class="form-control" id="inputId" placeholder="${userIdText}" onkeypress="return isIDKey(event)">
						</div>
					</div>
					
					<div id="divRole">
						<spring:message code="user.role" var="userRoleText" text="user.role not found"/>
						<label class="col-sm-2 control-label">${userRoleText}*</label>
						<div class="col-sm-3">
							<select class="form-control" id="selectRole">
	      						<option value="" label=""/>
	      						<c:forEach items="${lstRoles}" var="role" varStatus="loop">
	      							<spring:message code="${role.name}" var="roleText" text="${role.name} not found"/>
	      							<c:choose>
	      								<c:when test="${role.idRole == user.role}">
			      							<option value="${role.idRole}" label="${roleText}" selected>${roleText}</option>
	      								</c:when>
	      								<c:otherwise>
	      									<c:if test="${not (role.idRole == 'ROLE_PAR')}">
		      									<option value="${role.idRole}" label="${roleText}">${roleText}</option>
	      									</c:if>
	      								</c:otherwise>
	      							</c:choose>
	      						</c:forEach>
	 						</select>
						</div>
					</div>
					
				</div>
				
				<div id="divRow1" class="form-group">
				
					<input type="hidden" value="${user.password}" id="inputLastPassword">
					
					<div id="divPassword">
						<spring:message code="user.password" var="userPasswordText" text="user.password not found"/>
						<label class="col-sm-2 control-label">${userPasswordText}*</label>
						<div class="col-sm-3">
							<input type="password" value="${user.password}" class="form-control" id="inputPassword" placeholder="${userPasswordText}">
						</div>
					</div>
					
					<div id="divConfirmPassword">
						<spring:message code="user.confirmPassword" var="userConfirmPasswordText" text="user.confirmPassword not found"/>
						<label class="col-sm-2 control-label">${userConfirmPasswordText}*</label>
						<div class="col-sm-3">
							<input type="password" value="${user.password}" class="form-control" id="inputConfirmPassword" placeholder="${userConfirmPasswordText}"/>
						</div>
					</div>
					
					<label class="col-sm-2 control-label text-left">
						<span id="spanErrorPassword" class="text-danger"></span>
					</label>
				</div>
				
				<div id="divPersonForm"></div>
				
			</fieldset>
			
			<div class="form-group">
				<span class="col-sm-offset-2 col-sm-3"><small>* Campos obligatorios</small></span>
			</div>
			
			<div class="form-group ">
		    	<div class="col-sm-offset-4 col-sm-6 text-right">
		    		<div class="col-sm-4"></div>
		    		<c:choose>
		    			<c:when test="${editable}">
			    			<div class="col-sm-4">
					      		<button id ="buttonAdd" type="button" class="btn btn-block btn-default"><spring:message code="common.save" text="common.save not found"/></button>
				    		</div>
				    		<div class="col-sm-4">
					      		<button id ="buttonCancel" type="button" class="btn btn-block btn-default" ><spring:message code="common.cancel" text="common.cancel not found"/></button>
				    		</div>
		    			</c:when>
		    			<c:otherwise>
		    				<div class="col-sm-4">
				    		</div>
				    		<div class="col-sm-4">
					      		<button id ="buttonCancel" type="button" class="btn btn-block btn-default" ><spring:message code="common.ok" text="common.ok not found"/></button>
				    		</div>
		    			</c:otherwise>
		    		</c:choose>
		    		
	      		</div>
      		</div>
			
		</form>
	</div>
</div>

<script>
	
	var urlAction = '${urlAddUser}';
	
	var idCorrect = true;
	var passCorrect = true;
	
	var editable = ("${editable}" == "true");
	
	$(document).ready(function() {
		$(document).ajaxStart(function() {blockUI();}).ajaxStop(function() {unblockUI();});
		orderAllOptions();
		
		if($("#inputPassword").val() != ""){
			$("#inputConfirmPassword").val($("#inputPassword").val());
		}
		
		if(!editable){
			$("#filedsetForm").prop("disabled",true);
		}
		
		if($("#inputId").val() != ""){
			$("#inputId").prop('disabled', true);
			$("#selectRole").prop('disabled', true);
			$("#divPersonForm").load('${urlUpdatePerson}' + $("#inputId").val() + "/" + $("#selectRole").val());
		}
	} );
	
	$("#inputPassword").change(function(){
		if($("#inputConfirmPassword").val() !== "" || $(this).val() === "")
			checkPass($(this).val(),$("#inputConfirmPassword").val());
	});
	
	$("#inputConfirmPassword").change(function(){
		checkPass($("#inputPassword").val(),$(this).val());
	});
	
	$('#buttonCancel').click(function(event){
		confirm('<spring:message code="user.form.cancel" text="user.form.cancel not found"/>', redirect("${urlUsersSearch}"), null);
	});
	
	$("#inputId").change(function(){
		if($("#inputId").val().trim() != ""){
			if(checkOthersUsers()){
				checkIdUser($("#inputId").val().trim());
			}
		}
	});
	
	$("#selectRole").change(function(){
		if($("#selectRole").val() != ""){
			$("#divRole").removeClass("has-error");
			$("#divPersonForm").empty();
				$("#divPersonForm").load('${urlNewPerson}' + $("#selectRole").val());
		} else {
			$("#divPersonForm").empty();
		}
	});
	
	function checkOthersUsers(){
		return true;
	}

	function validForm(){
		var lstValidator = [];
		if($("#inputId").val().trim() === ""){
			lstValidator.push($("#inputId").attr('placeholder'));
			$("#divId").addClass("has-error");	
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
		} else {
			var list = validFormPerson();
			lstValidator = lstValidator.concat(list);
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
		
		var data = getPerson();
		
		$.ajax({
			url : getUrl(),
			type:"POST", 
			data: JSON.stringify(data),
			contentType: "application/json",
			success : function(error) {
				if (error == null || error == ""){
					alert('<spring:message code="user.saved" text="user.saved not found"/>',null);
				} 
				else {
					alert(error, null);
				}
			},
			error: function(){
				alert('<spring:message code="user.save.error" text="user.save.error not found"/>', null);
			}
		});			
	}
	
	function getUser(){
		return {
			idUser : $("#inputId").val(),
			password : $("#inputPassword").val(),
			role : $("#selectRole").val(),
			enabled : true,
			lastPassword : $("#inputLastPassword").val()
		}
	}
	
	function checkIdUser(idUser){
		$.ajax({
			url : '${urlCheckUser}' + idUser,
			type: "GET", 
			success : function(error) {
				if (error == null || error == ""){
					$("#divId").removeClass("has-error");
					idCorrect = true;
					checkSaveButton();
				} 
				else {
					$("#divId").addClass("has-error");
					idCorrect = false;
					alert(error, null);
					checkSaveButton();
				} 
			}
		});		
	}
	
	function checkPass(pass, confirmPass){
		if(pass === confirmPass){
			$("#spanErrorPassword").text("");
			$("#divPassword").removeClass("has-error");
			$("#divConfirmPassword").removeClass("has-error");
			passCorrect = true;
			checkSaveButton();
		} else {
			$("#spanErrorPassword").text('<spring:message code="user.error.password" text="user.error.password not found"/>');
			$("#divPassword").addClass("has-error");
			$("#divConfirmPassword").addClass("has-error");
			$("#buttonAdd").attr("disabled",true);
			passCorrect = false;
			checkSaveButton();
		}
	}
	
	function checkSaveButton(){
		if(idCorrect && passCorrect && checkSavePerson()){
			$("#buttonAdd").attr("disabled",false);
		} else {
			$("#buttonAdd").attr("disabled",true);
		}
	}
	
	function checkSavePerson(){
		return true;
	}
</script>