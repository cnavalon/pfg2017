/**
 * 
 */
package es.uned.lsi.pfg.dao.meetings;

import java.util.List;

import es.uned.lsi.pfg.model.Attendee;

/**
 * Repositorio de asistentes a reunion
 * @author Carlos Navalon Urrea
 *
 */
public interface AttendeeDAO {
	
	/**
	 * Inserta / actualiza un asistente a reunion
	 * @param attendee asistente a reunion
	 * @return asistente a reunion actualizado
	 */
	public Attendee upsert(Attendee attendee);
	
	/**
	 * Obtiene un listado de asistentes por reunion
	 * @param meeting is reunion
	 * @return listado de asistentes a reunion
	 */
	public List<Attendee> findByMeeting(Integer meeting);

	/**
	 * Obtiene un listado de asistentes por usuario
	 * @param user usuario
	 * @return listado de asistentes a reunion
	 */
	public List<Attendee> findByUser(String user);

	/**
	 * Obtiene un listado de asistentes por usuario y estado
	 * @param user usuario
	 * @param state estado
	 * @return listado de asistentes a reunion
	 */
	public List<Attendee> findByUserAndState(String user, String state);

	/**
	 * Recupera un asistente por reunion y usuario
	 * @param meeting reunion 
	 * @param user usuario
	 * @return asistente
	 */
	public Attendee findByMeetingUser(Integer meeting, String user);

	/**
	 * Recupera un listado de asistentes por reunion y estado
	 * @param meeting id reunion
	 * @param state estado
	 * @return listado de asistentes
	 */
	public List<Attendee> findByMeetingState(Integer meeting, String state);

	/**
	 * Obtiene un listado de asistentes por usuario y estado distinto al seleccionado
	 * @param user usuario
	 * @param state estado
	 * @return listado de asistentes a reunion
	 */
	public List<Attendee> findByUserAndNotState(String user, String state);
}
