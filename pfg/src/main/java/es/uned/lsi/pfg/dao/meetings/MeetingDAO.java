/**
 * 
 */
package es.uned.lsi.pfg.dao.meetings;

import java.util.Date;
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
	public Meeting findById(Integer id);
	
	/**
	 * Obtiene un listado de reuniones por usuario
	 * @param user id usuario
	 * @return listado de reuniones
	 */
	public List<Meeting> findByUser(String user);

	/**
	 * Obtiene un listado de reuniones por usuario, listado de estados y posteriores a una fecha
	 * @param user
	 * @param lstState listado de estados
	 * @param date fecha
	 * @return listado de reuniones
	 */
	public List<Meeting> findByUserStateDate(String user, List<String> lstState, Date date);
	
	/**
	 * Obtiene una reunion por id y posteriores a una fecha
	 * @param id de reunion
	 * @param date fecha
	 * @return reunion
	 */
	public Meeting findByIdDate(Integer id, Date date);
}
