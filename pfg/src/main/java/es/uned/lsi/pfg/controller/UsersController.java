package es.uned.lsi.pfg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import es.uned.lsi.pfg.model.Admin;
import es.uned.lsi.pfg.model.DNITypeEnum;
import es.uned.lsi.pfg.model.Group;
import es.uned.lsi.pfg.model.Option;
import es.uned.lsi.pfg.model.Parent;
import es.uned.lsi.pfg.model.Person;
import es.uned.lsi.pfg.model.SexEnum;
import es.uned.lsi.pfg.model.StudentWithParents;
import es.uned.lsi.pfg.model.Teacher;
import es.uned.lsi.pfg.model.User;
import es.uned.lsi.pfg.model.UserSearch;
import es.uned.lsi.pfg.service.groups.GroupsService;
import es.uned.lsi.pfg.service.users.RolesService;
import es.uned.lsi.pfg.service.users.UsersService;
import es.uned.lsi.pfg.utils.Constans;

/**
 * Controlador de usuarios
 * @author Carlos Navalon Urrea
 */
@Controller
@RequestMapping("/users")
public class UsersController {
	private static Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
    private MessageSource messageSource;
	
	@Autowired
	private GroupsService groupService;
	
	/**
	 * Obtiene la página de buscador de usuarios
	 * @return la página de buscador de usuarios
	 * @throws Exception
	 */
	@RequestMapping(value="/emp/users", method = RequestMethod.GET)
	public ModelAndView getUsers (HttpSession session) throws Exception {
		logger.debug("getUsers");
		
		UserSearch userSearch = (UserSearch) session.getAttribute("userSearch");
		boolean cached = true;
		if(userSearch == null){
			userSearch = new UserSearch();
			cached = false;
		}
		
		ModelAndView model = new ModelAndView();
		model.setViewName("users");
		model.addObject("lstRoles", rolesService.getAllRoles());
		model.addObject("userSearch", userSearch);
		model.addObject("cached", cached);
		return model;
	}
	
	/**
	 * Busca usuarios por los criterios de busqueda
	 * @param idRole perfil 
	 * @param name nombre
	 * @param surname1 apellido 1
	 * @param surname2 apellido 2
	 * @return listado de usuarios que cumplen los criterios
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/emp/usersSearch", method = RequestMethod.POST)
	public List<UserSearch> usersSearch (@RequestParam String idRole, @RequestParam String name, 
			@RequestParam String surname1, @RequestParam String surname2, HttpSession session) throws Exception {
		logger.debug("usersSearch: " + idRole + "," + name + "," + surname1 + "," + surname2);
		UserSearch user = new UserSearch(null, idRole, name, surname1, surname2, null);
		List<UserSearch> lstResult = usersService.search(user);
		session.setAttribute("userSearch", user);
		
		return lstResult;
	}
	
	/**
	 * Elimina un usuario
	 * @param idUser ID de usuario
	 * @param locale
	 * @return mensaje de resultado
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/adm/deleteUser/{idUSer}", method = RequestMethod.GET )
	public String deleteUser(@PathVariable("idUSer") String idUser, Locale locale) throws Exception {
		logger.debug("deleteUser: " + idUser);
		
		String response = "user.delete.error";
		
		if(usersService.delete(idUser)){
			logger.debug("Usuario eliminado: " + idUser); 
			response = "user.deleted";
		} else {
			logger.error("No se ha eliminado el usuario: " + idUser); 
		}
		return messageSource.getMessage(response, null, locale);
	}
	
	/**
	 * Elimina una relacion padre-alumno
	 * @param idParent id del padre
	 * @param idStudent id del alumno
	 * @param locale
	 * @return mensaje de resultado
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/adm/deleteParentLink/{idParent}/{idStudent}", method = RequestMethod.GET )
	public String deleteParentLink(@PathVariable("idParent") Integer idParent, @PathVariable("idStudent") Integer idStudent,
			Locale locale) throws Exception {
		logger.debug("deleteParentLink: padre " + idParent + ", alumno " + idStudent);
		
		String response = "user.deletedRelation.error";
		
		if(usersService.deleteLink(idParent, idStudent)){
			logger.debug("Usuario eliminado: padre " + idParent + ", alumno " + idStudent); 
			response = "user.deletedRelation";
		} else {
			logger.error("No se ha eliminado el usuario: padre " + idParent + ", alumno " + idStudent); 
		}
		return messageSource.getMessage(response, null, locale);
	}
	
	
	/**
	 * Obtiene la página de nuevo usuario
	 * @param locale
	 * @return la página de nuevo usuario
	 */
	@RequestMapping(value = "/adm/newUser", method = RequestMethod.GET )
	public ModelAndView newUser(Locale locale) {
		logger.debug("newUser");
		
		String title = messageSource.getMessage("user.title.new", null, locale);
		User user = new User();
				
		return getUserForm(title, user, true);
	}
	
