<%@ include file="/WEB-INF/views/common/include.jsp" %>

<table id="tableSchedule" class="stripe cell-border" width="95%">
	<thead>
  		<tr>
    		<th></th>
    		<th><spring:message code="schedule.monday" text="schedule.monday not found" /></th>
    		<th><spring:message code="schedule.tuesday" text="schedule.tuesday not found" /></th>
    		<th><spring:message code="schedule.wednesday" text="schedule.wednesday not found" /></th>
    		<th><spring:message code="schedule.thursday" text="schedule.thursday not found" /></th>
    		<th><spring:message code="schedule.friday" text="schedule.friday not found" /></th>
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