<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/test/all/lang" var="urlTestAllLang" />
<spring:url value="/test/adm/lang" var="urlTestAdmLang" />
<spring:url value="/test/tch/lang" var="urlTestTchLang" />

<nav class="navbar navbar-default secondbg">
	<ul class="nav navbar-nav">
		<li class="dropdown">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
				Test <span class="caret"></span>
			</a>
			<ul class="dropdown-menu secondbg">
				<li><a href="${urlTestAllLang}">Test All</a></li>
				<li><a href="${urlTestAdmLang}">Test Adm</a></li>
				<li><a href="${urlTestTchLang}">Test Tch</a></li>
<!-- 						<li role="separator" class="divider"></li> -->
			</ul>
		</li>
	</ul>
</nav>
