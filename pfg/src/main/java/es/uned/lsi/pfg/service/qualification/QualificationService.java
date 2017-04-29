/**
 * 
 */
package es.uned.lsi.pfg.service.qualification;

import java.util.List;
import java.util.Map;

import es.uned.lsi.pfg.model.Qualification;

/**
 * Servicio de calificaciones
 * @author Carlos Navalon Urrea
 *
 */
public interface QualificationService {
	
	/**
	 * Recupera calificacion por id
	 * @param id id de calificacion
	 * @return calificacion
	 */
	public Qualification getQualification(Integer id);
	
	/**
	 * Recupera un mapa de id de asignatura - calificacion por estudiante
	 * @param student id de estudiante
	 * @return mapa de id de asignatura - calificacion
	 */
	public Map<Integer, Qualification> getQualificationsByStudent(Integer student);
	
	/**
	 * Recupera listado de calificaciones por clase y asignatura
	 * @param group id de clase
	 * @param subject id de asignatura
	 * @return listado de calificaciones
	 */
	public List<Qualification> getQualificationsByGroupSubject(Integer group, Integer subject);
	
	/**
	 * Inserta/actualiza un listado de calificaciones
	 * @param lstQualifications listado de calificaciones
	 */
	public void upsert(List<Qualification> lstQualifications);
	
	/**
	 * Elimina calificacion
	 * @param qualificationId id de calificacion
	 */
	public void delete(Integer qualificationId);

}
