<%@ include file="/WEB-INF/views/common/include.jsp" %>

<div class="row-fluid">
	<div class="col-sm-offset-2 col-sm-8">
		<div class="text-center home-img">
			<p><spring:message code="home.hello" text="home.hello not found" /><p>
			<br>
			${sessionScope.sessionUserFullName} <br>
			(<spring:message code="${sessionScope.sessionUserRole.name}" text="${sessionScope.sessionUserRole.name} not found" />)
		</div>
	</div>
</div>
