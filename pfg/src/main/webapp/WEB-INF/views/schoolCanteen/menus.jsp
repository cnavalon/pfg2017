<%@ include file="/WEB-INF/views/common/include.jsp" %>

<spring:url value="/schoolCanteen/getMenuMonth/" var="urlMenuMonth" />
<spring:url value="/schoolCanteen/adm/modalMenu/" var="urlModalMenu" />
<spring:url value="/schoolCanteen/adm/saveMenu?${_csrf.parameterName}=${_csrf.token}" var="urlSaveMenu" />
<spring:url value="/schoolCanteen/adm/deleteMenuDay/" var="urlDeleteMenuDay" />


<div class="row-fluid">
	<h3 class="title"><spring:message code="menu.title" text="menu.title not found" /></h3>
</div>

<form class="form-horizontal" action="">
	<div class="row-fluid" id="divSelect">
		<div class="form-group">
			<label class="col-sm-2 control-label"><spring:message code="menu.month" text="menu.month not found"/></label>
			<div class="col-sm-3">
				<select class="form-control" id="selectMonth">
					<option value="0" label="<spring:message code="common.january" text="common.january not found"/>">
					<option value="1" label="<spring:message code="common.february" text="common.february not found"/>">
					<option value="2" label="<spring:message code="common.march" text="common.march not found"/>">
					<option value="3" label="<spring:message code="common.april" text="common.april not found"/>">
					<option value="4" label="<spring:message code="common.may" text="common.may not found"/>">
					<option value="5" label="<spring:message code="common.june" text="common.june not found"/>">
					<option value="6" label="<spring:message code="common.july" text="common.july not found"/>">
					<option value="7" label="<spring:message code="common.august" text="common.august not found"/>">
					<option value="8" label="<spring:message code="common.september" text="common.september not found"/>">
					<option value="9" label="<spring:message code="common.october" text="common.october not found"/>">
					<option value="10" label="<spring:message code="common.november" text="common.november not found"/>">
					<option value="11" label="<spring:message code="common.december" text="common.december not found"/>">
					
				</select>
			</div>
		</div>
	</div>
	
	<div class="row-fluid">
		<div class="col-sm-offset-1 col-sm-10">
			<table id="tableMenu" class="stripe cell-border" width="100%">
				<thead>
			  		<tr>
			    		<th class="text-center"><spring:message code="common.monday" text="common.monday not found" /></th>
			    		<th class="text-center"><spring:message code="common.tuesday" text="common.tuesday not found" /></th>
			    		<th class="text-center"><spring:message code="common.wednesday" text="common.wednesday not found" /></th>
			    		<th class="text-center"><spring:message code="common.thursday" text="common.thursday not found" /></th>
			    		<th class="text-center"><spring:message code="common.friday" text="common.friday not found" /></th>
			   		</tr>
			  	</thead>
			  	<tbody>
			  	</tbody>
			</table>
		</div>
	</div>
	
</form>


<script>
var types = {};
<c:forEach items="${lstMenuTypes}" var="menuType">
	types["${menuType.id}"] = {
			initial : '<spring:message code="menu.initial.${menuType.id}" text="menu.initial.${menuType.id} not found" />',
			text : '<spring:message code="menu.text.${menuType.id}" text="menu.text.${menuType.id} not found" />'
	}
</c:forEach>

moment.locale("${locale}");
var date = moment().startOf('month');
var month = moment(date).month();
var days = moment(date).daysInMonth();
var lstMenus = [];
var tableMenu = null;
var data = null;
var daySelect = null;

var lstMenus = [];

$(document).ready(function() {
	$("#selectMonth").val(month);
	loadData();
});

$("#selectMonth").change(function(){
	month = $("#selectMonth").val();
	date = moment(date).month(month);
	days = moment(date).daysInMonth();
	loadData();
});

function loadData(){
	if(tableMenu == null){
		createTable();
	} else {
		reloadTable();
	}
}

