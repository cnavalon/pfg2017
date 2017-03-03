<%@ include file="/WEB-INF/views/common/include.jsp" %>
<spring:url value="/users/emp/users" var="urlUsersSearch" />

	
<nav class="navbar navbar-default">
	<div class="row-fluid">
	
		<ul id="menu" class="nav navbar-nav">
			<c:if test="${pageContext.request.isUserInRole('ADM') || pageContext.request.isUserInRole('TCH')}">
				<li class="dropdown" id="menuUsers">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="menu.users" text="menu.users not found" /> <span class="caret"></span></a>
			        <ul class="dropdown-menu">
			        	<li><a href="${urlUsersSearch}" id="menuUsers_search" ><spring:message code="menu.users.search" text="menu.users.search not found" /></a></li>
			        </ul>
				</li>
<!-- 				<li id="menuUsers" onclick="selectLink(this)"> -->
<%-- 					<a href="${urlUsersList}"></a> --%>
<!-- 				</li> -->
			</c:if>
		</ul>
	</div>
</nav>

<script>
$(document).ready(function() {
	setActiveMenuOption();
});

function setActiveMenuOption(){
 	var baseUrl = '${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/';
    var currentUrl = window.location.href;
		// remove base url from current url
 	var path = currentUrl.replace(baseUrl, '');
 	
 	// No menu option selected
 	if(path.trim() == ''){
 		return;
 	}
 	
 	var paths = path.split('/');
 	var uniquePathFound = false;
 	var numPath = 0;
 	var uniquePath = "${pageContext.request.contextPath}";
 	var menuOption = null;
 	
 	// iterate url paths until a unique menu option is found starting with the same path
 	while (!uniquePathFound && numPath < paths.length) {
 		
     	// increase path
     	uniquePath = uniquePath + '/' +paths[numPath];
        
     	// Find menu options starting with the path
		menuOption = $('ul[id="menu"] a[href="'+uniquePath+'"]');
		if (menuOption.length == 1){
			uniquePathFound = true;
		}else{
			numPath++;
		}
 	}
	     	
 	// if unique menu option found 
	if (menuOption != null && menuOption.length){
		var id = menuOption.attr('id').split("_")[0];
		// change css style of the menu option selected
		$("#"+ id).addClass('active');
	}
}
</script>


