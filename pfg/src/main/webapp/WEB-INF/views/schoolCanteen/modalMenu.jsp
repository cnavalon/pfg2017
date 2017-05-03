<%@ include file="/WEB-INF/views/common/include.jsp" %>

<div id="dataModal" class="modal fade" role="dialog" aria-labelledby="dataConfirmLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false"> 
	<div class="modal-dialog modal-xl"> 
		<div class="modal-content"> 
	 		<div class="modal-header"> 		        			
	 			<h3 id="dataModalLabel"></h3> 
	 		</div> 
	 		<div id="dataModalBody" class="modal-body">
	 			<form id="menuForm" class="form-horizontal" method="post" enctype="form-data" action="" >
					<div id="divRow0" class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-3 text-center"><label><spring:message code="menu.first" text="menu.first not found"/></label></div>
						<div class="col-sm-3 text-center"><label><spring:message code="menu.second" text="menu.second not found"/></label></div>
						<div class="col-sm-3 text-center"><label><spring:message code="menu.dessert" text="menu.dessert not found"/></label></div>
					</div>
					<c:forEach items="${lstMenuTypes}" var="menuType" varStatus="loop">
						<div id="menuRow${loop.index}" class="form-group">
							<label class="col-sm-2 control-label"><spring:message code="menu.text.${menuType.id}" text="menu.text.${menuType.id} not found"/></label>
							<div class="col-sm-3">
								<textarea rows="2" id="0_${menuType.id}" class="form-control no_resize" maxlength="255"></textarea>
							</div>
							<div class="col-sm-3">
								<textarea rows="2" id="1_${menuType.id}" class="form-control no_resize" maxlength="255"></textarea>
							</div>
							<div class="col-sm-3">
								<textarea rows="2" id="2_${menuType.id}" class="form-control no_resize" maxlength="255"></textarea>
							</div>
						</div>
					</c:forEach>
				</form>	 		
	 		</div>  
	 		<div class="modal-footer"> 
	 			<button type="button" class="btn btn-primary" id="dataModalCancel"><spring:message code="common.cancel" text="common.cancel not found" /></button> 
	 			<button type="button" class="btn btn-primary" id="dataModalOK"><spring:message code="common.save" text="common.save not found" /></button> 
	 		</div> 
	 	</div> 
 	</div> 
</div>

<script>
$(document).ready(function() {
	var title = '<spring:message code="menu.title" text="menu.title not found"/>' + ", " + moment(date).month(month).day(daySelect+1).format("dddd D MMMM");
	$("#dataModalLabel").text(title.toUpperCase());
	if(data[daySelect] != null){
		for(var i=0; i < 3; i++){
			for(type in data[daySelect].data[i]){
				var description = data[daySelect].data[i][type].description;
				if(description != null){
					$("#"+i+"_"+type).val(description);
				}
			}
		}
	}
});

$("#dataModalOK").click(function(){
	$("textarea").each(function(){
		var order = parseInt(this.id.split("_")[0]);
		var type = this.id.split("_")[1];
		if((this.value != null && this.value != "") || (checkExist(order, type))){
			var menu = {
				month : month,
				day : daySelect,
				order : order,
				type : type,
				description : $("#"+order+"_"+type).val()
			}
			lstMenus.push(menu);
		}
	});
	
});

function checkExist(order, type){
	return (data[daySelect] != null && data[daySelect].data[order][type] != null)
}

</script>
