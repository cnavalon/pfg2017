/**
 * 
 */
package es.uned.lsi.pfg.dao.groups;

import java.util.List;

import es.uned.lsi.pfg.model.Schedule;

/**
 * Repositorio de horarios
 * @author Carlos Navalon Urrea
 *
 */
public interface SchedulesDAO {

	/**
	 * Inserta un listado de horarios
	 * @param lstSchedule listado de horarios
	 */
	public void saveSchedules(List<Schedule> lstSchedule);
	
	/**
	 * Elimina todos los horarios de una clase
	 * @param group clase
	 */
	public void deleteSchudelsByGroup(Integer group);
	
	/**
	 * Elimina un profesor de todos sus horarios
	 * @param teacher profesor
	 */
	public void deleteTeacher(Integer teacher);
	
	/**
	 * Recupera el horario por clase
	 * @param group clase
	 * @return listado con las horas de una clase
	 */
	public List<Schedule> findScheduleByGroup(Integer group);
	
	/**
	 * Recupera el horario por profesor
	 * @param teacher profesor
	 * @return listado con las horas del profesor
	 */
	public List<Schedule> findScheduleByTeacher(Integer teacher);

	/**
	 * Devuelve la mayor hora de un horario por clase
	 * @param group clase
	 * @return la mayor hora
	 */
	public Integer findMaxHourByGroup(Integer group);
	
	/**
	 * Devuelve la mayor hora de un horario por profesor
	 * @param teacher profesor
	 * @return la mayor hora
	 */
	public Integer findMaxHourByTeacher(Integer teacher);

	/**
	 * Devuelve un listado de horarios por codigo de asignatura
	 * @param subject codigo de asignatura
	 * @return listado de horarios
	 */
	public List<Schedule> findScheduleBySubject(String subject);
}
