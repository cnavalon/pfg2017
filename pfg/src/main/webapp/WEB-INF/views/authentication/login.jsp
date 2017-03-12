<%@ include file="/WEB-INF/views/common/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title><spring:message code="login.title" text="login.title not found" /></title>
		<link href="static/css/bootstrap.min.css" rel="stylesheet" 	type="text/css">
		<link href="static/css/main.css" rel="stylesheet" type="text/css">
		<link href="static/css/login.css" rel="stylesheet" type="text/css">
	
	
	</head>

	<body >
	<div class="logo-login text-center containerImage">
		<img src="static/images/logo.png" class="img-rounded">
	</div>
		<div class="container-fluid ">
			<div class="box-center bg-2">
      			
	      		<form class="form-login" role="form"  name="loginForm" action="login" method="POST">
		        	<h2 class="form-login-heading logo-text"><spring:message code="app.name" text="app.name not found" /></h2>
		        	<c:if test="${not empty error}">
						<div id="divError" class="alert alert-danger"><spring:message code="${error}" text="${error} not found" /></div>
					</c:if>
					<c:if test="${not empty msg}">
						<div id="divMsg" class="alert alert-info"><spring:message code="${msg}" text="${msg} not found" /></div>
					</c:if>
			        <div class="input-group">
			        	<label class="input-group-addon"><i class="glyphicon glyphicon-user"></i></label>
			        	<input type="text" id="inputUser" class="form-control" name="username" placeholder='<spring:message code="login.user" text="login.user not found" />' required autofocus>
			        </div>
			        <div class="input-group">
			        	<label class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></label>
			        	<input type="password" id="inputPassword" class="form-control" name="password" placeholder='<spring:message code="login.password" text="login.password not found" />' required autofocus>
			        </div>
			        <button id="buttonSubmit"class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="login.button" text="login.button not found" /></button>
			        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		      	</form>
			</div>
    	</div> 

		<script src="static/js/jquery-3.1.1.min.js"></script>
		<script src="static/js/bootstrap.min.js"></script>
	</body>
</html>
