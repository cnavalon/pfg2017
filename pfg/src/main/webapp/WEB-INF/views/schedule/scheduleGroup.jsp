<%@ include file="/WEB-INF/views/common/include.jsp" %>

<table id="tableSchedule" class="stripe cell-border" width="95%">
	<thead>
  		<tr>
    		<th></th>
    		<th><spring:message code="common.monday" text="common.monday not found" /></th>
    		<th><spring:message code="common.tuesday" text="common.tuesday not found" /></th>
    		<th><spring:message code="common.wednesday" text="common.wednesday not found" /></th>
    		<th><spring:message code="common.thursday" text="common.thursday not found" /></th>
    		<th><spring:message code="common.friday" text="common.friday not found" /></th>
   		</tr>
  	</thead>
  	<tbody>
  		<c:forEach items="${schedule.rows}" var="row">
	  		<tr>
  				<td>${row.initHour} <br> ${row.endHour}</td>
	  			<c:forEach items="${row.cells}" var="cell">
	  				<td><b>${cell.subject.name}</b><br> ${cell.teacher.surname1} ${cell.teacher.surname2}, ${cell.teacher.name} </td>
				</c:forEach>
	  		</tr>
  		</c:forEach>
  	</tbody>
</table>

<script>
$('#tableSchedule').DataTable( {
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
        { "width":"10%"},
        { "width":"18%"},
        { "width":"18%"},
        { "width":"18%"},
        { "width":"18%"},
        { "width":"18%"}
	],
} );
</script>