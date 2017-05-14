<%@ include file="/WEB-INF/views/common/include.jsp" %>
<%@ page session="true" %>

<spring:url value="/" var="urlHome" />

<header>
	<div id="divHeader" class="container-fluid">
		<div class="row-fluid">
			<div class="col-sm-6" >
				<label class="cursorPointer" onclick="redirect(${urlHome})" style="display: inline-flex;">
					<span class="logo-img containerImage"><img src="static/images/logo.png"></img></span>   
					<h3>
						<span class="logo-text"><strong><spring:message code="app.name" text="app.name not found" /></strong></span>
					</h3>
				</label>
			</div>
			<div class="col-sm-6 text-right">
				<div >
					<c:set var="urlQueryString" value="${requestScope['javax.servlet.forward.query_string']}"/>
					<c:set var="index" value="${fn:indexOf(urlQueryString, 'lang')}"/>
					<c:choose>
						<c:when test="${index ne '-1'}">
							<c:set var="usedQueryString" value="${fn:substring(urlQueryString, 0, index - 1)}"/>
						</c:when>
						<c:otherwise>
							<c:set var="usedQueryString" value="${urlQueryString}"/>
						</c:otherwise>
					</c:choose>
					<div class=" text-right">
						<a href="${requestScope['javax.servlet.forward.request_uri']}?${usedQueryString}&lang=es" id="changeLanguageToEn"><span class="flag flag-es"></span></a>
						<a href="${requestScope['javax.servlet.forward.request_uri']}?${usedQueryString}&lang=en" id="changeLanguageToEs"><span class="flag flag-gb"></span></a>
						<a href="<c:url value="/logout"/>" id="logout"><span class="glyphicon glyphicon-off"></span></a>
					</div>
				</div>
				<div>
					<span class="glyphicon glyphicon-user"></span> 
					<span><strong id="sessionUserName">${sessionScope.sessionUserFullName}</strong></span>
				</div> 
				<div>
					<small>
						<span><spring:message code="${sessionScope.sessionUserRole.name}" text="${sessionScope.sessionUserRole.name} not found" /></span>
					</small>
				</div>
			</div>
		</div>
	</div>
</header>

