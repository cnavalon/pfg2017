package es.uned.lsi.pfg.controller;

import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import es.uned.lsi.pfg.model.Role;
import es.uned.lsi.pfg.model.User;
import es.uned.lsi.pfg.service.RolesService;
import es.uned.lsi.pfg.service.UsersService;

/**
 * @author Carlos Navalon Urrea
 * Controlador de pagina de usuarios
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
	 * Obtiene la página de listado de usuarios
	 * Permisos: administrador
	 * @return la página de listado de usuarios
	 * @throws Exception
	 */
	@RequestMapping(value="/adm/list", method = RequestMethod.GET)
	public ModelAndView getUsersList () throws Exception {
		logger.debug("getUsersList");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("usersList");
		model.addObject("lstUsers", usersService.getAllUsers());
		
		Map<String,String> htRoles = new Hashtable<String,String>();
		List<Role> lstRoles = rolesService.getAllRoles();
		for(Role role : lstRoles){
			htRoles.put(role.getRole(), role.getName());
		}
		model.addObject("mapRoles", htRoles);
		
		return model;
	}
	
	/**
	 * Elimina un usuario
	 * @param id identificador del usuario a eliminar
	 * @param locale 
	 * @return mensaje a mostrar con el resultado de la operación
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/deleteUser/{id}", method = RequestMethod.GET )
	@ResponseBody
	public String deleteUser(@PathVariable("id") String id, Locale locale) throws Exception {
		logger.debug("deleteUser: " + id);
		String response = null;
		String[] args = {id};
		
		if(usersService.delete(id)){
			response = messageSource.getMessage("user.deleted", args, locale);
		} else {
			logger.warn("Error al elimina usuario " + id);
			response = messageSource.getMessage("user.delete.error", args, locale);
		}
		
		return response;
	}
	
	/**
	 * Obtiene la página de nuevo usuario
	 * @param locale
	 * @return la página de nuevo usuario
	 */
	@RequestMapping(value = "/adm/newUser", method = RequestMethod.GET )
	public ModelAndView addUser(Locale locale) {
		logger.debug("addUser");
		
		String title = messageSource.getMessage("user.title.new", null, locale);
		User user = new User();
		
		return getUserForm(title, user, locale);
	}
	
	/**
	 * Obtiene la página de edicion de usuario
	 * @param id id de usuario
	 * @param locale
	 * @return la página de edicion de usuario
	 */
	@RequestMapping(value = "/adm/editUser/{id}", method = RequestMethod.GET )
	public ModelAndView editUser(@PathVariable("id") String id, Locale locale) {
		logger.debug("editUser: " + id);
		String title = messageSource.getMessage("user.title.edit", null, locale);
		User user = usersService.getUser(id);
		
		return getUserForm(title, user, locale);
	}
	
	/**
	 * Obtiene a la pagina de formulario de usuario
	 * @param title título a mostrar en la pagina
	 * @param user usuario 
	 * @param locale 
	 * @return la pagina de formulario de usuario
	 */
	private ModelAndView getUserForm(String title, User user, Locale locale) {
		ModelAndView model = new ModelAndView();
	
		model.setViewName("userForm");
		model.addObject("title", title);
		model.addObject("user", user);
		model.addObject("lstRoles", rolesService.getAllRoles());
		model.addObject("locale",locale.getLanguage());
		
		return model;
	}

	/**
	 * Inserta un usuario nuevo
	 * @param user el usuario
	 * @return 	0: operacion correcta
	 * 			1: error
	 * 			2: ya existe usuario
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/addUser", method = RequestMethod.POST)
	@ResponseBody
	public Integer addUser(@ModelAttribute User user) throws Exception {
		logger.debug("addUser: " + user);
		try {
			if(user != null && user.getId() != null){
				if(!usersService.existsUser(user.getId())){
					if(usersService.save(user)){
						logger.debug("user " + user.getId() + " saved");
						return 0;
					}
				} else {
					logger.debug("already exists user with username " + user.getId());
					return 2;
				}
			}
			return 1;
		} catch (Exception e) {
			logger.error("ERROR: " + e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Actualiza un usuario
	 * @param user el usuario
	 * @return 	0: operacion correcta
	 * 			1: error
	 * @throws Exception
	 */
	@RequestMapping(value = "/adm/updateUser", method = RequestMethod.POST)
	@ResponseBody
	public Integer updateUser(@ModelAttribute User user) throws Exception {
		logger.debug("updateUser: " + user);
		try {
			if(user != null && user.getId() != null){
				if(usersService.save(user)){
					logger.debug("user " + user.getId() + " saved");
					return 0;
				}
			}
			return 2;
		} catch (Exception e) {
			logger.error("ERROR: " + e.getMessage());
			throw e;
		}
	}
}