function createTable(){
	tableMenu = $('#tableMenu').DataTable( {
		"language": {
	         "url": '<spring:message code="table.urlDataTables" text="table.urlDataTables not found" />'
	     },
		"paging": false,
	    "ordering": false,
	    "info": false,
	    "searching": false,
	    "fixedHeader": {
	        header: true,
	        footer: false
	    },
	    "autoWidth": false,
	    ajax : {
   			url : "${urlMenuMonth}" + month,
   			type : "GET",
   			dataSrc : function ( json ) {
   				data = json;
   				var menu = [];
   				var menuDay = {};
   				var countDay = moment(date).day()-1;
   				var initial = 0;
   				if(countDay > 0 && countDay < 5){ //Dia 1 -> de martes a viernes
	   				//Rellenar primera fila hasta dia 1
	   				for(var i = 0; i < countDay; i++){
	   					menuDay[i] = "";
	   				}   					
   				} else if (countDay == 5){ //Dia 1 -> sabado
   					initial = 2;
   					countDay = 0;
   				} else if (countDay == -1){ //Dia 1 -> domingo
   					initial = 1;
   					countDay = 0;
   				}
   				   		
   				//Recorremos todos los datos
	   	    	for (var i = initial; i < days; i++) {
	   	    		menuDay[countDay%7] = getDayMenu(i, json[i]);
	   	    		
	   	    		countDay++;
	   	    		if(countDay%7 == 0){
	   	    			menu.push(jQuery.extend(true, {}, menuDay));
	   	    			menuDay = {};
	   	    		}
     	    	}
   				
	   	   		//Rellenar ultima fila 
	   	   		while(countDay%7 != 0){
	   	   			menuDay[countDay%7] = "";
	   	   			countDay++;
	   	   			if(countDay%7 == 0){
   	    				menu.push(jQuery.extend(true, {}, menuDay));
   	    			}
	   	   		}
   	 	      	return menu;
   	 	    }
   		},
	    "columns" : [
	        { "data": "0","width":"20%", "className":"vertical_top"},
	        { "data": "1","width":"20%", "className":"vertical_top"},
	        { "data": "2","width":"20%", "className":"vertical_top"},
	        { "data": "3","width":"20%", "className":"vertical_top"},
	        { "data": "4","width":"20%", "className":"vertical_top"},
	        { "data": "5","width":"20%","visible":false},
	        { "data": "6","width":"20%","visible":false}
		],
	} );
}

function getDayMenu(day, dataDay){
	var cell = getHeader(day, dataDay != null);
	if(dataDay != null){
		cell += getDataDay(day, dataDay);
	}
	return cell;
}

function getHeader(day, showDelete){
	var div = '<div class="table_view">'
	div += '<div class="table_cell_view text-left">' + (day+1) + '</div>';
	if("${sessionScope.sessionUserRole.idRole}" == "ROLE_ADM"){
		div += '<div class="text-right">';
		div += '<label class="cursorPointer iconTable" onclick="updateMenu('+ day +')"><i class="glyphicon glyphicon-pencil" title="'+ '<spring:message code="common.edit" text="common.edit not found" />' +'"> </i></label>';
		if(showDelete)
			div += '<label class="cursorPointer iconTable" onclick="confirmDeleteMenu('+ day +')"><i class="glyphicon glyphicon-trash" title="'+ '<spring:message code="common.delete" text="common.delete not found" />' +'"> </i></label>';
		div += '</div>';
	}
	div += '</div>';
	
	return div;
}

function getDataDay(day,dataDay){
	var div = '<div>';
	for(var i=0; i < 3; i++){
		if(dataDay.data[i]["G"] != null){
			div += '<div><b>' + dataDay.data[i]["G"].description + '</b></div>';
			for(type in dataDay.data[i]){
				if(type != "G"){
					div += '<div class="identedText"><label title="'+types[type].text+'">' + types[type].initial + '</label> ' + dataDay.data[i][type].description  + '</div>';
				}
			}
		}
	}
	return div;
}

function reloadTable(){
	tableMenu.ajax.url('${urlMenuMonth}' + month).load();
}

function updateMenu(day){
	lstMenus = [];
	daySelect = day;
	modal("${urlModalMenu}" + month + "/" +day,saveMenu,null);
}

function saveMenu(){
	$.ajax({
		url : "${urlSaveMenu}",
		type:"POST", 
		data: JSON.stringify(lstMenus),
		contentType: "application/json",
		success : function(text) {
			alert(text,loadData);
		},
		error: function(){
			alert('<spring:message code="menu.save.error" text="menu.save.error not found"/>', loadData);
		}
	});			
}

function confirmDeleteMenu(day){
	confirm('<spring:message code="menu.delete.quest" text="menu.delete.quest not found"/>', function(){deleteMenu(day);}, null);
}

function deleteMenu(day){
	$.ajax({
		url : "${urlDeleteMenuDay}" + month + "/" + day,
		type:"GET", 
		success : function(text) {
			alert(text,loadData);
		},
		error: function(){
			alert('<spring:message code="menu.delete.error" text="menu.delete.error not found"/>', loadData);
		}
	});		
}


</script>