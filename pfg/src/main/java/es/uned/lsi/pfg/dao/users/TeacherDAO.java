/**
 * 
 */
package es.uned.lsi.pfg.dao.users;

import java.util.List;

import es.uned.lsi.pfg.model.Teacher;

/**
 * Repositorio de profesores
 * @author Carlos Navalon Urrea
 */
public interface TeacherDAO {
	/**
	 * Obtiene un profesor por su id
	 * @param id id del profesor
	 * @return el profesor
	 */
	public Teacher findTeacher(String id);

	/**
	 * Obtiene todos los profersores
	 * @return lista todos los profersores
	 */
	public List<Teacher> findAllTeachers();

	/**
	 * Inserta o actualiza un profesor
	 * @param teacher profesor
	 * @return <code>true</code> si la operacion se ha realizado correctamente, <code>false</code> en caso contrario
	 */
	public boolean upsert(Teacher teacher); 
}
