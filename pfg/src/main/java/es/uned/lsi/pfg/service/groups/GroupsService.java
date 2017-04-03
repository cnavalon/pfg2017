/**
 * 
 */
package es.uned.lsi.pfg.service.groups;

import java.util.List;

import es.uned.lsi.pfg.model.Course;
import es.uned.lsi.pfg.model.Group;

/**
 * Servicio de clases
 * @author Carlos Navalon Urrea
 *
 */
public interface GroupsService {
	
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
	public Course getCourse(Integer idCourse);

	/**
	 * Recupera todas las clases
	 * @return listado de todas las clases
	 */
	public List<Group> getAllGroups();

	/**
	 * Recupera una clase id
	 * @param group id de clase
	 * @return clase
	 */
	public Group getGroup(Integer group);
	
	/**
	 * Recupera una clase por curso y letra
	 * @param course curso
	 * @param letter letra
	 * @return clase con este curso y letra
	 */
	public Group getGroupByCourseAndLetter(Integer course, String letter);

	/**
	 * Recupera un listado de clases por curso
	 * @param course curso
	 * @return listado de clases
	 */
	public List<Group> getGroupsByCourse(Integer course);
	
	/**
	 * Inserta/actualiza una clase
	 * @param group clase
	 * @return Id de la clase
	 */
	public Integer saveGroup(Group group);
	
	/**
	 * Elimina una clase
	 * @param idGroup clase
	 */
	public void deleteGroup(Integer idGroup);

}