<%@ include file="/WEB-INF/views/common/include.jsp" %>
<%@ page session="true" %>

<spring:url value="/" var="urlHome" />

<header>
	<div class="row-fluid">
		<div class="col-sm-6" >
			<h3>
				<span class="cursorPointer" onclick="goToHome()">
					<span class="glyphicon glyphicon-education icon-lg"></span>   
					<span class="logo-text"><strong><spring:message code="app.name" text="app.name not found" /></strong></span>
				</span> 
			</h3>
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
				<span><strong id="sessionUserName">${sessionScope.user}</strong></span>
			</div> 
			<div>
				<small>
					<c:forEach items="${sessionScope.roles}" var="role" varStatus="status">
						<span><spring:message code="${role.name}" text="${role.name} not found" />
						<c:if test="${not status.last}"> - </c:if>
						</span>
					</c:forEach>
				</small>
			</div>
		</div>
	</div>
</header>

<script>
	function goToHome(){
		location.href = "${urlHome}";
	}
</script>