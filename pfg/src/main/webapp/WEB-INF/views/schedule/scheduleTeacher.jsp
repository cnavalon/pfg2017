<%@ include file="/WEB-INF/views/common/include.jsp" %>

<table id="tableSchedule" class="stripe cell-border" width="95%">
	<thead>
  		<tr>
    		<th></th>
    		<th class="text-center"><spring:message code="common.monday" text="common.monday not found" /></th>
    		<th class="text-center"><spring:message code="common.tuesday" text="common.tuesday not found" /></th>
    		<th class="text-center"><spring:message code="common.wednesday" text="common.wednesday not found" /></th>
    		<th class="text-center"><spring:message code="common.thursday" text="common.thursday not found" /></th>
    		<th class="text-center"><spring:message code="common.friday" text="common.friday not found" /></th>
   		</tr>
  	</thead>
  	<tbody>
  		<c:forEach items="${schedule.rows}" var="row">
	  		<tr>
  				<td>${row.initHour} <br> ${row.endHour}</td>
	  			<c:forEach items="${row.cells}" var="cell" varStatus="loop">
	  				<c:choose>
	  					<c:when test="${not empty cell and interactive}">
	  						<td class="cursorPointer">
	  							<div onclick="actionCell(${cell.id},${cell.group.id},${loop.index},'${cell.subject.name}','${cell.group.course.name} ${cell.group.letter}')">
	  								<b>${cell.subject.name}</b><br> ${cell.group.course.name} ${cell.group.letter}
	  							</div> 
	  						</td>
	  					</c:when>
	  					<c:otherwise>
			  				<td><b>${cell.subject.name}</b><br> ${cell.group.course.name} ${cell.group.letter} </td>
	  					</c:otherwise>
	  				</c:choose>
				</c:forEach>
	  		</tr>
  		</c:forEach>
  	</tbody>
</table>

<script>
var tableSchedule = $('#tableSchedule').DataTable( {
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
    "columns" : [
        { "width":"5%"},
        { "width":"19%"},
        { "width":"19%"},
        { "width":"19%"},
        { "width":"19%"},
        { "width":"19%"}
	]
} );


</script>