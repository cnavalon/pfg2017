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
		
        <script src="static/js/jquery-3.1.1.min.js"></script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/main.js"></script>
		<script src="static/js/main.js"></script>
		<script src="static/js/jquery.dataTables.min.js"></script>
<!-- 		<script src="static/js/dataTables.bootstrap.min.js"></script> -->
		<script src="static/js/jquery.blockUI.js"></script>
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
        
        <script type="text/javascript">
			function confirm(heading, question, callback, callbackCancel){
				var cancelButtonTxt = '<spring:message code="common.cancel" text="common.cancel not found" />';
				var okButtonTxt = '<spring:message code="common.ok" text="common.ok not found" />';
				confirmModal(heading, question, callback, callbackCancel, okButtonTxt, cancelButtonTxt);
				
			}
			
			function alert(heading, question, callback){
				var okButtonTxt = '<spring:message code="common.ok" text="common.ok not found" />';
				alertModal(heading, question, callback, okButtonTxt);
				
			}
			
		</script>
    
	</body>
</html>
