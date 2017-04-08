/**
 * 
 */
package es.uned.lsi.pfg.dao.groups;

import java.util.List;

import es.uned.lsi.pfg.model.Subject;

/**
 * Repositorio de asignaturas
 * @author Carlos Navalon Urrea
 *
 */
public interface SubjectDAO {

	/**
	 * Recupera todas las asignaturas
	 * @return listado de todas las asignaturas
	 */
	public List<Subject> findAll();
	
	/**
	 * Recupera una asignatura por id
	 * @param id id de asignatura
	 * @return asignatura
	 */
	public Subject findById(Integer id);
	
	/**
	 * Recupera una asignatura por codigo
	 * @param code codigo de la asignatura
	 * @return asignatura
	 */
	public Subject findByCode(String code);
	
	/**
	 * Inserta/actualiza una asignatura
	 * @param subject asignatura
	 * @return asignatura actualizada
	 */
	public Subject upsert(Subject subject);
}
