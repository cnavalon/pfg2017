/**
 * 
 */
package es.uned.lsi.pfg.service.absences;

import java.util.Date;
import java.util.List;

import es.uned.lsi.pfg.model.Absence;

/**
 * Servicio de faltas de asistencia
 * @author Carlos Navalon Urrea
 *
 */
public interface AbsencesService {

	/**
	 * Inserta / actualiza un listado de faltas de asistencia
	 * @param lstAbsences listado de faltas de asistencia
	 * @return listado de las faltas de asistencia actualizadas
	 */
	public List<Absence> upsert(List<Absence> lstAbsences);

	/**
	 * Recupera un listado de los ids de alumnos con falta de asistencia para un horario y dia
	 * @param schedule id horario
	 * @param day fecha
	 * @return listado de los ids de alumnos
	 */
	public List<Integer> getStudentsAbsencesByScheduleDay(Integer schedule, Date day);

	/**
	 * Recupera un listado de faltas de asistencia por alumno	
	 * @param studentId id de alumno
	 * @return listado de faltas de asistencia
	 */
	public List<Absence> getAbsencesByStudent(Integer studentId);

}
