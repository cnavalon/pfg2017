/**
 * 
 */
package es.uned.lsi.pfg.service.courses;

import java.util.List;

import es.uned.lsi.pfg.model.Course;

/**
 * Servicio de cursos
 * @author Carlos Navalon Urrea
 *
 */
public interface CoursesService {
	
	/**
	 * Recupera todos los cursos
	 * @return listado de todos los cursos
	 */
	public List<Course> getAllCourses();
	
	/**
	 * Recupera el curso por id
	 * @param idCourse id del curso
	 * @return el curso
	 */
	public Course getCourse(String idCourse);
}
