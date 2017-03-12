package es.uned.lsi.pfg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import es.uned.lsi.pfg.model.Option;
import es.uned.lsi.pfg.model.Parent;
import es.uned.lsi.pfg.model.Person;
import es.uned.lsi.pfg.model.SexEnum;
import es.uned.lsi.pfg.model.StudentWithParents;
import es.uned.lsi.pfg.model.Teacher;
import es.uned.lsi.pfg.model.User;
import es.uned.lsi.pfg.model.UserSearch;
import es.uned.lsi.pfg.service.courses.CoursesService;
import es.uned.lsi.pfg.service.users.RolesService;
import es.uned.lsi.pfg.service.users.UsersService;
import es.uned.lsi.pfg.utils.Constans;

/**
 * Controlador de pagina de usuarios
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
	private CoursesService coursesService;
	
	/**
	 * Obtiene la página de buscador de usuarios
	 * Permisos: administrador/profesor
	 * @return la página de buscador de usuarios
	 * @throws Exception
	 */
	@RequestMapping(value="/emp/users", method = RequestMethod.GET)
	public ModelAndView getUsers () throws Exception {
		logger.debug("getUsers");
		
		
		
		ModelAndView model = new ModelAndView();
		model.setViewName("users");
		model.addObject("lstRoles", rolesService.getAllRoles());
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
			@RequestParam String surname1, @RequestParam String surname2 ) throws Exception {
		logger.debug("usersSearch: " + idRole + "," + name + "," + surname1 + "," + surname2);
		UserSearch user = new UserSearch(null, idRole, name, surname1, surname2, null);
		return usersService.search(user);
	}
	
	/**
	 * Elimina un usuario
	 * @param id ID de usuario
	 * @param idRole ID de perfil
	 * @param locale
	 * @return mensaje de resultado
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/adm/deleteUser/{id}/{idRole}", method = RequestMethod.GET )
	public String deleteUser(@PathVariable("id") String id, @PathVariable("idRole") String idRole,
			Locale locale) throws Exception {
		logger.debug("deleteUser: " + id + "," + idRole);
		
		String response = "user.delete.error";
		try {
			Integer idNumber = Integer.parseInt(id);
			if(usersService.delete(idNumber, idRole)){
				logger.debug("Usuario eliminado [" + id + "," + idRole +"]"); 
				response = "user.deleted";
			} else {
				logger.warn("No se ha eliminado el usuario [" + id + "," + idRole +"]"); 
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
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
				
		return getUserForm(title, user);
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
					model.addObject("lstCourses", coursesService.getAllCourses());
					model.addObject("parent1", new Parent());
					model.addObject("parent2", new Parent());
					model.addObject("userPar1", new User());
					model.addObject("userPar2", new User());
					model.addObject("parRole", rolesService.getRole(Constans.ROLE_PARENT));
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

	private Object getLstSex(Locale locale) {
		List<Option> lstSex = new ArrayList<Option>();
		for(SexEnum sex : SexEnum.values()){
			lstSex.add(new Option(sex.name(), messageSource.getMessage("user.sex." + sex.name(), null, locale)));
		}
		return lstSex;
	}

	private Object getLstDNI() {
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
	 * @param locale 
	 * @return el modelo de la pagina de formulario de usuario
	 */
	private ModelAndView getUserForm(String title, User user) {
		ModelAndView model = new ModelAndView();
		
		model.setViewName("userForm");
		model.addObject("title", title);
		model.addObject("user", user);
		model.addObject("lstRoles", rolesService.getAllRoles());

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
	
	private <T extends Person> String upsertPerson(T person, Locale locale) {
		try {
			if(usersService.upsert(person))
				return "";	
		} catch (Exception e) {
			logger.error("ERROR: " + e.getMessage(), e);
		}
		return messageSource.getMessage("user.save.error", null, locale);
	}
	
}