	/**
	 * Obtiene la pagina de edicicion de usuario
	 * @param locale
	 * @param idUser id del usuario
	 * @return pagina de edición de usuario
	 */
	@RequestMapping(value = "/adm/editUser/{idUser}", method = RequestMethod.GET )
	public ModelAndView editUser(Locale locale, @PathVariable("idUser")String idUser) {
		logger.debug("editUser: " + idUser);
		
		String title = messageSource.getMessage("user.title.edit", null, locale);
		User user = usersService.getUser(idUser);
				
		return getUserForm(title, user, true);
	}
	
	/**
	 * Obtiene la página de visualización de usuario
	 * @param locale
	 * @param idUser id del usuario
	 * @return página de visualización de usuario
	 */
	@RequestMapping(value = "/emp/viewUser/{idUser}", method = RequestMethod.GET )
	public ModelAndView viewUser(Locale locale, @PathVariable("idUser")String idUser) {
		logger.debug("viewUser: " + idUser);
		
		String title = messageSource.getMessage("user.title.view", null, locale);
		User user = usersService.getUser(idUser);
				
		return getUserForm(title, user, false);
	}
	
	/**
	 * Obtiene el formulario de nueva persona segun su perfil
	 * @param locale
	 * @param idRole perfil
	 * @return el formulario de nueva persona 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/adm/newPerson/{idRole}", method = RequestMethod.GET )
	public ModelAndView newPerson(Locale locale, @PathVariable("idRole") String idRole) {
		logger.debug("newPerson: " + idRole);
		
		if(idRole != null && !idRole.equals("")){
			try {
				ModelAndView model = new ModelAndView();
				model.setViewName(findFormByRole(idRole));
				model.addObject("person", usersService.findClassRole(idRole).getConstructor().newInstance());
				model.addObject("locale",locale.getLanguage());
				model.addObject("lstDNI", getLstDNI());
				if(idRole.equals(Constans.ROLE_STUDENT)){
					model.addObject("lstSex", getLstSex(locale));
					model.addObject("lstCourses", groupService.getAllCourses());
				}
				return model;
			} catch (Exception e) {
				logger.error("ERROR obteniendo formulario de persona " + idRole, e);
			}
		} else {
			logger.warn("Perfil nulo o vacío");
		}
		return null;
	}
	
	/**
	 * Obtiene el formulario de edicion de persona segun el perfil
	 * @param locale
	 * @param idUser id de usuario
	 * @param idRole perfil
	 * @return el formulario de edicion de persona
	 */
	@RequestMapping(value = "/emp/updatePerson/{idUser}/{idRole}", method = RequestMethod.GET )
	public ModelAndView updatePerson(Locale locale, @PathVariable("idUser") String idUser, @PathVariable("idRole") String idRole) {
		logger.debug("updatePerson: " + idUser + ", " + idRole);
		
		if(idRole != null && !idRole.equals("")){
			try {
				ModelAndView model = new ModelAndView();
				model.setViewName(findFormByRole(idRole));
				Person person = usersService.getByUser(idRole, idUser);
				model.addObject("person", person);
				model.addObject("locale",locale.getLanguage());
				if(idRole.equals(Constans.ROLE_STUDENT)){
					model.addObject("lstDNI", getLstDNI());
					model.addObject("lstSex", getLstSex(locale));
					model.addObject("lstCourses", groupService.getAllCourses());
					model.addObject("lstParents", usersService.findParents(person.getId()));
				} else if(idRole.equals(Constans.ROLE_PARENT)){
					model.addObject("lstDNI", getLstDNI());
					model.addObject("lstStudents", usersService.findStudents(person.getId()));
				}
				return model;
			} catch (Exception e) {
				logger.error("ERROR obteniendo formulario de persona " + idRole, e);
			}
		} else {
			logger.warn("Perfil nulo o vacío");
		}
		return null;
	}

	/**
	 * Obtiene un listado con las opciones de sexos
	 * @param locale
	 * @return listado con las opciones de sexos
	 */
	private List<Option> getLstSex(Locale locale) {
		List<Option> lstSex = new ArrayList<Option>();
		for(SexEnum sex : SexEnum.values()){
			lstSex.add(new Option(sex.name(), messageSource.getMessage("user.sex." + sex.name(), null, locale)));
		}
		return lstSex;
	}
	
	/**
	 * Obtiene un listado con los tipos de documentos
	 * @return listado con los tipos de documentos
	 */
	private List<String> getLstDNI() {
		List<String> lstDni = new ArrayList<String>();
		for(DNITypeEnum dni: DNITypeEnum.values()){
			lstDni.add(dni.name());
		}
		return lstDni;
	}

