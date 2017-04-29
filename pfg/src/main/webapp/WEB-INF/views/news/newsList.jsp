<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/news/getUpdateModal/" var="urlUpdateModal" />
<spring:url value="/news/addCommentModal" var="urlAddCommentModal" />
<spring:url value="/news/updateCommentModal/" var="urlUpdateCommentModal" />
<spring:url value="/news/saveComment?${_csrf.parameterName}=${_csrf.token}" var="urlSaveComment" />
<spring:url value="/news/deleteNews/" var="urlDeleteNews" />
<spring:url value="/news/deleteComment/" var="urlDeleteComment" />

<c:forEach items="${lstNews}" var="news">
	<div id="divNews" class="box-news col-sm-12">
		<div class="row-fluid">
			<div class="col-sm-6 text-muted">
				<small> ${news.person.fullName} - <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${news.date}" /> </small>
			</div>
			<div class="col-sm-6 text-right">
   				<label id="commentNew" class="cursorPointer iconTable" onclick="addComment('${news.id}')"><i class="glyphicon glyphicon-comment" title='<spring:message code="news.comment.add" text="news.comment.add not found" />'></i></label>
   				<c:if test="${pageContext.request.isUserInRole('ADM') || (pageContext.request.isUserInRole('TCH') && news.user == sessionScope.sessionUser)}">
					<c:if test="${news.user == sessionScope.sessionUser}">
						<label id="editNew" class="cursorPointer iconTable" onclick="editNews('${news.id}')"><i class="glyphicon glyphicon-pencil" title='<spring:message code="common.edit" text="common.edit not found" />'></i></label>
					</c:if>
	   				<label id="deleteNew" class="cursorPointer iconTable" onclick="confirmDeleteNews('${news.id}')"><i class="glyphicon glyphicon-trash" title='<spring:message code="common.delete" text="common.delete not found" />'></i></label>
   				</c:if>
			</div>
		</div>
		<div class="row-fluid">
			<b><h4>${news.title}</h4></b>
		</div>
		<div class="row-fluid">
			<p>${news.text}</p>
		</div>
		<c:if test="${not empty news.comments}">
			<hr>
			<div class="row-fluid"><h4><spring:message code="news.comment" text="news.comment not found" /></h4></div>
			<div class="comments-box">
				<c:forEach items="${news.comments}" var="comment">
					<div class="col-sm-6 text-muted">
						<small> ${comment.person.fullName} - <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${comment.date}" /> </small>
					</div>
					<div class="col-sm-6 text-right">
		   				<c:if test="${pageContext.request.isUserInRole('ADM') || (pageContext.request.isUserInRole('TCH') && news.user == sessionScope.sessionUser || comment.user == sessionScope.sessionUser)}">
			   				<c:if test="${comment.user == sessionScope.sessionUser}">
								<label id="editComment" class="cursorPointer iconTable" onclick="editComment('${news.id}','${comment.id}')"><i class="glyphicon glyphicon-pencil" title='<spring:message code="common.edit" text="common.edit not found" />'></i></label>
							</c:if>
			   				<label id="deleteComment" class="cursorPointer iconTable" onclick="confirmDeleteComment('${comment.id}')"><i class="glyphicon glyphicon-trash" title='<spring:message code="common.delete" text="common.delete not found" />'></i></label>
		   				</c:if>
					</div>
					<div class="clearfix"></div>
					<div class="row-fluid">
						<p>${comment.text}</p>
					</div>
				</c:forEach>
			</div>
		</c:if>
	</div>
</c:forEach>


<script>

var comment = {
	id : null,
	user : "${sessionScope.sessionUser}",
	news : null,
	text : null
};	

function restoreComment(){
	comment = {
		id : null,
		user : "${sessionScope.sessionUser}",
		news : null,
		text : null
	};	
}

function editNews(idNews){
	news.id = idNews;
	modal("${urlUpdateModal}" + idNews, saveNews, restoreNews);
};

function addComment(idNews){
	comment.news = idNews;
	modal("${urlAddCommentModal}", saveComment, restoreComment);
};

function editComment(idNews, idComment){
	comment.id = idComment;
	comment.news = idNews;
	modal("${urlUpdateCommentModal}" + idComment, saveComment, restoreComment);
};
	
function saveComment(){
	$.ajax({
		url : "${urlSaveComment}",
		type:"POST", 
		data: JSON.stringify(comment),
		contentType: "application/json",
		success : function(text) {
			alert(text,getNewsSubject);
			restoreComment()
		},
		error: function(){
			alert('<spring:message code="news.comment.save.error" text="news.comment.save.error"/>', getNewsSubject);
			restoreComment()
		}
	});		
}

function confirmDeleteNews(idNews){
	confirm('<spring:message code="news.delete.quest" text="news.delete.quest"/>', function(){deleteNews(idNews);}, null);
}

function deleteNews(idNews){
	$.ajax({
		url : "${urlDeleteNews}" + idNews,
		type:"GET", 
		success : function(response) {
			alert(response, getNewsSubject);
		},
		error: function(){
			alert('<spring:message code="news.delete.error" text="news.delete.error not found"/>',getNewsSubject);
		}
	});		
}

function confirmDeleteComment(idComment){
	confirm('<spring:message code="news.comment.delete.quest" text="news.comment.delete.quest"/>', function(){deleteComment(idComment);}, null);
}

function deleteComment(idComment){
	$.ajax({
		url : "${urlDeleteComment}" + idComment,
		type:"GET", 
		success : function(response) {
			alert(response, getNewsSubject);
		},
		error: function(){
			alert('<spring:message code="news.comment.delete.error" text="news.comment.delete.error not found"/>',getNewsSubject);
		}
	});		
}
</script>