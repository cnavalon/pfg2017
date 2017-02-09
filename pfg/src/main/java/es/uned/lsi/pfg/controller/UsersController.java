package es.uned.lsi.pfg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(value="/adm/list", method = RequestMethod.GET)
	public ModelAndView getUsersList () throws Exception{
		logger.debug("getUsersList");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("usersList");
		model.addObject("lstUsers", usersService.getAllUsers());
		
		return model;
	}

}