	/**
	 * Encuentra el formulario de persona en funcion del perfil
	 * @param idRole perfil
	 * @return el nombre de la pagina del formulario
	 */
	private String findFormByRole(String idRole) throws Exception{
		switch (idRole) {
		case Constans.ROLE_ADMIN:
			return "adminForm";
		case Constans.ROLE_TEACHER:
			return "teacherForm";
		case Constans.ROLE_STUDENT:
			return "studentForm";
		case Constans.ROLE_PARENT:
			return "parentForm";
		default:
			throw new Exception("No existe el perfil");
		}
	}
	
	/**
	 * Comprueba si el id de usuario es viable
	 * @param locale
	 * @param idUser id de usuario
	 * @return mensaje de error si existe, en caso contrario cadena vacía.
	 */
	@ResponseBody
	@RequestMapping(value = "/adm/checkUser/{idUser}", method = RequestMethod.GET )
	public String checkIdUser(Locale locale, @PathVariable("idUser") String idUser) {
		logger.debug("checkIdUser: " + idUser);
		String response = "";
		if(idUser != null && !idUser.equals("")){
			try {
				if(usersService.getUser(idUser) != null){
					response = messageSource.getMessage("user.id.exist", null, locale);
				}
			} catch (Exception e) {
				logger.error("ERROR obteniendo formulario de persona " + idUser, e);
				response = messageSource.getMessage("user.error.id", null, locale);
			}
		} else {
			logger.warn("ID de usuario nulo o vacío");
			response = messageSource.getMessage("user.id.empty", null, locale);
		}
		return response;
	}
	
	/**
	 * Genera el modelo de la pagina de formulario de usuario
	 * @param title titulo de la pagina
	 * @param user entidad usuario del usuario
	 * @param b 
	 * @param locale 
	 * @return el modelo de la pagina de formulario de usuario
	 */
	private ModelAndView getUserForm(String title, User user, boolean editable) {
		ModelAndView model = new ModelAndView();
		
		model.setViewName("userForm");
		model.addObject("title", title);
		model.addObject("user", user);
		model.addObject("lstRoles", rolesService.getAllRoles());
		model.addObject("editable", editable);

		return model;
	}
	
	/**
	 * Inserta o actualiza un administrador
	 * @param admin administrador
	 * @param locale
	 * @return mensaje de error si existe, en caso contrario cadena vacia
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/upsertAdmin", method = RequestMethod.POST)
	@ResponseBody
	public String upsertAdmin(@RequestBody Admin admin, Locale locale) throws Exception {
		logger.debug("upsertAdmin: " + admin);
		return upsertPerson(admin, locale);
	}
	
	/**
	 * Inserta o actualiza un profesor
	 * @param teacher profesor
	 * @param locale
	 * @return mensaje de error si existe, en caso contrario cadena vacia
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/upsertTeacher", method = RequestMethod.POST)
	@ResponseBody
	public String upsertTeacher(@RequestBody Teacher teacher, Locale locale) throws Exception {
		logger.debug("upsertTeacher: " + teacher);
		return upsertPerson(teacher, locale);
	}
	
	/**
	 * Inserta o actualiza un alumno y sus padres
	 * @param studentWithParent alumno y sus padres
	 * @param locale
	 * @return mensaje de error si existe, en caso contrario cadena vacia
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/upsertStudent", method = RequestMethod.POST)
	@ResponseBody
	public String upsertStudent(@RequestBody StudentWithParents studentWithParent, Locale locale) throws Exception {
		logger.debug("upsertStudent: " + studentWithParent);
		try {
			if(usersService.upsertStudent(studentWithParent)){
				return "";
			}
		} catch (Exception e) {
			logger.error("ERROR: " + e.getMessage(), e);
		}
		return messageSource.getMessage("user.save.error", null, locale);
	}
	
	/**
	 * Inserta o actualiza un padre
	 * @param parent padre
	 * @param locale
	 * @return mensaje de error si existe, en caso contrario cadena vacia
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/upsertParent", method = RequestMethod.POST)
	@ResponseBody
	public String upsertParent(@RequestBody Parent parent, Locale locale) throws Exception {
		logger.debug("upsertParent: " + parent);
		return upsertPerson(parent, locale);
	}
	
	/**
	 * Inserta o actualiza una persona
	 * @param person persona
	 * @param locale
	 * @return mensaje de error si existe, en caso contrario cadena vacia
	 */
	private <T extends Person> String upsertPerson(T person, Locale locale) {
		try {
			if(usersService.upsert(person))
				return "";	
		} catch (Exception e) {
			logger.error("ERROR: " + e.getMessage(), e);
		}
		return messageSource.getMessage("user.save.error", null, locale);
	}
	
	@RequestMapping(value = "/adm/getGroups/{course}", method = RequestMethod.GET)
	@ResponseBody
	public List<Group> getGroups(@PathVariable("course") Integer course) throws Exception {
		logger.debug("getGroups: " + course);
		return groupService.getGroupsByCourse(course);
	}
}
