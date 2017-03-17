<%@ include file="/WEB-INF/views/common/include.jsp" %>
<spring:url value="/users/adm/upsertParent?${_csrf.parameterName}=${_csrf.token}" var="urlUpsert" />
<spring:url value="/users/adm/editUser/" var="urlEditUser" />
<spring:url value="/users/emp/viewUser/" var="urlViewUser" />

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
		
		
		<div id="divSurname1">
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
				<input type="text" value="${person.surname1}"  class="form-control" id="inputSurname2" placeholder="${userSurname2Text}">
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
	
	<div id="divPerRow3" class="form-group">
		<div id="divDNIType">
			<spring:message code="user.dni.type" var="userDNITypeText" text="user.dni.type not found"/>
			<label class="col-sm-2 control-label">${userDNITypeText}*</label>
			<div class="col-sm-3">
				<select class="form-control" id="selectDNIType">
					<option value="" label=""/>
					<c:forEach items="${lstDNI}" var="dni">
						<c:choose>
							<c:when test="${dni == person.dniType}">
								<option value="${dni}" label="${dni}" selected>${dni}</option>
							</c:when>
							<c:otherwise>
								<option value="${dni}" label="${dni}">${dni}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div id="divDNI">
			<spring:message code="user.dni.number" var="userDNIText" text="user.dni.number not found"/>
			<label class="col-sm-2 control-label">${userDNIText}*</label>
			<div class="col-sm-3">
				<input type="text" value="${person.dni}"class="form-control" id="inputDNI" placeholder="${userDNIText}" maxlength="9">
			</div>
		</div>
	</div>
	
	<div id="divPerRow4" class="form-group">
		<div id="divAddress">
			<spring:message code="user.address" var="userAddressText" text="user.address not found"/>
			<label class="col-sm-2 control-label">${userAddressText}</label>
			<div class="col-sm-3">
				<input type="text" value="${person.address}" class="form-control" id="inputAddress" placeholder="${userAddressText}">
			</div>
		</div>
		
		<div id="divCp">
			<spring:message code="user.cp" var="userCpText" text="user.cp not found"/>
			<label class="col-sm-2 control-label">${userCpText}</label>
			<div class="col-sm-3">
				<input type="text" value="${person.cp}" class="form-control" id="inputCp" placeholder="${userCpText}" onkeypress="return isNumberKey(event)" maxlength="5">
			</div>
		</div>
	</div>
	
	<div id="divPerRow5" class="form-group">
		<div id="divCity">
			<spring:message code="user.city" var="userCityText" text="user.city not found"/>
			<label class="col-sm-2 control-label">${userCityText}</label>
			<div class="col-sm-3">
				<input type="text" value="${person.city}" class="form-control" id="inputCity" placeholder="${userCityText}">
			</div>
		</div>
		
		<div id="divProvince">
			<spring:message code="user.province" var="userProvinceText" text="user.province not found"/>
			<label class="col-sm-2 control-label">${userProvinceText}</label>
			<div class="col-sm-3">
				<input type="text" value="${person.province}" class="form-control" id="inputProvince" placeholder="${userProvinceText}">
			</div>
		</div>	
	</div>
	<c:if test="${not empty person.id}">
	<!-- 	========================= TABLA HIJOS ======================= -->
		<h4 class="col-sm-offset-1"><spring:message code="parentList.title" text="parentList.title not found"/></h4>
		<div id="divStudentsTable" class="col-sm-offset-2 col-sm-8 noPadding">
			<table id="tableStudents" class="stripe hover row-border" width="100%">
				<thead>
			  		<tr>
			  			<th><spring:message code="user.username" text="user.username not found" /></th>
			    		<th><spring:message code="user.name" text="user.name not found" /></th>
			    		<th><spring:message code="user.surname1" text="user.surname1 not found" /></th>
			    		<th><spring:message code="user.surname2" text="user.surname2 not found" /></th>
			    		<th></th>
			  	</thead>
<!-- 			  	<tfoot> -->
<!-- 				  	<tr> -->
<%-- 			    		<th><spring:message code="user.name" text="user.name not found" /></th> --%>
<%-- 			    		<th><spring:message code="user.surname1" text="user.surname1 not found" /></th> --%>
<%-- 			    		<th><spring:message code="user.surname2" text="user.surname2 not found" /></th> --%>
<!-- 			    		<th></th> -->
<!-- 				  	</tr> -->
<!-- 			  	</tfoot> -->
			  	<tbody>
			  		<c:forEach items="${lstStudents}" var="student">
			  		<tr>
			  			<td>${student.idUser}</td>
			  			<td>${student.name}</td>
			  			<td>${student.surname1}</td>
			  			<td>${student.surname2}</td>
			  			<td>
			  				<div class="text-center">
			  					<spring:message code="common.go" var="editText" text="common.go not found" />
			  					<label id="editUser" class="cursorPointer iconTable" onclick="confirmEditStudent('${student.idUser}')"><i class="glyphicon glyphicon-arrow-right" title="${editText}"> </i></label>
			  				</div>
			  				
			  			</td>
			  		</tr>
			  		</c:forEach>
			  	</tbody>
			</table>
		</div>
	</c:if>
</fieldset>

<script>
	
	$(document).ready(function() {
		if($("#inputIdPerson").val() != ""){
			table =  $('#tableStudents').DataTable( {
				"paging": false,
			    "ordering": false,
			    "info": false,
			    "searching": false,
			    fixedHeader: {
			        header: true,
			        footer: false
			    }
			} );
		}
	});
	
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
		
		if($("#selectDNIType").val() === ""){
			lstValidatorPerson.push("${userDNITypeText}");
			$("#divDNIType").addClass("has-error");				
		}
		
		if($("#inputDNI").val() === ""){
			lstValidatorPerson.push($("#inputDNI").attr('placeholder'));
			$("#divDNI").addClass("has-error");				
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
	
	$("#selectDNIType").change(function(){
		if($("#selectDNIType").val() != ""){
			$("#divDNIType").removeClass("has-error");
		}
	});
	
	$("#inputDNI").change(function(){
		if($("#inputDNI").val() != ""){
			$("#divDNI").removeClass("has-error");
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
			dniType : $("#selectDNIType").val(),
			dni : $("#inputDNI").val(),			
			address : $("#inputAddress").val(),
			city : $("#inputCity").val(),
			province : $("#inputProvince").val(),
			cp : $("#inputCp").val(),
			user : getUser()
		}
	}
	
	function getUrl(){
		return "${urlUpsert}"
	}
	
	function checkSavePerson(){
		return true;
	}
	
	function confirmEditStudent(idUser){
		var url = "";
		if(editable){
			url = "${urlEditUser}" + idUser;
		} else {
			url = "${urlViewUser}" + idUser;
		} 
		confirm('<spring:message code="common.loseChanges" text="common.loseChanges not found"/>',function(){redirect(url)},null);
	}
</script>