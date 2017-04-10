/**
 * 
 */
package es.uned.lsi.pfg.service.meetings;

import java.util.List;

import es.uned.lsi.pfg.model.ScheduleMeeting;

/**
 * Servicio de tutorias
 * @author Carlos Navalon Urrea
 *
 */
public interface MeetingsService {
	
	/**
	 * Obtiene un listado de horarios de tutoria para un profesor
	 * @param idTeacher profesor
	 * @return listado de horarios de tutorias
	 */
	public List<ScheduleMeeting> getScheduleMeeting(Integer idTeacher);

	/**
	 * Inserta un horario de tutoria
	 * @param scheduleMeeting horario de tutorias
	 */
	public void saveScheduleMeeting(ScheduleMeeting scheduleMeeting);
	
	/**
	 * Elimina un horario de tutoria
	 * @param id id de horario de tutorias
	 */
	public void deleteScheduleMeeting(Integer id);

}
