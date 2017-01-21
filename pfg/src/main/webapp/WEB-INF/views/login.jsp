<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Login Page</title>
		<link href="static/css/bootstrap.min.css" rel="stylesheet" 	type="text/css">
		<link href="static/css/login.css" rel="stylesheet" type="text/css">
	
	
	</head>

	<body>
		<div class="container">
			<div class="box-login">
	      		<form class="form-login" role="form"  name="loginForm" action="login" method="POST">
		        	<h2 class="form-login-heading">Centro Educativo PFG</h2>
		        	<c:if test="${not empty error}">
						<div id="divError" class="alert alert-danger">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div id="divMsg" class="alert alert-info">${msg}</div>
					</c:if>
			        <div class="input-group">
			        	<label class="input-group-addon"><i class="glyphicon glyphicon-user"></i></label>
			        	<input type="text" id="inputUser" class="form-control" name="username" placeholder="Usuario" required autofocus>
			        </div>
			        <div class="input-group">
			        	<label class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></label>
			        	<input type="password" id="inputPassword" class="form-control" name="password" placeholder="Contraseña" required autofocus>
			        </div>
			        <button id="buttonSubmit"class="btn btn-lg btn-primary btn-block" type="submit">Acceder</button>
			        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		      	</form>
			</div>
    	</div> 

		<script src="static/js/jquery-3.1.1.min.js"></script>
		<script src="static/js/bootstrap.min.js"></script>
	</body>
</html>