/**
 * 
 */
package es.uned.lsi.pfg.service.users;

import java.util.List;
import java.util.Map;

import es.uned.lsi.pfg.model.Person;
import es.uned.lsi.pfg.model.Student;
import es.uned.lsi.pfg.model.StudentWithParents;
import es.uned.lsi.pfg.model.User;
import es.uned.lsi.pfg.model.UserSearch;

/**
 * Servicio de usuarios
 * @author Carlos Navalon Urrea
 */
public interface UsersService {
	/**
	 * Obtiene el nombre completo (nombre y apellidos) por id y perfil
	 * @param id
	 * @param idRole perfil 
	 * @return nombre completo
	 */
	public String getFullName(Integer id, String idRole);
	
	/**
	 * Obtiene el nombre completo de usuario (nombre y apellidos) por id de usuario y perfil
	 * @param id id de usuario
	 * @param idRole perfil de usuario
	 * @return nombre completo de usuario
	 */
	public String getFullNameByUser(String idUser, String idRole);

	/**
	 * Obtiene un usuario por id
	 * @param idUser id de usuario
	 * @return el usuario
	 */
	public User getUser(String idUser);
	
	/**
	 * Comprueba si ya existe un usuario con ese id
	 * @param idUser id de usuario
	 * @return <code>true</code> si ya existe usuario, en otro caso <code>false</code>
	 */
	public boolean existsUser(String idUser);
	
	/**
	 * Eliminia un usuario
	 * @param idUser ID usuario 
	 * @return <code>true</code> si la operación se ha realizado con éxito, en caso contrario <code>false</code>
	 */
	public boolean delete(String idUser);

	/**
	 * Busca los usuarios que coinciden con los datos de usuario pasados como parametro
	 * @param userSearch datos de usuario
	 * @return listado de los usuarios
	 */
	public List<UserSearch> search(UserSearch userSearch);
	
	/**
	 * Devuelve la clase persona por perfil
	 * @param role perfil
	 * @return clase de persona
	 */
	public Class<? extends Person> findClassRole(String role);
	
	/**
	 * Inserta o actualiza una persona, incluido el usuario 
	 * @param person persona
	 * @return <code>true</code> si la operación se ha realizado con éxito, en caso contrario <code>false</code>
	 */
	public <T extends Person> boolean upsert(T person);
	
	/**
	 * Inserta un estudiante y los padres
	 * @param studentWithParent estudiante y padres
	 * @return <code>true</code> si la operación se ha realizado con éxito, en caso contrario <code>false</code>
	 */
	public boolean upsertStudent(StudentWithParents studentWithParent);
	
	/**
	 * Obtiene una entidad de persona en funcion de su perfil e id de usuario
	 * @param role perfil
	 * @param idUser id de usuario
	 * @return entida de persona
	 */
	public <T extends Person> T getByUser(String role, String idUser);

	/**
	 * Obtiene un listado de los padres de un alumno 
	 * @param studentId id de alumno
	 * @return listado de los padres
	 */
	public List<UserSearch> findParents(Integer studentId);

	/**
	 * Obtiene un listado de los alumnos de un padre
	 * @param parentId id del padre
	 * @return listado de los alumnos
	 */
	public List<UserSearch> findStudents(Integer parentId);
	
	/**
	 * Elimina una relación padre-alumno
	 * @param idParent id del padre
	 * @param idStudent id del alumno
	 * @return <code>true</code> si la operación se ha realizado con éxito, en caso contrario <code>false</code>
	 */
	public boolean deleteLink(Integer idParent, Integer idStudent);

	/**
	 * Busca estudiantes por curso
	 * @param course id de curso
	 * @return
	 */
	public List<Student> searchStudent(Integer course);

	/**
	 * Recupera un mapa con el numero de alumnos por clase
	 * @param lstGroups 
	 * @return mapa con el numero de alumnos por clase
	 */
	public Map<Integer,Long> getMapGroupCount(List<Integer> lstGroups);

//	/**
//	 * Recupera un listado de estudiantes disponibles para formar una clase, es decir,
//	 * los que pertenencen a la clase y los del curso sin clase asignada
//	 * @param course curso
//	 * @param group clase
//	 * @return listado de estudiantes disponibles
//	 */
//	public List<UserSearch> getStudensToAddGroup(Integer course, Integer group);

	/**
	 * Recupera un listado de estudiantes sin clase asignada por curso
	 * @param course curso
	 * @return listado de estudiantes sin clase asignada
	 */
	public List<Student> getFreeStudensByCourse(Integer course);

	/**
	 * Actualiza el campo clase de los alumnos
	 * @param idGroup id de clase
	 * @param lstStudents listado de alumnos que pertenecen a esa clase
	 * @throws Exception 
	 */
	public void saveStudentsGroup(Integer idGroup, List<Integer> lstStudents) throws Exception;

	/**
	 * Recupera un listado de alumnos por grupo
	 * @param idGroup grupo
	 * @return listado de alumnos
	 */
	public List<Student> getStudensByGroup(Integer idGroup);
	
	/**
	 * Vacia el campo clase de todos los alumnos de una clase
	 * @param idGroup clase
	 */
	public void deleteStudentsGroup(Integer idGroup);

	/**
	 * Devuelve un listado de personas por tipo
	 * @param classPerson tipo de persona
	 * @return listado de personas
	 */
	public  <T extends Person> List<T> getByClass(Class<T> classPerson);

	/**
	 * Devuelve una persona por id y tipo
	 * @param id id persona
	 * @param classPerson tipo
	 * @return persona
	 */
	public <T extends Person> T getById(Integer id, Class<T> classPerson);

	/**
	 * Devuelve un listado de personas por tipo, incluyendo historico
	 * @param classPerson tipo de persona
	 * @return listado de personas
	 */
	public  <T extends Person> List<T> getByClassHistoric(Class<T> classPerson);
	
}
