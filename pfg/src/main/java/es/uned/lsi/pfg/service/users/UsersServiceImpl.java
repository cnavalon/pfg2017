/**
 * 
 */
package es.uned.lsi.pfg.service.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uned.lsi.pfg.dao.users.PersonDAO;
import es.uned.lsi.pfg.dao.users.StudentDAO;
import es.uned.lsi.pfg.dao.users.StudentParentDAO;
import es.uned.lsi.pfg.dao.users.UsersDAO;
import es.uned.lsi.pfg.model.Admin;
import es.uned.lsi.pfg.model.Parent;
import es.uned.lsi.pfg.model.Person;
import es.uned.lsi.pfg.model.Student;
import es.uned.lsi.pfg.model.StudentParent;
import es.uned.lsi.pfg.model.StudentWithParents;
import es.uned.lsi.pfg.model.Teacher;
import es.uned.lsi.pfg.model.User;
import es.uned.lsi.pfg.model.UserSearch;
import es.uned.lsi.pfg.utils.Constans;

/**
 * Implementacion del servicio de usuarios
 * @author Carlos Navalon Urrea
 */
@Service
public class UsersServiceImpl implements UsersService {

	private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
	
	@Autowired
	private UsersDAO usersDAO;
	
	@Autowired
	private StudentParentDAO studentParentDAO;
	
//	@Autowired
//	private AdminDAO adminDAO;
//	
//	@Autowired
//	private TeacherDAO teacherDAO;
//	
	@Autowired
	private StudentDAO studentDAO;
//	
//	@Autowired
//	private ParentDAO parentDAO;
	
	@Autowired
	private PersonDAO personDAO;
	
	@Autowired
	private ShaPasswordEncoder shaPasswordEncoder;
	

	@Override
	public User getUser(String username) {
		logger.debug("getUser: " + username);
		return usersDAO.findUser(username);
	}
	
