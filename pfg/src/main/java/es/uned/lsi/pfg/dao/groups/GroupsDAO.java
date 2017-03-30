/**
 * 
 */
package es.uned.lsi.pfg.dao.groups;

import java.util.List;

import es.uned.lsi.pfg.model.Group;

/**
 * Repositorio de clases
 * @author Carlos Navalon Urrea
 *
 */
public interface GroupsDAO {

	/**
	 * Recupera una clase por id
	 * @param id id de la clase
	 * @return clase
	 */
	public Group findById(Integer id);
	
	/**
	 * Recupera todas las clases
	 * @return listado de todas las clases
	 */
	public List<Group> findAll();
	
	/**
	 * Inserta o actualiza una clase
	 * @param group clase
	 */
	public void upsert(Group group);
	
	/**
	 * Elimina una clase
	 * @param group clase
	 */
	public void remove(Group group);

	/**
	 * Recupera una clase por curso y letra
	 * @param course curso
	 * @param letter letra
	 * @return clase con este curso y letra
	 */
	public Group findByCourseAndLetter(Integer course, String letter);

	/**
	 * Recupera un listado de clases por curso
	 * @param course id de curso
	 * @return listado de clases por curso
	 */
	public List<Group> findByCourse(Integer course);
}

