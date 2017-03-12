<%@page contentType="text/html;charset=UTF-8"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,  initial-scale=1, maximum-scale=1">
		
		<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
		
		<title>Centro Educativo PFG</title>
		
		<meta name="_csrf" content="${_csrf.token}"/>
		<meta name="_csrf_header" content="${_csrf.headerName}"/>
		
		<link href="static/css/bootstrap.min.css" rel="stylesheet" 	type="text/css">
		<link href="static/css/main.css" rel="stylesheet" type="text/css">
		<link href="static/css/flags.css" rel="stylesheet" type="text/css">
		<link href="static/css/flags.css" rel="stylesheet" type="text/css">
		<link href="static/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
<!-- 		<link href="static/css/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css"> -->
		<link href="static/css/menu.css" rel="stylesheet" type="text/css">
		<link href="static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
		
        <script src="static/js/jquery-3.1.1.min.js"></script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/main.js"></script>
		<script src="static/js/main.js"></script>
		<script src="static/js/jquery.dataTables.min.js"></script>
<!-- 		<script src="static/js/dataTables.bootstrap.min.js"></script> -->
		<script src="static/js/jquery.blockUI.js"></script>
		<script src="static/js/moment-with-locales.min.js"></script>
		<script src="static/js/bootstrap-datetimepicker.min.js"></script>
	</head>

	<body>
        

		
		<header id="header">
       		<tiles:insertAttribute name="header" />
        </header>
     
        <section id="menu">
        	<div id="divBody" class="container-fluid">
           		<tiles:insertAttribute name="menu" />
  			</div>
        </section>
	             
        <section id="body">
        	<div id="divBody" class="container-fluid">
            	<tiles:insertAttribute name="body" />
           	</div>
        </section>
        
    
	</body>
</html>

<script type="text/javascript">
	csrf = "?${_csrf.parameterName}=${_csrf.token}";
</script>
