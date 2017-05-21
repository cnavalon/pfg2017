/**
 * 
 */
package es.uned.lsi.pfg.dao.absences;

import java.util.Date;
import java.util.List;

import es.uned.lsi.pfg.model.Absence;

/**
 * Repositorio de faltas de asistencia
 * @author Carlos Navalon Urrea
 *
 */
public interface AbsencesDAO {

	/**
	 * Recupera una falta de asistencia por id
	 * @param id id de falta de asistencia
	 * @return falta de asistencia
	 */
	public Absence findById(Integer id);
	
	/**
	 * Recupera un listado de faltas de asistencia por alumno
	 * @param student id de alumno
	 * @return listado de faltas de asistencia
	 */
	public List<Absence> findByStudent(Integer student);
	
	/**
	 * Recupera un listado de faltas de asistencia por horario
	 * @param schedule id de horario
	 * @param day dia
	 * @return listado de faltas de asistencia
	 */
	public List<Absence> findByScheduleDay(Integer schedule, Date day);
	
	/**
	 * Inserta/actualiza una falta de asistencia
	 * @param absence falta de asistencia
	 * @return falta de asistencia actualizada
	 */
	public Absence upsert(Absence absence);

	/**
	 * Recupera una falta de asistencia por estudiante, horario y dia
	 * @param student id de estudiante
	 * @param schedule id de horario
	 * @param day dia
	 * @return falta de asistencia
	 */
	public Absence findByStudentScheduleDay(Integer student, Integer schedule, Date day);
}
