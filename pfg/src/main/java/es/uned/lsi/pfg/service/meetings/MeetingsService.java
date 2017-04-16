/**
 * 
 */
package es.uned.lsi.pfg.service.meetings;

import java.util.List;

import es.uned.lsi.pfg.model.Attendee;
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

	/**
	 * Obtiene todas las reuniones aceptadas o pendientes y con fecha posterior a la 
	 * actual en las que está involucrado el usuario
	 * @param idUser id de usuario
	 * @return listado de las reuinoes 
	 */
	public List<Meeting> getActiveMeetingsByUser(String idUser);

	/**
	 * Obtiene todas las solicitudes de reunion pendientes de contestar por el usuario
	 * y con fecha posterior a la actual 
	 * @param idUser id de usuario
	 * @return listado de las reuinoes 
	 */
	public List<Meeting> getActiveRequest(String idUser);

	/**
	 * Guarda una respuesta a solicitud de reunion y actualiza el estado de la reunion
	 * @param idUser id usuario
	 * @param idMeeting id reunion
	 * @param state estado
	 * @param comment comentario
	 * @return asistente actualizado
	 */
	public Attendee saveResponse(String idUser, Integer idMeeting, String state, String comment);

	/**
	 * Recupera una reunion 
	 * @param meeting codigo de reunion
	 * @return reunion
	 */
	public Meeting getMeeting(Integer meeting);

	/**
	 * Recupera un listado de asisitentes por reunion
	 * @param meeting id de reunion
	 * @return listado de asistentes
	 */
	public List<Attendee> getAttendeeByMeeting(Integer meeting);

	/**
	 * Cancela una reunion
	 * @param idMeeting id reunion
	 * @return reunion
	 */
	public Meeting cancelMeeting(Integer idMeeting);
	
	/**
	 * Recupera un listado de asistentes por reunion y estado
	 * @param meeting id reuion
	 * @param state estado
	 * @return listado de asistentes
	 */
	public List<Attendee> getAttendeeByMeetingState(Integer meeting, String state);

}
