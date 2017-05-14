package es.uned.lsi.pfg.controller;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.uned.lsi.pfg.model.Role;
import es.uned.lsi.pfg.utils.Constans;

/**
 * Controlador de pagina de inicio
 * @author Carlos Navalon Urrea
 */
@Controller
public class HomeController {
	
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Obtiene la pagina de inicio
	 * @return pagina de inicio
	 * @throws Exception
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView home () throws Exception{
		logger.debug("home");
		ModelAndView model = new ModelAndView("home");
		return model;
	}
	
}
