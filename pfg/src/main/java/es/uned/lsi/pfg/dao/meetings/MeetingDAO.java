/**
 * 
 */
package es.uned.lsi.pfg.dao.meetings;

import java.util.List;

import es.uned.lsi.pfg.model.Meeting;

/**
 * Repositorio de reuniones
 * @author Carlos Navalon Urrea
 *
 */
public interface MeetingDAO {
	
	/**
	 * Inserta/actualiza una reunion
	 * @param meeting
	 * @return reunion
	 */
	public Meeting upsert(Meeting meeting);
	
	/**
	 * Obtiene una reunion por id
	 * @param id de reunion
	 * @return reunion
	 */
	public Meeting findMeeting(Integer id);
	
	/**
	 * Obtiene un listado de reuniones por usuario
	 * @param user id usuario
	 * @return listado de reuniones
	 */
	public List<Meeting> findMeetingByUser(String user);
}
