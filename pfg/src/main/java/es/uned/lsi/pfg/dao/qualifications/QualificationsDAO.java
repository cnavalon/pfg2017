/**
 * 
 */
package es.uned.lsi.pfg.dao.qualifications;

import java.util.List;

import es.uned.lsi.pfg.model.Qualification;

/**
 * Repositorio de calificaciones
 * @author Carlos Navalon Urrea
 *
 */
public interface QualificationsDAO {

	/**
	 * Recupera una calificación por id
	 * @param id id de calificacion
	 * @return calificacion
	 */
	public Qualification findById(Integer id);
	
	/**
	 * Recupera un listado de calificaciones por estudiante
	 * @param student id de estudiante
	 * @return listado de calificaciones
	 */
	public List<Qualification> findByStudent(Integer student);
	
	/**
	 * Recupera un listado de calificaciones por clase y asignatura
	 * @param group id de clase
	 * @param subject id de asignatura
	 * @return listado de calificaciones
	 */
	public List<Qualification> findByGroupSubject(Integer group, Integer subject);
	
	/**
	 * Inserta/actualiza calificacion
	 * @param qualification calificacion
	 * @return calificacion actualizada
	 */
	public Qualification upsert(Qualification qualification);
	
}
