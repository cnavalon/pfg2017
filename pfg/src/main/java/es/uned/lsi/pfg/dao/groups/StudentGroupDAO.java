/**
 * 
 */
package es.uned.lsi.pfg.dao.groups;

import java.util.List;

/**
 * Repositorio de tabla relacional estudiante-clase
 * @author Carlos Navalon Urrea
 *
 */
public interface StudentGroupDAO {
	
	/**
	 * Cuenta el numero de alumnos de una clase
	 * @param group clase
	 * @return el numero de alumnos
	 */
	public Long countStudents(Integer group);
	
	/**
	 * Recupera listado de ids de alumnos por clase
	 * @param group clase
	 * @return listado de ids de alumnos
	 */
	public List<Integer> findStundentsByGroup(Integer group);

}
