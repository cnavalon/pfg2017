<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/news/getSubjects/" var="urlSubjects" />
<spring:url value="/news/getNewsInfo/" var="urlNewsInfo" />
<spring:url value="/news/getNews/" var="urlNews" />
<spring:url value="/news/getAddModal" var="urlAddModal" />
<spring:url value="/news/saveNew?${_csrf.parameterName}=${_csrf.token}" var="urlSaveNew" />


<div class="row-fluid">
	<h3 class="title"><spring:message code="news.title.news" text="news.title.news not found" /></h3>
</div>

<c:if test="${pageContext.request.isUserInRole('ADM') || pageContext.request.isUserInRole('TCH')}">
	<div class="row-fluid">
		<div class="col-sm-offset-10 col-sm-1">
			<a class="btn btn-primary btn-block btn-sm" id="addNews" disabled>
				<i class="glyphicon glyphicon-plus" rel="tooltip"></i> <spring:message code="common.add" text="common.add not found" />
			</a>
		</div>
	</div>
</c:if>

<form class="form-horizontal" action="">
	<div class="row-fluid" id="divSelect">
		<div class="form-group">
			<label class="col-sm-2 control-label"><spring:message code="news.group" text="news.group not found"/></label>
			<div class="col-sm-3">
				<select class="form-control" id="selectGroup">
					<c:forEach items="${lstGroups}" var="group">
						<option value="${group.id}" label="${group.course.name} ${group.letter}">${group.course.name} ${group.letter}</option>
					</c:forEach>
				</select>
			</div>
			<label class="col-sm-2 control-label"><spring:message code="news.subject" text="news.subject not found"/></label>
			<div class="col-sm-3">
				<select class="form-control" id="selecSubject">
				</select>
			</div>
		</div>
	</div>
	
	<div class="row-fluid">
		<div id="divNews" class="col-sm-offset-2 col-sm-8 noPadding div-news">
		</div>
	</div>
	
	<div class="row-fluid">
		<div class="form-group ">
	    	<div class="col-sm-offset-2 col-sm-8 text-right">
	   			<div class="col-sm-2">
		      		<a id ="buttonPrevious" class="form-control btn btn-default" disabled><i class="glyphicon glyphicon-chevron-left" rel="tooltip"></i> <spring:message code="common.previous" text="common.previous not found"/></a>
	    		</div>
	    		<div class="col-sm-8"></div>
	    		<div class="col-sm-2">
		      		<a id ="buttonNext" class="form-control btn btn-default" disabled><spring:message code="common.next" text="common.next not found"/> <i class="glyphicon glyphicon-chevron-right" rel="tooltip"></i></a>
	    		</div>
	   		</div>
		</div>
	</div>
</form>


<script>
var page = 0;
var totalPages = 0;
const NEWS_FOR_PAGE = 5;
var news = {
	id : null,
	group : null,
	subject : null,
	user : "${sessionScope.sessionUser}",
	title : null,
	text : null,
}


$(document).ready(function() {
	loadSubjects();
});

$("#selectGroup").change(function(){
	restorePage();
	loadSubjects();
});

$("#selecSubject").change(function(){
	if($(this).val() != null && $(this).val() != ""){
		$("#addNews").removeAttr("disabled");
		getNewsSubject();
	} else {
		restorePage();
	}
});

$("#buttonNext").click(function(){
	page++;
	checkPagesButtons();
	loadNews();
});

$("#buttonPrevious").click(function(){
	page--;
	checkPagesButtons();
	loadNews();
});

function loadSubjects(){
	var group = $("#selectGroup").val();
	if(group != null && group != ""){
		$.ajax({
			url : "${urlSubjects}" + group,
			type:"GET", 
			success : function(lstSubjects) {
				if(lstSubjects != null && lstSubjects.length > 0){
					$("#selecSubject").empty();
					$("#selecSubject").append('<option value="" label=""></option>');
					for(var i=0; i < lstSubjects.length; i++){
						var subject = lstSubjects[i];
						var option = '<option value="' + subject.id + '" label="' + subject.name + '">' + subject.name +'</option>';
						$("#selecSubject").append(option);
					}
					orderOptions("#selecSubject");
				}
			},
			error: function(){
				alert('<spring:message code="news.subjects.error" text="news.subjects.error not found"/>', null);
			}
		});		
	}
}

function restorePage(){
	$("#divNews").empty();
	$("#addNews").attr("disabled",true);
	$("#buttonPrevious").attr("disabled",true);
	$("#buttonNext").attr("disabled",true);
	page = 0;
	totalPages = 0;
}

function getNewsSubject(){
	var group = $("#selectGroup").val();
	var subject = $("#selecSubject").val();
	page = 0;
	totalPages = 0;
	$.ajax({
		url : "${urlNewsInfo}" + group + "/" + subject,
		type:"GET", 
		success : function(info) {
			if(info > 0){
				totalPages = Math.ceil(info/NEWS_FOR_PAGE) - 1;
				checkPagesButtons();
				loadNews();
			} else {
				$("#divNews").empty();
				$("#divNews").append('<div id="divNews" class="box-news"><spring:message code="news.noNews" text="news.noNews not found"/></div>');
				checkPagesButtons();
			}
		},
		error: function(){
			alert('<spring:message code="news.news.error" text="news.news.error not found"/>', null);
		}
	});		
}

function loadNews(){
	var group = $("#selectGroup").val();
	var subject = $("#selecSubject").val();
	$("#divNews").empty();
	$("#divNews").load("${urlNews}" + group + "/" + subject + "/" + page);
}

function checkPagesButtons(){
	if(page > 0){
		$("#buttonPrevious").removeAttr("disabled");
	} else {
		$("#buttonPrevious").attr("disabled",true);
	}
	
	if(page < totalPages){
		$("#buttonNext").removeAttr("disabled");
	} else {
		$("#buttonNext").attr("disabled",true);
	}
}

$("#addNews").click(function(){
	modal("${urlAddModal}", saveNews, restoreNews);
});

function saveNews(){
	news.group = $("#selectGroup").val();
	news.subject = $("#selecSubject").val();
	
	$.ajax({
		url : "${urlSaveNew}",
		type:"POST", 
		data: JSON.stringify(news),
		contentType: "application/json",
		success : function(text) {
			alert(text,getNewsSubject);
			restoreNews();
		},
		error: function(){
			alert('<spring:message code="news.save.error" text="news.save.error not found"/>', getNewsSubject);
			restoreNews();
		}
	});			
};

function restoreNews(){
	news = {
		id : null,
		group : null,
		subject : null,
		user : "${sessionScope.sessionUser}",
		title : null,
		text : null,
	};
};

</script>