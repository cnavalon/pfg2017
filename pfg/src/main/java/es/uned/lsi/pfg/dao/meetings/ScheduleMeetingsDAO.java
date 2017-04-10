/**
 * 
 */
package es.uned.lsi.pfg.dao.meetings;

import java.util.List;

import es.uned.lsi.pfg.model.ScheduleMeeting;

/**
 * Repositorio de horario de tutorias
 * @author Carlos Navalon Urrea
 *
 */
public interface ScheduleMeetingsDAO {
	
	/**
	 * Obtiene un listado de todos los horarios de tutoria por profesor
	 * @param teacher profesor
	 * @return listado de todos los horarios de tutoria 
	 */
	public List<ScheduleMeeting> findByTeacher(Integer teacher);
	
	/**
	 * Obtiene un horario de tutoria por id
	 * @param id id de horario de tutoria
	 * @return horario de tutoria
	 */
	public ScheduleMeeting findById(Integer id);
	
	/**
	 * Inserta un horario de tutoria
	 * @param scheduleMeeting horario de tutoria
	 */
	public void save(ScheduleMeeting scheduleMeeting);

	/**
	 * Elimina un horario de tutoria
	 * @param scheduleMeeting horario de tutoria
	 */
	public void delete(ScheduleMeeting scheduleMeeting);

}
