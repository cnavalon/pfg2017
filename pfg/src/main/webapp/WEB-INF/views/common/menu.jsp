<%@ include file="/WEB-INF/views/common/include.jsp" %>
<%-- <spring:url value="/test/all/lang" var="urlTestAllLang" /> --%>
<%-- <spring:url value="/test/adm/lang" var="urlTestAdmLang" /> --%>
<%-- <spring:url value="/test/tch/lang" var="urlTestTchLang" /> --%>
<spring:url value="/users/adm/list" var="urlUsersList" />

<nav class="navbar navbar-default navbar-static-top">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
<!-- 			<li class="dropdown"> -->
<!-- 				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> -->
<!-- 					Test <span class="caret"></span> -->
<!-- 				</a> -->
<!-- 				<ul class="dropdown-menu"> -->
<%-- 					<li><a href="${urlTestAllLang}">Test All</a></li> --%>
<%-- 					<li><a href="${urlTestAdmLang}">Test Adm</a></li> --%>
<%-- 					<li><a href="${urlTestTchLang}">Test Tch</a></li> --%>
<!-- 					<li role="separator" class="divider"></li> -->
<!-- 				</ul> -->
					
<!-- 			</li> -->
			<c:if test="${pageContext.request.isUserInRole('ADM')}">
				<li>
					<a href="${urlUsersList }"><spring:message code="menu.users" text="menu.users not found" /></a>
				</li>
			</c:if>
		</ul>
	</div>
</nav>
