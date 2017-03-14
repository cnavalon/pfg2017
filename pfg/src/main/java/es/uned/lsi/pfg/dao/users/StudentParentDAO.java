/**
 * 
 */
package es.uned.lsi.pfg.dao.users;

import java.util.List;

import es.uned.lsi.pfg.model.StudentParent;

/**
 * Repositorio de la tabla relacional student_parent
 * @author Carlos Navalon Urrea
 *
 */
public interface StudentParentDAO {

	/**
	 * Inserta una relacion alumno padre
	 * @param studentParent relacion alumno padre
	 */
	public void insert(StudentParent studentParent);
	
	/**
	 * Obtiene la lista ids de padres de un alumno
	 * @param student alumno
	 * @return lista de ids de padres del alumno
	 */
	public List<Integer> findByStudent(Integer student);

}
