package es.uned.lsi.pfg.controller;

import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import es.uned.lsi.pfg.model.Role;
import es.uned.lsi.pfg.model.UserSearch;
import es.uned.lsi.pfg.service.users.RolesService;
import es.uned.lsi.pfg.service.users.UsersService;

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
		UserSearch user = new UserSearch(null, idRole, name, surname1, surname2);
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
			if(usersService.delete(idNumber, idRole))
				response = "user.deleted";
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return messageSource.getMessage(response, null, locale);
	}
	
	
	/**
	 * Obtiene la página de nuevo empleado
	 * @param locale
	 * @return la página de nuevo empleado
	 */
//	@RequestMapping(value = "/adm/newEmployee", method = RequestMethod.GET )
//	public ModelAndView addEmployee(Locale locale) {
//		logger.debug("newEmployee");
//		
//		String title = messageSource.getMessage("employees.title.new", null, locale);
//		Employee employee = new Employee();
//		
//		return getEmployeeForm(title, employee, locale);
//	}

	/**
	 * Obtiene la página de edicion de usuario
	 * @param id id de usuario
	 * @param locale
	 * @return la página de edicion de usuario
	 */
//	@RequestMapping(value = "/adm/editUser/{id}", method = RequestMethod.GET )
//	public ModelAndView editUser(@PathVariable("id") String id, Locale locale) {
//		logger.debug("editUser: " + id);
//		String title = messageSource.getMessage("user.title.edit", null, locale);
//		User user = usersService.getActiveUser(id);
//		
//		return getUserForm(title, user, locale);
//	}
	
	
	/**
	 * Obtiene a la pagina de formulario de empleado
	 * @param title título a mostrar en la pagina
	 * @param employee empleado
	 * @param locale 
	 * @return la pagina de formulario de empleado
	 */
//	private ModelAndView getEmployeeForm(String title, Employee employee, Locale locale) {
//		ModelAndView model = new ModelAndView();
//	
//		model.setViewName("userForm");
//		model.addObject("title", title);
//		model.addObject("user", employee);
//		model.addObject("lstRoles", rolesService.getEmployeeRoles());
//		model.addObject("locale",locale.getLanguage());
//		
//		return model;
//	}

	/**
	 * Inserta un usuario nuevo
	 * @param user el usuario
	 * @return 	0: operacion correcta
	 * 			1: error
	 * 			2: ya existe usuario
	 * @throws Exception
	 */
//	@RequestMapping(value = "/adm/addUser", method = RequestMethod.POST)
//	@ResponseBody
//	public Integer addUser(@ModelAttribute User user) throws Exception {
//		logger.debug("addUser: " + user);
//		try {
//			if(user != null && user.getId() != null){
//				if(!usersService.existsUser(user.getId())){
//					if(usersService.save(user)){
//						logger.debug("user " + user.getId() + " saved");
//						return 0;
//					}
//				} else {
//					logger.debug("already exists user with username " + user.getId());
//					return 2;
//				}
//			}
//			return 1;
//		} catch (Exception e) {
//			logger.error("ERROR: " + e.getMessage());
//			throw e;
//		}
//	}
//	
//	/**
//	 * Actualiza un usuario
//	 * @param user el usuario
//	 * @return 	0: operacion correcta
//	 * 			1: error
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/adm/updateUser", method = RequestMethod.POST)
//	@ResponseBody
//	public Integer updateUser(@ModelAttribute User user) throws Exception {
//		logger.debug("updateUser: " + user);
//		try {
//			if(user != null && user.getId() != null){
//				if(usersService.save(user)){
//					logger.debug("user " + user.getId() + " saved");
//					return 0;
//				}
//			}
//			return 2;
//		} catch (Exception e) {
//			logger.error("ERROR: " + e.getMessage());
//			throw e;
//		}
//	}
	
}
