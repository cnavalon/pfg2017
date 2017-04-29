/**
 * 
 */
package es.uned.lsi.pfg.service.groups;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import es.uned.lsi.pfg.model.Course;
import es.uned.lsi.pfg.model.Group;
import es.uned.lsi.pfg.model.ScheduleTable;
import es.uned.lsi.pfg.model.Subject;
import es.uned.lsi.pfg.model.Teacher;

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

	/**
	 * Procesa un fichero de horario de una clase 
	 * @param idGroup clase
	 * @param file fichero de horario
	 * @throws IOException 
	 */
	public void saveSchedule(Integer idGroup, MultipartFile file) throws IOException;

	/**
	 * Recupera un horario por grupo
	 * @param idGroup grupo
	 * * @param lstTeacher listado de todos los profesores
	 * @return horario
	 */
	public ScheduleTable getScheduleByGroup(Group group, List<Teacher> lstTeacher);
	
	/**
	 * Recupera un horario por profesor
	 * @param teacher profesor
	 * @return horario
	 */
	public ScheduleTable getScheduleByTeacher(Teacher teacher);

	/**
	 * Obtiene una clase por tutor
	 * @param tutor tutor
	 * @return clase
	 */
	public Group getGroupByTutor(Integer tutor);
	
	/**
	 * Obtiene una listado de clases impartidas por un profesor
	 * @param teacher profesor
	 * @return listado de clases
	 */
	public List<Group> getGroupsByTeacher(Integer teacher);

	/**
	 * Obtiene un listado de todas las asignaturas
	 * @return listado de todas las asignaturas
	 */
	public List<Subject> getSubjects();

	/**
	 * Obtiene una asignatura por id
	 * @param idSubject id
	 * @return asignatura
	 */
	public Subject getSubject(Integer idSubject);

	/**
	 * Inserta/actualiza una asignatura
	 * @param subject asignatura
	 */
	public void upsertSubject(Subject subject);
	
	/**
	 * Eliminia una asignatura
	 * @param idSubject id asignatura
	 * @return mensaje con el resultado de la operacion
	 */
	public String deleteSubject(Integer idSubject);

	/**
	 * Obtiene una asignatura por codigo
	 * @param code codigo
	 * @return asignatura
	 */
	public Subject getSubjectByCode(String code);
	
	/**
	 * Obtiene un listado de asignaturas por clase
	 * @param groupId id de clase
	 * @return listado de asignaturas
	 */
	public List<Subject> getSubjectsByGroup(Integer groupId);

	/**
	 * Obtiene un listado de asignaturas por clase y profesor
	 * @param groupId id de clase
	 * @param teacherId id de profesor
	 * @return listado de asignaturas
	 */
	public List<Subject> getSubjectsByGroupAndTeacher(Integer groupId, Integer teacherId);
	
}