	@Override
	public boolean existsUser(String username) {
		logger.debug("existsUser: " + username);
		if(usersDAO.findUser(username) == null){
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public <T extends Person> boolean upsert(T person){
		logger.debug("upsert: " + person);
		try {
			saveUSer(person.getUser());
			personDAO.upsert(person);
			return true;
		} catch (Exception e) {
			logger.error("ERROR insertando o actualizando persona: " + person, e);
		}
		return false;
	}
	
	private void saveUSer(User user) throws Exception {
		if(user.getLastPassword() == null || !user.getPassword().equals(user.getLastPassword())){
			user.setPassword(shaPasswordEncoder.encodePassword(user.getPassword(), null));
		}
		user.setEnabled(true);
		usersDAO.upsert(user);
	}

	@Override
	@Transactional
	public boolean delete(String idUser) {
		try {
			User user = usersDAO.findUser(idUser);
			Integer id = deletePerson(user);
			if(user.getRole().equals(Constans.ROLE_STUDENT)){
				List<StudentParent> lstStudentParents = studentParentDAO.findByStudent(id);
				for(StudentParent studentParent : lstStudentParents){
					studentParentDAO.remove(studentParent);
					if(studentParentDAO.findByParent(studentParent.getParent()).isEmpty()){
						deletePerson(studentParent.getParent(), Constans.ROLE_PARENT);
					}
				}
			} else if(user.getRole().equals(Constans.ROLE_PARENT)){
				List<StudentParent> lstStudentParents = studentParentDAO.findByParent(id);
				for(StudentParent studentParent : lstStudentParents){
					studentParentDAO.remove(studentParent);
				}
			}
			return true;
		} catch (Exception e) {
			logger.error("Error borrando persona: " + idUser);
		}
		return false;
	}
	
	private Integer deletePerson(User user) throws Exception {
		logger.debug("delete: " + user);
		Person person = personDAO.findByIdUser(user.getIdUser(), findClassRole(user.getRole()));
		person.setEnabled(false);
		personDAO.upsert(person);
		
		user.setEnabled(false);
		usersDAO.upsert(user);
		
		return person.getId();
	}

	private void deletePerson(Integer id, String idRole) throws Exception {
		logger.debug("delete: " + id + "," + idRole);
		
		Person person = personDAO.find(id, findClassRole(idRole));
		person.setEnabled(false);
		personDAO.upsert(person);
		
		User user = usersDAO.findUser(person.getIdUser());
		user.setEnabled(false);
		usersDAO.upsert(user);
	}

	@Override
	public List<UserSearch> search(UserSearch userSearch) {
		logger.debug("searchUsers: " + userSearch);
		try{
			if(userSearch.getIdRole() == null || userSearch.getIdRole().equals("")){
				List<UserSearch> lstUserSearch = new ArrayList<UserSearch>();
				
				for(String role : Constans.ROLES_ARRAY){
					List<UserSearch> lstUsersByRole = searchPersons(userSearch, role);
					if(lstUsersByRole != null && !lstUsersByRole.isEmpty()){
						lstUserSearch.addAll(lstUsersByRole);
					}
				}
				return lstUserSearch;
			} else {
				return searchPersons(userSearch, userSearch.getIdRole());
			}
			
		} catch (Exception e) {
			logger.error("ERROR, en la busqueda de usarios",e);
		}
		return null;
	}
	
	/**
	 * Busca las personas que cumplan el criterio de busqueda por perfil
	 * @param userSearch criterios de busqueda
	 * @param role perfil
	 * @return listado de personas del perfil que cumplen el criterio de busqueda
	 */
	private List<UserSearch> searchPersons(UserSearch userSearch, String role){
		Class<? extends Person> classperson = findClassRole(role);
		return convertToUserSearchList(personDAO.searchUsers(userSearch, classperson), role, false);
	}

	
	@Override
	public Class<? extends Person> findClassRole(String role) {
		logger.debug("findClassRole: " + role);
		Class<? extends Person> classperson = null;
		switch (role) {
			case Constans.ROLE_ADMIN:
				classperson =  Admin.class;
				break;
			case Constans.ROLE_TEACHER:
				classperson =  Teacher.class;
				break;
			case Constans.ROLE_STUDENT:
				classperson =  Student.class;
				break;
			case Constans.ROLE_PARENT:
				classperson =  Parent.class;
				break;
			default:
				break;
		}
		return classperson;
	}

	/**
	 * Convierte un listado de {@link Person} a un listado de {@link UserSearch}
	 * @param lstPersons listado de {@link Person}
	 * @param idRole id del perfil del listado de personas
	 * @param checked checked
	 * @return listado de {@link UserSearch}
	 */
	private List<UserSearch> convertToUserSearchList(List<? extends Person> lstPersons, String idRole, boolean checked) {
		List<UserSearch> lstUserSearch = new ArrayList<UserSearch>();
		if(lstPersons != null){
			for(Person person : lstPersons){
				try {
					UserSearch user = new UserSearch(person.getId(), idRole, person.getName(), person.getSurname1(), person.getSurname2(), person.getIdUser(), checked);
					lstUserSearch.add(user);
				} catch (Exception e) {
					logger.error("ERROR, al convertir el usuario " + person, e);
				}
			}
		}
		return lstUserSearch;
	}
	
	@Override
	@Transactional
	public boolean upsertStudent(StudentWithParents studentWithParent) {
		logger.debug("upsertStudent: " + studentWithParent);
		try {
			Student student = studentWithParent.getStudent();
			Parent parent1 = studentWithParent.getParent1();
			Parent parent2 = studentWithParent.getParent2();
			
			saveUSer(student.getUser());
			personDAO.upsert(student);
			student = personDAO.findByIdUser(student.getIdUser(), Student.class);
			logger.debug("Alumno insertado: " + student);
			
			if(parent1 != null){
				if(parent1.getId() == null || parent1.getId().equals("")){
					saveUSer(parent1.getUser());
					personDAO.upsert(parent1);
					parent1 = personDAO.findByIdUser(parent1.getIdUser(), Parent.class);
					logger.debug("Padre 1 insertado: " + parent1);
				}
				studentParentDAO.insert(new StudentParent(student.getId(), parent1.getId()));
				logger.debug("Relacion alumno " + student.getId() + " padre 1 "+ parent1.getId() + " insertada");
			}
			
			if(parent2 != null){
				if(parent2.getId() == null || parent2.getId().equals("")){
					saveUSer(parent2.getUser());
					personDAO.upsert(parent2);
					parent2 = personDAO.findByIdUser(parent2.getIdUser(), Parent.class);
					logger.debug("Padre 2 insertado: " + parent2);
				}
				studentParentDAO.insert(new StudentParent(student.getId(), parent2.getId()));
				logger.debug("Relacion alumno " + student.getId() + " padre 2 "+ parent2.getId() + " insertada");
			}
			return true;
		} catch (Exception e) {
			logger.error("ERROR añadiendo estudiante y padres " + studentWithParent, e);
		}
		return false;
		
		
	}

	@Override
	public <T extends Person> T getByUser(String role, String idUser) {
		logger.debug("getByUser: " + role + ", " + idUser); 
		return (T) personDAO.findByIdUser(idUser, findClassRole(role));
	}
	
	@Override
	public <T extends Person> T getByUser(String idUser) {
		logger.debug("getByUser: " + idUser); 
		User user = usersDAO.findUser(idUser);
		return getByUser(user.getRole(), idUser);
	}

	@Override
	public List<UserSearch> findParents(Integer studentId) {
		logger.debug("findParents: " + studentId);
		try {
			List<Parent> lstParents = findParentsList(studentId);
			return convertToUserSearchList(lstParents, Constans.ROLE_PARENT, false);
		} catch (Exception e) {
			logger.error("ERROR recuperando padres de " + studentId, e );
		}
		return null;
	}
	
	@Override
	public List<Parent> findParentsList(Integer studentId) {
		logger.debug("findStudents: " + studentId);
		try {
			List<Parent> lstParents = new ArrayList<Parent>();
			List<StudentParent> lstParentsIds = studentParentDAO.findByStudent(studentId);
			
			for(StudentParent studentParent : lstParentsIds){
				lstParents.add(personDAO.find(studentParent.getParent(), Parent.class));
			}
			
			return lstParents;
		} catch (Exception e) {
			logger.error("ERROR recuperando padres de " + studentId, e );
		}
		return null;
	}

	@Override
	public List<UserSearch> findStudents(Integer parentId) {
		logger.debug("findStudents: " + parentId);
		try {
			List<Student> lstStudents = findStudentsList( parentId);
			return convertToUserSearchList(lstStudents, Constans.ROLE_STUDENT, false);
		} catch (Exception e) {
			logger.error("ERROR recuperando padres de " + parentId, e );
		}
		return null;
	}
	
	@Override
	public List<Student> findStudentsList(Integer parentId) {
		logger.debug("findStudents: " + parentId);
		try {
			List<Student> lstStudents = new ArrayList<Student>();
			List<StudentParent> lstStudentsIds = studentParentDAO.findByParent(parentId);
			
			for(StudentParent studentParent : lstStudentsIds){
				lstStudents.add(personDAO.find(studentParent.getStudent(), Student.class));
			}
			
			return lstStudents;
		} catch (Exception e) {
			logger.error("ERROR recuperando padres de " + parentId, e );
		}
		return null;
	}

	@Transactional
	@Override
	public boolean deleteLink(Integer idParent, Integer idStudent) {
		logger.debug("deleteLink: " + idParent + ", " + idStudent);
		try {
			StudentParent studentParent = studentParentDAO.find(idStudent,idParent);
			studentParentDAO.remove(studentParent);
			if(studentParentDAO.findByParent(idParent).isEmpty()){
				deletePerson(idParent, Constans.ROLE_PARENT);
			}
			return true;
		} catch (Exception e) {
			logger.error("ERROR eliminando relacion " + idParent + "-" + idStudent, e);		
		}
		return false;
	}

	@Override
	public List<Student> searchStudent(Integer course) {
		logger.debug("searchStudent:" + course );
		return studentDAO.findStudentsByCourse(course);
	}
	
	@Override
	public Map<Integer, Long> getMapGroupCount(List<Integer> lstGroups) {
		logger.debug("getMapGroupCount: " + lstGroups);
		Map<Integer, Long> mapGroupCount = new HashMap<Integer,Long>();
		for(Integer group : lstGroups){
			mapGroupCount.put(group, studentDAO.countStudents(group));
		}
		return mapGroupCount;
	}

//	@Override
//	public List<UserSearch> getStudensToAddGroup(Integer course, Integer group) {
//		logger.debug("getStudensToAddGroup: " + course + ", " + group);
//		List<UserSearch> lstStudents = convertToUserSearchList(studentDAO.findStudentsByCourseWithoutGroup(course),Constans.ROLE_STUDENT, false);
//		if(group != null){
//			lstStudents.addAll(convertToUserSearchList(studentDAO.findStundentsByGroup(group), Constans.ROLE_STUDENT, true));
//		}
//		return lstStudents;
//	}
	
	@Override
	public List<Student> getFreeStudensByCourse(Integer course) {
		logger.debug("getFreeStudensByCourse: " + course );
		return studentDAO.findStudentsByCourseWithoutGroup(course);
	}

	@Override
	public void saveStudentsGroup(Integer idGroup, List<Integer> lstStudents) throws Exception {
		Set<Integer> hsPresentStudents = new HashSet<Integer>(lstStudents);
		List<Student> lstPreviousStundents = studentDAO.findStundentsByGroup(idGroup);
		Set<Integer> hsPreviousStudents = new HashSet<Integer>();
		
		for(Student student : lstPreviousStundents){
			hsPreviousStudents.add(student.getId());
		}
		
		//Eliminamos los descartes
		HashSet<Integer> hsRemove = new HashSet<Integer>(hsPreviousStudents);
		hsRemove.removeAll(hsPresentStudents);
		Integer removed = studentDAO.updateGroup(hsRemove, null);
		if(removed != hsRemove.size()){
			logger.error("No se han eliminado todos los alumnos (" + removed + " de " + hsRemove.size() + ")");
			throw new Exception("No se han eliminado todos los alumnos (" + removed + " de " + hsRemove.size() + ")" );
		}
		
		//Actualizamos los nuevos
		HashSet<Integer> hsAdd = new HashSet<Integer>(hsPresentStudents);
		hsAdd.removeAll(hsPreviousStudents);
		Integer added = studentDAO.updateGroup(hsAdd, idGroup);
		if(added != hsAdd.size()){
			logger.error("No se han eliminado todos los alumnos (" + added + " de " + hsAdd.size() + ")");
			throw new Exception("No se han eliminado todos los alumnos (" + added + " de " + hsAdd.size() + ")" );
		}
	}

	@Override
	public List<Student> getStudensByGroup(Integer idGroup) {
		logger.debug("getStudensByGroup: " + idGroup);
		return studentDAO.findStundentsByGroup(idGroup);
	}

	@Override
	public void deleteStudentsGroup(Integer idGroup) {
		logger.debug("deleteStudentsGroup: " + idGroup);
		studentDAO.removeGroup(idGroup);
	}
	
	@Override
	public <T extends Person> List<T> getByClass(Class<T> classPerson){
		logger.debug("getByClass: " + classPerson);
		return personDAO.findAll(classPerson);
	}

	@Override
	public <T extends Person> T getById(Integer id, Class<T> classPerson) {
		logger.debug("getById: " + id + ", " + classPerson);
		return personDAO.find(id, classPerson);
	}

	@Override
	public <T extends Person> List<T> getByClassHistoric(Class<T> classPerson) {
		logger.debug("getByClassHistoric: " + classPerson);
		return personDAO.findAllHistoric(classPerson);
	}


}
