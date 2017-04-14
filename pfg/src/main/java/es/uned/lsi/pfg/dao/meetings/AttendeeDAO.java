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
}
