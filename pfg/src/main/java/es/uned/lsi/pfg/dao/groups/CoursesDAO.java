/**
 * 
 */
package es.uned.lsi.pfg.dao.groups;

import java.util.List;

import es.uned.lsi.pfg.model.Course;

/**
 * Repositorio de cursos
 * @author Carlos Navalon Urrea
 *
 */
public interface CoursesDAO {

	/**
	 * Recupera todos los cursos
	 * @return listado de todos los cursos
	 */
	public List<Course> findAll();
	
	/**
	 * Recupera el curso por id
	 * @param idCourse id del curso
	 * @return el curso
	 */
	public Course findById(Integer idCourse);
}
