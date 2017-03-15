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
	 * @throws Exception
	 */
	public void insert(StudentParent studentParent) throws Exception;
	
	/**
	 * Obtiene la lista de relaciones alumno-padre por alumno
	 * @param student alumno
	 * @return lista de relaciones alumno-padre
	 */
	public List<StudentParent> findByStudent(Integer student);
	
	/**
	 * Obtiene la lista de relaciones alumno-padre por padre
	 * @param parent padre
	 * @return lista de relaciones alumno-padre
	 */
	public List<StudentParent> findByParent(Integer parent);
	
	/**
	 * Elimina una relacion alumno-padre
	 * @param studentParent relacion alumno-padre
	 * @throws Exception
	 */
	public void remove(StudentParent studentParent) throws Exception;

}
