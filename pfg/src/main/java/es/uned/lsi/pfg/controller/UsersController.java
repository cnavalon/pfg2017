package es.uned.lsi.pfg.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
		try {
			response = messageSource.getMessage("user.delete", null, locale);
			logger.debug("deleteUser: Correcto " + id);
		} catch (Exception e) {
			response = messageSource.getMessage("user.delete.error", null, locale);
			logger.debug("deleteUser: Erroneo " + id);
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
		logger.debug("addUser: ");
		
		String title = messageSource.getMessage("user.title.new", null, locale);
		User user = new User();
		
		return getUserForm(title, user);
	}

	/**
	 * Obtiene a la pagina de formulario de usuario
	 * @param title título a mostrar en la pagina
	 * @param user usuario 
	 * @return la pagina de formulario de usuario
	 */
	private ModelAndView getUserForm(String title, User user) {
		ModelAndView model = new ModelAndView();
	
		model.setViewName("userForm");
		model.addObject("title", title);
		model.addObject("user", user);
		model.addObject("lstRoles", rolesService.getAllRoles());
		
		return model;
	}

}
