/**
 * 
 */
package es.uned.lsi.pfg.service.meetings;

import java.util.List;

import es.uned.lsi.pfg.model.Meeting;
import es.uned.lsi.pfg.model.MeetingFull;
import es.uned.lsi.pfg.model.ScheduleMeeting;
import es.uned.lsi.pfg.model.Teacher;
import es.uned.lsi.pfg.model.TeacherMeeting;

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

	/**
	 * Obtiene un profesor con horario de tutorias
	 * @param teacher profesor
	 * @return profesor con horario de tutorias
	 */
	public TeacherMeeting getTeacherMeeting(Teacher teacher);

	/**
	 * Inserta una reunion
	 * @param meetingFull reunion con listado de asistentes
	 * @return tutoria
	 */
	public Meeting saveMeeting(MeetingFull meetingFull);

}
