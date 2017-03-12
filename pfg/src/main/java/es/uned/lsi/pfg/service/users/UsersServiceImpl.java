/**
 * 
 */
package es.uned.lsi.pfg.service.users;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uned.lsi.pfg.dao.users.PersonDAO;
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
//	@Autowired
//	private StudentDAO studentDAO;
//	
//	@Autowired
//	private ParentDAO parentDAO;
	
	@Autowired
	private PersonDAO personDAO;
	
	@Autowired
	private ShaPasswordEncoder shaPasswordEncoder;
	
	@Override
	public String getFullName(String idUser, String idRole) {
		logger.debug("getFullName: " + idUser + "," + idRole);
		String fullName = "";
		try {
			Class<? extends Person> classPerson = findClassRole(idRole);
			Person person = personDAO.findByIdUser(idUser, classPerson);
			fullName = createFullName(person);
		} catch (Exception e) {
			logger.error("ERROR, obteniendo nombre completo del usuario: " + idUser + ", " + idRole,e);
		}
		return fullName;
	}

	private String createFullName(Person person) {
		String fullName = person.getName() + " " + person.getSurname1();
		if(person.getSurname2() != null && !person.getSurname2().equals("")){
			fullName += " " + person.getSurname2();
		}
		return fullName;
	}

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
		if(save(person.getUser())){
			return personDAO.upsert(person);
		}
		return false;
	}
	
	private boolean save(User user) {
		user.setPassword(shaPasswordEncoder.encodePassword(user.getPassword(), null));
		user.setEnabled(true);
		return usersDAO.upsert(user);
	}

	@Override
	@Transactional
	public boolean delete(Integer id, String idRole) {
		logger.debug("delete: " + id + "," + idRole);
		
		Person person = personDAO.find(id, findClassRole(idRole));
		if(person != null){
			person.setEnabled(false);
			if(personDAO.upsert(person)){
				User user = usersDAO.findUser(person.getIdUser());
				if(user != null){
					user.setEnabled(false);
					return usersDAO.upsert(user);
				}
			}
		}
		return false;
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
		return convertToUserSearchList(personDAO.searchUsers(userSearch, classperson), role);
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
	 * @return listado de {@link UserSearch}
	 */
	private List<UserSearch> convertToUserSearchList(List<? extends Person> lstPersons, String idRole) {
		if(lstPersons == null){
			return null;
		}
		List<UserSearch> lstUserSearch = new ArrayList<UserSearch>();
		for(Person person : lstPersons){
			try {
				UserSearch user = new UserSearch(person.getId(), idRole, person.getName(), person.getSurname1(), person.getSurname2(), person.getIdUser());
				lstUserSearch.add(user);
			} catch (Exception e) {
				logger.error("ERROR, al convertir el usuario " + person, e);
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
			
			save(student.getUser());
			personDAO.upsert(student);
			student = personDAO.findByIdUser(student.getIdUser(), Student.class);
			logger.debug("Alumno insertado: " + student);
			
			if(parent1 != null){
				if(parent1.getId() == null || parent1.getId().equals("")){
					save(parent1.getUser());
					personDAO.upsert(parent1);
					parent1 = personDAO.findByIdUser(parent1.getIdUser(), Parent.class);
					logger.debug("Padre 1 insertado: " + parent1);
				}
				studentParentDAO.insert(new StudentParent(student.getId(), parent1.getId()));
				logger.debug("Relacion alumno " + student.getId() + " padre 1 "+ parent1.getId() + " insertada");
			}
			
			if(parent2 != null){
				if(parent2.getId() == null || parent1.getId().equals("")){
					save(parent2.getUser());
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

}
