/**
 * 
 */
package es.uned.lsi.pfg.dao.users;

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

}
