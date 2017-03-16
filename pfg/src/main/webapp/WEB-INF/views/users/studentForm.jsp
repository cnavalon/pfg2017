<%@ include file="/WEB-INF/views/common/include.jsp" %>
<spring:url value="/users/adm/upsertStudent?${_csrf.parameterName}=${_csrf.token}" var="urlUpsert" />
<spring:url value="/users/adm/checkUser/" var="urlCheckUser" />
<spring:url value="/users/adm/editUser/" var="urlEditUser" />
<spring:url value="/users/emp/viewUser/" var="urlViewUser" />
<spring:url value="/users/adm/deleteUser/" var="urlDeleteUser" />

<fieldset id="filedsetPerson">
	<fieldset id="fieldsetStudent">
<!-- 	========================= ALUMNO ======================= -->
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
			<div id="divBirthdate">
				<spring:message code="user.birthdate" var="userBirthdateText" text="birthdate not found"/>
				<label class="col-sm-2 control-label">${userBirthdateText}*</label>
				<div class="col-sm-3 date">
					<div id="birthdateAction" class="input-group input-append date">
						<input type='text' value="${person.birthdate}" class="form-control" id="inputBirthdate" >
						<span id="spanBirthdate" class="input-group-addon add-on">
							<span class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
			
			<div id="divCourse">
				<spring:message code="user.course" var="userCourseText" text="user.course not found"/>
				<label class="col-sm-2 control-label">${userCourseText}*</label>
				<div class="col-sm-3">
					<select class="form-control" id="selectCourse" >
						<option value="" label=""/>
						<c:forEach items="${lstCourses}" var="course">
							<c:choose>
								<c:when test="${course.idCourse == person.course}">
									<option value="${course.idCourse}" label="${course.name}" selected>${course.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${course.idCourse}" label="${course.name}">${course.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			
		</div>
		
		<div id="divPerRow5" class="form-group">
			<div id="divSex">
				<spring:message code="user.sex" var="userSexText" text="user.sex not found"/>
				<label class="col-sm-2 control-label">${userSexText}*</label>
				<div class="col-sm-3">
					<spring:message code="user.sex.male" var="sexMaleText" text="user.sex.male not found"/>
					<spring:message code="user.sex.female" var="sexFemaleText" text="user.sex.female not found"/>
					<spring:message code="user.sex.other" var="sexOtherText" text="user.sex.other not found"/>
					<select class="form-control" id="selectSex">
						<option value="" label=""/>
						<c:forEach items="${lstSex}" var="sex">
							<c:choose>
								<c:when test="${sex.value == person.sex}">
									<option value="${sex.value}" label="${sex.label}" selected>${sex.label}</option>
								</c:when>
								<c:otherwise>
									<option value="${sex.value}" label="${sex.label}">${sex.label}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
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
		
		<div id="divPerRow6" class="form-group">
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
		
		<div id="divPerRow7" class="form-group">
			<div id="divAddress">
				<spring:message code="user.address" var="userAddressText" text="user.address not found"/>
				<label class="col-sm-2 control-label">${userAddressText}</label>
				<div class="col-sm-8">
					<input type="text" value="${person.address}" class="form-control" id="inputAddress" placeholder="${userAddressText}">
				</div>
			</div>
			<div id="divCopyAddress">
				<button id="buttonCopyAddress" type="button" class="btn btn-default" title="<spring:message code="student.copyAddress" text="student.copyAddress not found"/>"><span class="glyphicon glyphicon-copy" aria-hidden="true"></span></button>
			</div>
		</div>
	</fieldset>
	
	<c:if test="${not empty person.id}">
	<!-- 	========================= TABLA PADRES ======================= -->
		<h4 class="col-sm-offset-1"><spring:message code="parentList.title" text="parentList.title not found"/></h4>
		<div id="divParentsTable" class="col-sm-offset-2 col-sm-8 noPadding">
			<table id="tableParents" class="stripe hover row-border" width="100%">
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
			  		<c:forEach items="${lstParents}" var="parent">
			  		<tr>
			  			<td>${parent.idUser}</td>
			  			<td>${parent.name}</td>
			  			<td>${parent.surname1}</td>
			  			<td>${parent.surname2}</td>
			  			<td>
			  				<div class="text-center">
			  					<spring:message code="common.go" var="editText" text="common.go not found" />
			  					<label id="editUser" class="cursorPointer iconTable" onclick="confirmEditParent('${parent.idUser}')"><i class="glyphicon glyphicon-arrow-right" title="${editText}"> </i></label>
			  					<spring:message code="common.delete" var="deleteText" text="common.delete not found" />
			  					<label id="deleteUser" class="cursorPointer iconTable deleteUser" onclick="confirmDeleteUser('${parent.idUser}')"><i class="glyphicon glyphicon-trash" title="${deleteText}"> </i></label>
			  				</div>
			  				
			  			</td>
			  		</tr>
			  		</c:forEach>
			  	</tbody>
			</table>
		</div>
	</c:if>
	<div class="clearfix" ></div>
	<div id="divParent1">
<!-- 	========================= PADRE 1 ======================= -->
		<hr>
		
		<h4 class="col-sm-offset-1">
			<input type="checkbox" id="checkboxPar1" checked>
			<spring:message code="parent1.title" text="parent1.title not found"/> 
		</h4>
		
		<fieldset id="fieldsetParent1">
			<div id="divPar1_0" class="form-group">
				<input type="hidden" id="inputIdPersonPar1">
				<div id="divIdPar1">
					<spring:message code="user.id" var="userIdText" text="user.id not found"/>
					<label class="col-sm-2 control-label">${userIdText}*</label>
					<div class="col-sm-3">
						<div id="divIdPar1" class="input-group input-append date">
							<input type="text" class="form-control" id="inputIdPar1" placeholder="${userIdText}">
							<span id="iconSearchPar1" class="input-group-addon add-on">
								<span class="glyphicon glyphicon-search"></span>
							</span>
						</div>
					</div>
				</div>
				
				<div id="divNamePar1">
					<spring:message code="user.name" var="userNameText" text="user.name not found"/>
					<label class="col-sm-2 control-label">${userNameText}*</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="inputNamePar1" placeholder="${userNameText}">
					</div>
				</div>
	<!-- 			<div id="divRolePar1"> -->
	<%-- 				<spring:message code="user.role" var="userRoleText" text="user.role not found"/> --%>
	<%-- 				<label class="col-sm-2 control-label">${userRoleText}*</label> --%>
	<!-- 				<div class="col-sm-3"> -->
	<!-- 					<select class="form-control" id="selectRolePar1" disabled> -->
	<%-- 						<spring:message code="${parRole.name}" var="parRoleText" text="${parRole.name} not found"/> --%>
	<%--    						<option value="${parRole.name}" label="${parRoleText}">${parRoleText}</option> --%>
	<!-- 					</select> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
			</div>
			<div id="divPar1_1" class="form-group">
				<div id="divSurname1Par1">
					<spring:message code="user.surname1" var="userSurname1Text" text="user.surname1 not found"/>
					<label class="col-sm-2 control-label">${userSurname1Text}*</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="inputSurname1Par1" placeholder="${userSurname1Text}">
					</div>
				</div>
				
				<div id="divSurname2Par1">
					<spring:message code="user.surname2" var="userSurname2Text" text="user.surname2 not found"/>
					<label class="col-sm-2 control-label">${userSurname2Text}</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="inputSurname2Par1" placeholder="${userSurname2Text}">
					</div>
				</div>
			</div>
			<fieldset id=fieldsetNewPar1>
				<div id="divPar1_2" class="form-group">
					<div id="divPasswordPar1">
						<spring:message code="user.password" var="userPasswordText" text="user.password not found"/>
						<label class="col-sm-2 control-label">${userPasswordText}*</label>
						<div class="col-sm-3">
							<input type="password" class="form-control" id="inputPasswordPar1" placeholder="${userPasswordText}">
						</div>
					</div>
					
					<div id="divConfirmPasswordPar1">
						<spring:message code="user.confirmPassword" var="userConfirmPasswordText" text="user.confirmPassword not found"/>
						<label class="col-sm-2 control-label">${userConfirmPasswordText}*</label>
						<div class="col-sm-3">
							<input type="password" class="form-control" id="inputConfirmPasswordPar1" placeholder="${userConfirmPasswordText}"/>
						</div>
					</div>
					
					<label class="col-sm-2 control-label text-left">
						<span id="spanErrorPasswordPar1" class="text-danger"></span>
					</label>
				</div>	
				
				<div id="divPar1_3" class="form-group">
					<div id="divTelephone1Par1">
						<spring:message code="user.telephone1" var="userTelephone1Text" text="user.telephone1 not found"/>
						<label class="col-sm-2 control-label">${userTelephone1Text}</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="inputTelephone1Par1" placeholder="${userTelephone1Text}" onkeypress="return isNumberKey(event)" maxlength="9">
						</div>
					</div>
			
					<div id=divTelephone2Par1>
						<spring:message code="user.telephone2" var="userTelephone2Text" text="user.telephone2 not found"/>
						<label class="col-sm-2 control-label">${userTelephone2Text}</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="inputTelephone2Par1" placeholder="${userTelephone2Text}" onkeypress="return isNumberKey(event)" maxlength="9">
						</div>
					</div>
				</div>
				
				<div id="divPar1_4" class="form-group">
					<div id="divDNITypePar1">
						<spring:message code="user.dni.type" var="userDNITypeText" text="user.dni.type not found"/>
						<label class="col-sm-2 control-label">${userDNITypeText}*</label>
						<div class="col-sm-3">
							<select class="form-control" id="selectDNITypePar1">
								<option value="" label=""/>
								<c:forEach items="${lstDNI}" var="dni">
									<option value="${dni}" label="${dni}">${dni}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div id="divDNIPar1">
						<spring:message code="user.dni.number" var="userDNIText" text="user.dni.number not found"/>
						<label class="col-sm-2 control-label">${userDNIText}*</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="inputDNIPar1" placeholder="${userDNIText}" maxlength="9">
						</div>
					</div>
				</div>
				
				<div id="divPar1_5" class="form-group">
					<div id="divEmailPar1">
						<spring:message code="user.email" var="userEmailText" text="user.email not found"/>
						<label class="col-sm-2 control-label">${userEmailText}</label>
						<div class="col-sm-3">
							<input type="email" class="form-control" id="inputEmailPar1" placeholder="${userEmailText}">
						</div>
					</div>
					
					<div id="divCpPar1">
						<spring:message code="user.cp" var="userCpText" text="user.cp not found"/>
						<label class="col-sm-2 control-label">${userCpText}</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="inputCpPar1" placeholder="${userCpText}" onkeypress="return isNumberKey(event)" maxlength="5">
						</div>
					</div>
				</div>
				
				<div id="divPar1_6" class="form-group">
					<div id="divCityPar1">
						<spring:message code="user.city" var="userCityText" text="user.city not found"/>
						<label class="col-sm-2 control-label">${userCityText}</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="inputCityPar1" placeholder="${userCityText}">
						</div>
					</div>
					
					<div id="divProvincePar1">
						<spring:message code="user.province" var="userProvinceText" text="user.province not found"/>
						<label class="col-sm-2 control-label">${userProvinceText}</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="inputProvincePar1" placeholder="${userProvinceText}">
						</div>
					</div>	
				</div>
				
				<div id="divPar1_7" class="form-group">
					<div id="divAddressPar1">
						<spring:message code="user.address" var="userAddressText" text="user.address not found"/>
						<label class="col-sm-2 control-label">${userAddressText}</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="inputAddressPar1" placeholder="${userAddressText}">
						</div>
					</div>
				</div>
			</fieldset>
		</fieldset>
	</div>
	<!-- 	========================= PADRE 2 ======================= -->
	<div id="divParent2">
		<hr>
		
		<h4 class="col-sm-offset-1">
			<input type="checkbox" id="checkboxPar2" checked>
			<spring:message code="parent2.title" text="parent2.title not found"/>  
		</h4>
		
		<fieldset id="fieldsetParent2">
			
			<div id="divPar2_0" class="form-group">
				<div id="divIdPar2">
					<input type="hidden" id="inputIdPersonPar2">
					<spring:message code="user.id" var="userIdText" text="user.id not found"/>
					<label class="col-sm-2 control-label">${userIdText}*</label>
					<div class="col-sm-3">
						<div id="divIdPar2" class="input-group input-append date">
							<input type="text" class="form-control" id="inputIdPar2" placeholder="${userIdText}">
							<span id="iconSearchPar2" class="input-group-addon add-on">
								<span class="glyphicon glyphicon-search"></span>
							</span>
						</div>
					</div>
				</div>
				
				<div id="divNamePar2">
					<spring:message code="user.name" var="userNameText" text="user.name not found"/>
					<label class="col-sm-2 control-label">${userNameText}*</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="inputNamePar2" placeholder="${userNameText}">
					</div>
				</div>
				
	<!-- 			<div id="divRolePar2"> -->
	<%-- 				<spring:message code="user.role" var="userRoleText" text="user.role not found"/> --%>
	<%-- 				<label class="col-sm-2 control-label">${userRoleText}*</label> --%>
	<!-- 				<div class="col-sm-3"> -->
	<!-- 					<select class="form-control" id="selectRolePar2" disabled> -->
	<%-- 						<spring:message code="${parRole.name}" var="parRoleText" text="${parRole.name} not found"/> --%>
	<%--    						<option value="${parRole.name}" label="${parRoleText}">${parRoleText}</option> --%>
	<!-- 					</select> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
			</div>
			<div id="divPar2_1" class="form-group">
				<div id="divSurname1Par2">
					<spring:message code="user.surname1" var="userSurname1Text" text="user.surname1 not found"/>
					<label class="col-sm-2 control-label">${userSurname1Text}*</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="inputSurname1Par2" placeholder="${userSurname1Text}">
					</div>
				</div>
				
				<div id="divSurname2Par2">
					<spring:message code="user.surname2" var="userSurname2Text" text="user.surname2 not found"/>
					<label class="col-sm-2 control-label">${userSurname2Text}</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="inputSurname2Par2" placeholder="${userSurname2Text}">
					</div>
				</div>
			</div>
			
			<fieldset id=fieldsetNewPar2>
				<div id="divPar2_2" class="form-group">
					<div id="divPasswordPar2">
						<spring:message code="user.password" var="userPasswordText" text="user.password not found"/>
						<label class="col-sm-2 control-label">${userPasswordText}*</label>
						<div class="col-sm-3">
							<input type="password" class="form-control" id="inputPasswordPar2" placeholder="${userPasswordText}">
						</div>
					</div>
					
					<div id="divConfirmPasswordPar2">
						<spring:message code="user.confirmPassword" var="userConfirmPasswordText" text="user.confirmPassword not found"/>
						<label class="col-sm-2 control-label">${userConfirmPasswordText}*</label>
						<div class="col-sm-3">
							<input type="password" class="form-control" id="inputConfirmPasswordPar2" placeholder="${userConfirmPasswordText}"/>
						</div>
					</div>
					
					<label class="col-sm-2 control-label text-left">
						<span id="spanErrorPasswordPar2" class="text-danger"></span>
					</label>
				</div>	
				
				<div id="divPar2_3" class="form-group">
					<div id="divTelephone1Par2">
						<spring:message code="user.telephone1" var="userTelephone1Text" text="user.telephone1 not found"/>
						<label class="col-sm-2 control-label">${userTelephone1Text}</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="inputTelephone1Par2" placeholder="${userTelephone1Text}" onkeypress="return isNumberKey(event)" maxlength="9">
						</div>
					</div>
			
					<div id=divTelephone2Par2>
						<spring:message code="user.telephone2" var="userTelephone2Text" text="user.telephone2 not found"/>
						<label class="col-sm-2 control-label">${userTelephone2Text}</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="inputTelephone2Par2" placeholder="${userTelephone2Text}" onkeypress="return isNumberKey(event)" maxlength="9">
						</div>
					</div>
				</div>
				
				<div id="divPar2_4" class="form-group">
					<div id="divDNITypePar2">
						<spring:message code="user.dni.type" var="userDNITypeText" text="user.dni.type not found"/>
						<label class="col-sm-2 control-label">${userDNITypeText}*</label>
						<div class="col-sm-3">
							<select class="form-control" id="selectDNITypePar2">
								<option value="" label=""/>
								<c:forEach items="${lstDNI}" var="dni">
									<option value="${dni}" label="${dni}">${dni}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div id="divDNIPar2">
						<spring:message code="user.dni.number" var="userDNIText" text="user.dni.number not found"/>
						<label class="col-sm-2 control-label">${userDNIText}*</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="inputDNIPar2" placeholder="${userDNIText}" maxlength="9">
						</div>
					</div>
				</div>
				
				<div id="divPar2_5" class="form-group">
					<div id="divEmailPar2">
						<spring:message code="user.email" var="userEmailText" text="user.email not found"/>
						<label class="col-sm-2 control-label">${userEmailText}</label>
						<div class="col-sm-3">
							<input type="email" class="form-control" id="inputEmailPar2" placeholder="${userEmailText}">
						</div>
					</div>
					
					<div id="divCpPar2">
						<spring:message code="user.cp" var="userCpText" text="user.cp not found"/>
						<label class="col-sm-2 control-label">${userCpText}</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="inputCpPar2" placeholder="${userCpText}" onkeypress="return isNumberKey(event)" maxlength="5">
						</div>
					</div>
				</div>
				
				<div id="divPar2_6" class="form-group">
					<div id="divCityPar2">
						<spring:message code="user.city" var="userCityText" text="user.city not found"/>
						<label class="col-sm-2 control-label">${userCityText}</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="inputCityPar2" placeholder="${userCityText}">
						</div>
					</div>
					
					<div id="divProvincePar2">
						<spring:message code="user.province" var="userProvinceText" text="user.province not found"/>
						<label class="col-sm-2 control-label">${userProvinceText}</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="inputProvincePar2" placeholder="${userProvinceText}">
						</div>
					</div>	
				</div>
				
				<div id="divPar2_7" class="form-group">
					<div id="divAddressPar2">
						<spring:message code="user.address" var="userAddressText" text="user.address not found"/>
						<label class="col-sm-2 control-label">${userAddressText}</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="inputAddressPar2" placeholder="${userAddressText}">
						</div>
					</div>
				</div>
			</fieldset>
		</fieldset>
	</div>
	
</fieldset>

<script>
	
	var parentFocus = null;
	var parent = null;
	var isNewParent = [true,true];
	var hasParent = [true,true];
	var textParent = [' (' + '<spring:message code="parent1.title" text="parent1.title not found"/>' + ')',' (' + '<spring:message code="parent2.title" text="parent2.title not found"/>' + ')'];
	var checksParent = [{id:true,pass:true},{id:true,pass:true}];
	var table = null;
	
	$(document).ready(function() {
		
		
		if($("#inputIdPerson").val() != ""){
			hasParent = [false, false];
			$("#divParent1").hide();
			$("#divParent2").hide();
			$("#divCopyAddress").hide();
			table =  $('#tableParents').DataTable( {
				"paging": false,
			    "ordering": false,
			    "info": false,
			    "searching": false,
			    fixedHeader: {
			        header: true,
			        footer: false
			    }
			} );
			if(!editable){
				$(".deleteUser").hide()
			} else {
				if(table.rows().count() == 0){
					hasParent = [true, true];
					$("#divParent1").show();
					$("#divParent2").show();
				} else if(table.rows().count() == 1){
					hasParent = [false, true];
					$("#divParent2").show();
					$("#inputIdPar1").val(table.row(0).data()[0]);
				}
			}
			
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
		
		if($("#selectSex").val() === ""){
			lstValidatorPerson.push("${userSexText}");
			$("#divSex").addClass("has-error");				
		}
		
		if($("#inputBirthdate").val() === ""){
			lstValidatorPerson.push("${userBirthdateText}");
			$("#divBirthdate").addClass("has-error");				
		}
		
		if($("#selectCourse").val() === ""){
			lstValidatorPerson.push("${userCourseText}");
			$("#divCourse").addClass("has-error");				
		}
		
		if(hasParent[0] && isNewParent[0]){
			lstValidatorPerson = lstValidatorPerson.concat(validatorParent(1));
		}
		
		if(hasParent[1] && isNewParent[1]){
			lstValidatorPerson = lstValidatorPerson.concat(validatorParent(2));
		}
		
		return lstValidatorPerson;
	}
	
	function validatorParent(index){
		
		var listValidatorParent = [];
		
		if($("#inputIdPar" + index).val() === ""){
			listValidatorParent.push($("#inputIdPar" + index).attr('placeholder') + textParent[index-1]);
			$("#divIdPar" + index).addClass("has-error");			
		}
		if($("#inputPasswordPar" + index).val() === ""){
			listValidatorParent.push($("#inputPasswordPar" + index).attr('placeholder') + textParent[index-1]);
			$("#divPasswordPar" + index).addClass("has-error");			
		}
		if($("#inputConfirmPasswordPar" + index).val() === ""){
			listValidatorParent.push($("#inputConfirmPasswordPar" + index).attr('placeholder') + textParent[index-1]);
			$("#divConfirmPasswordPar" + index).addClass("has-error");			
		}		
		if($("#inputNamePar" + index).val() === ""){
			listValidatorParent.push($("#inputNamePar" + index).attr('placeholder') + textParent[index-1]);
			$("#divNamePar" + index).addClass("has-error");			
		}
		if($("#inputSurname1Par" + index).val() === ""){
			listValidatorParent.push($("#inputSurname1Par" + index).attr('placeholder') + textParent[index-1]);
			$("#divSurname1Par" + index).addClass("has-error");				
		}		
		if($("#selectDNITypePar" + index).val() === ""){
			listValidatorParent.push("${userDNITypeText}" + textParent[index-1]);
			$("#divDNITypePar" + index).addClass("has-error");				
		}		
		if($("#inputDNIPar" + index).val() === ""){
			listValidatorParent.push($("#inputDNIPar" + index).attr('placeholder') + textParent[index-1]);
			$("#divDNIPar" + index).addClass("has-error");				
		}
		
		return listValidatorParent;
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
	
	$("#selectSex").change(function(){
		if($("#selectSex").val() != ""){
			$("#divSex").removeClass("has-error");
		}
	});
	
	$("#inputBirthdate").change(function(){
		if($("#inputBirthdate").val() != ""){
			var pattern = new RegExp("^[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]$");
			if(pattern.test($("#inputBirthdate").val())){
				$("#divBirthdate").removeClass("has-error");
			} else {
				$("#inputBirthdate").val("");
				$("#divBirthdate").addClass("has-error");
			}
		}
	});
	
	$("#spanBirthdate").click(function(){
		$("#divBirthdate").removeClass("has-error");
	})
	
	$("#selectCourse").change(function(){
		if($("#selectCourse").val() != ""){
			$("#divCourse").removeClass("has-error");
		}
	});
	
	$("#inputIdPar1").change(function(){
		if($("#inputIdPar1").val().trim() != ""){
			if(checkOthersUsersPar(1)){
				checkIdUserParent($("#inputIdPar1").val().trim(),1);
			}
		}
	});
	
	$("#inputPasswordPar1").change(function(){
		if($("#inputConfirmPasswordPar1").val() !== "" || $(this).val() === "")
			checkPassParent($(this).val(),$("#inputConfirmPasswordPar1").val(),1);
	});
	
	$("#inputConfirmPasswordPar1").change(function(){
		checkPassParent($("#inputPasswordPar1").val(),$(this).val(),1);
	});
	
	$("#inputNamePar1").change(function(){
		if($("#inputNamePar1").val() != ""){
			$("#divNamePar1").removeClass("has-error");
		}
	});
	
	$("#inputSurname1Par1").change(function(){
		if($("#inputSurname1Par1").val() != ""){
			$("#divSurname1Par1").removeClass("has-error");
		}
	});
	
	$("#selectDNITypePar1").change(function(){
		if($("#selectDNITypePar1").val() != ""){
			$("#divDNITypePar1").removeClass("has-error");
		}
	});
	
	$("#inputDNIPar1").change(function(){
		if($("#inputDNIPar1").val() != ""){
			$("#divDNIPar1").removeClass("has-error");
		}
	});
	
	$("#inputIdPar2").change(function(){
		if($("#inputIdPar2").val().trim() != ""){
			if(checkOthersUsersPar(2)){
				checkIdUserParent($("#inputIdPar2").val().trim(),2);
			}
		}
	});
	
	$("#inputPasswordPar2").change(function(){
		if($("#inputConfirmPasswordPar2").val() !== "" || $(this).val() === "")
			checkPassParent($(this).val(),$("#inputConfirmPasswordPar2").val(),2);
	});
	
	$("#inputConfirmPasswordPar2").change(function(){
		checkPassParent($("#inputPasswordPar2").val(),$(this).val(),2);
	});
	
	$("#inputNamePar2").change(function(){
		if($("#inputNamePar2").val() != ""){
			$("#divNamePar2").removeClass("has-error");
		}
	});
	
	$("#inputSurname1Par2").change(function(){
		if($("#inputSurname1Par2").val() != ""){
			$("#divSurname1Par2").removeClass("has-error");
		}
	});
	
	$("#selectDNITypePar2").change(function(){
		if($("#selectDNITypePar2").val() != ""){
			$("#divDNITypePar2").removeClass("has-error");
		}
	});
	
	$("#inputDNIPar2").change(function(){
		if($("#inputDNIPar2").val() != ""){
			$("#divDNIPar2").removeClass("has-error");
		}
	});

	$("#buttonCopyAddress").click(function(){
		$("#inputCpPar1").val($("#inputCp").val());
		$("#inputAddressPar1").val($("#inputAddress").val());
		$("#inputCityPar1").val($("#inputCity").val());
		$("#inputProvincePar1").val($("#inputProvince").val());
		
		$("#inputCpPar2").val($("#inputCp").val());
		$("#inputAddressPar2").val($("#inputAddress").val());
		$("#inputCityPar2").val($("#inputCity").val());
		$("#inputProvincePar2").val($("#inputProvince").val());
	});
	
	
	$("#checkboxPar1").change(function(){
		if($("#checkboxPar1").is(':checked')){
			$("#checkboxPar2").prop("disabled",false);
			$("#fieldsetParent1").show();
			hasParent[0] = true;
		} else {
			hasParent[0] = false;
			hasParent[1] = false;
			$("#fieldsetParent1").hide();
			$("#fieldsetParent2").hide();
			$("#checkboxPar2").prop("checked",false);
			$("#checkboxPar2").prop("disabled",true);
			
		}
	})
	
	$("#checkboxPar2").change(function(){
		if($("#checkboxPar2").is(':checked')){
			$("#fieldsetParent2").show();
			hasParent[1] = true;
		} else {
			$("#fieldsetParent2").hide();
			hasParent[1] = false;
		}
	})
	
	function getPerson(){
		var data = {};
		data.student = {
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
			sex : $("#selectSex").val(),
			birthdate : $('#birthdateAction').data("DateTimePicker").date(),
			address : $("#inputAddress").val(),
			city : $("#inputCity").val(),
			province : $("#inputProvince").val(),
			cp : $("#inputCp").val(),
			course : $("#selectCourse").val(),
			user : getUser()
		};
		
		if(hasParent[0]){
			if(isNewParent[0]){
				data.parent1 = {
					id : $("#inputIdPersonPar1").val(),
					idUser : $("#inputIdPar1").val(),
					name : $("#inputNamePar1").val(),
					surname1 : $("#inputSurname1Par1").val(),
					surname2 : $("#inputSurname2Par1").val(),
					email : $("#inputEmailPar1").val(),
					telephone1 : $("#inputTelephone1Par1").val(),
					telephone2 :  $("#inputTelephone2Par1").val(),
					enabled : true,
					dniType : $("#selectDNITypePar1").val(),
					dni : $("#inputDNIPar1").val(),
					address : $("#inputAddressPar1").val(),
					city : $("#inputCityPar1").val(),
					province : $("#inputProvincePar1").val(),
					cp : $("#inputCpPar1").val(),
					user : {
						idUser : $("#inputIdPar1").val(),
						password : $("#inputPasswordPar1").val(),
						role : "ROLE_PAR",
						enabled : true
					}
				};
			} else {
				data.parent1 = {id : $("#inputIdPersonPar1").val()}
			}
		}
		
		if(hasParent[1]){
			if(isNewParent[1]){
				data.parent2 = {
					id : $("#inputIdPersonPar2").val(),
					idUser : $("#inputIdPar2").val(),
					name : $("#inputNamePar2").val(),
					surname1 : $("#inputSurname1Par2").val(),
					surname2 : $("#inputSurname2Par2").val(),
					email : $("#inputEmailPar2").val(),
					telephone1 : $("#inputTelephone1Par2").val(),
					telephone2 :  $("#inputTelephone2Par2").val(),
					enabled : true,
					dniType : $("#selectDNITypePar2").val(),
					dni : $("#inputDNIPar2").val(),
					address : $("#inputAddressPar2").val(),
					city : $("#inputCityPar2").val(),
					province : $("#inputProvincePar2").val(),
					cp : $("#inputCpPar2").val(),
					user : {
						idUser : $("#inputIdPar2").val(),
						password : $("#inputPasswordPar2").val(),
						role : "ROLE_PAR",
						enabled : true
					}
				};
			} else {
				data.parent2 = {id : $("#inputIdPersonPar2").val()}
			}
		}
		
		return data;
	}
	
	function getUrl(){
		return "${urlUpsert}"
	}
	
	$('#birthdateAction').datetimepicker({
	    format: 'DD/MM/YYYY',
	    locale: '${locale}'
	});

	$("#iconSearchPar1").click(function(){
		parent = null;
		parentFocus = 1;
		modal("modal/searchParent",fillParent,null);
	});
	
	$("#iconSearchPar2").click(function(){
		parent = null;
		parentFocus = 2;
		modal("modal/searchParent",fillParent,null);
	});
	
	function fillParent(){
		if(parent == null){
			isNewParent[parentFocus-1] = true;
			$("#fieldsetParent" + parentFocus).prop("disabled",false);
			$("#fieldsetNewPar" + parentFocus).show();
			$("#inputIdPar" + parentFocus).val("");
			$("#inputIdPersonPar" + parentFocus).val("");
			$("#inputNamePar" + parentFocus).val("");
			$("#inputSurname1Par" + parentFocus).val("");
			$("#inputSurname2Par" + parentFocus).val("");
		}else {
			if($("#inputIdPar1").val() == parent.idUser || $("#inputIdPar2").val() == parent.idUser){
				alert('<spring:message code="parent.id.select" text="parent.id.select not found" />',null)
			}else {
				isNewParent[parentFocus-1] = false;
				$("#fieldsetParent" + parentFocus).prop("disabled",true);
				$("#fieldsetNewPar" + parentFocus).hide();
				$("#inputIdPar" + parentFocus).val(parent.idUser);
				$("#divIdPar" + parentFocus).removeClass("has-error");
				$("#inputIdPersonPar" + parentFocus).val(parent.id);
				$("#inputNamePar" + parentFocus).val(parent.name);
				$("#divNamePar" + parentFocus).removeClass("has-error");
				$("#inputSurname1Par" + parentFocus).val(parent.surname1);
				$("#divSurname1Par" + parentFocus).removeClass("has-error");
				$("#inputSurname2Par" + parentFocus).val(parent.surname2);
			}
		}
		
	}
	
	function checkSavePerson(){
		return checkParent(0) && checkParent(1);
	}
	
	function checkParent(index){
		return !hasParent[index] || !isNewParent[index] || (checksParent[index].id && checksParent[index].pass);
	}
	
	function checkPassParent(pass, confirmPass, index){
		if(pass === confirmPass){
			$("#spanErrorPasswordPar" + index).text("");
			$("#divPasswordPar" + index).removeClass("has-error");
			$("#divConfirmPasswordPar" + index).removeClass("has-error");
			checksParent[index-1].pass = true;
			checkSaveButton();
		} else {
			$("#spanErrorPasswordPar" + index).text('<spring:message code="user.error.password" text="user.error.password not found"/>');
			$("#divPasswordPar" + index).addClass("has-error");
			$("#divConfirmPasswordPar" + index).addClass("has-error");
			$("#buttonAddPar" + index).attr("disabled",true);
			checksParent[index-1].pass = false;
			checkSaveButton();
		}
	}
	
	function checkIdUserParent(idUser, index){
		$.ajax({
			url : '${urlCheckUser}' + idUser,
			type: "GET", 
			success : function(error) {
				if (error == null || error == ""){
					$("#divIdPar" + index).removeClass("has-error");
					checksParent[index-1].id = true;
					checkSaveButton();
				} 
				else {
					$("#divIdPar" + index).addClass("has-error");
					checksParent[index-1].id = false;
					alert(error, null);
					checkSaveButton();
				} 
			}
		});		
	}
	
	function checkOthersUsers(){
		if($("#inputId").val() == $("#inputIdPar1").val() || $("#inputId").val() == $("#inputIdPar2").val()){
			alert('<spring:message code="user.id.exist" text="user.id.exist not found"/>', null);
			$("#divId").addClass("has-error");
			idCorrect = false;
			checkSaveButton();
			return false;			
		}
		return true;
	}
	
	function checkOthersUsersPar(index){
		if($("#inputIdPar" + index).val() == $("#inputId").val() || $("#inputIdPar1").val() == $("#inputIdPar2").val()){
			alert('<spring:message code="user.id.exist" text="user.id.exist not found"/>', null);
			$("#divIdPar" + index).addClass("has-error");
			checksParent[index-1].id = false;
			checkSaveButton();
			return false;			
		}
		return true;
	}
	
	function confirmEditParent(idUser){
		var url = "";
		if(editable){
			url = "${urlEditUser}" + idUser;
		} else {
			url = "${urlViewUser}" + idUser;
		} 
		confirm('<spring:message code="common.loseChanges" text="common.loseChanges not found"/>',function(){redirect(url)},null);
	}
	
	function confirmDeleteUser(idUSerParent){
		confirm('<spring:message code="user.delete.quest" text="user.delete.quest not found" />', function(){deleteUser(idUSerParent)}, null);
	}
	
	function deleteUser(idUSerParent){
		$.ajax({
			dataType : "text",
			type:"GET", 
			url : "${urlDeleteUser}" + idUSerParent,
			success : function(response) {
				alert(response, reload);
			},
			error: function(){
				alert('<spring:message code="user.delete.error" text="user.delete.error not found" />', reloadTable);
			} 
		});			
	}
	
</script>