/**
 * 
 */
package es.uned.lsi.pfg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.uned.lsi.pfg.model.UserSearch;
import es.uned.lsi.pfg.service.users.UsersService;
import es.uned.lsi.pfg.utils.Constans;

/**
 * Controlador de modales genericas
 * @author Carlos Navalon Urrea
 *
 */
@Controller
@RequestMapping("/modal")
public class ModalController {

	private final static Logger logger = LoggerFactory.getLogger(ModalController.class);

	@Autowired
	UsersService usersService;
	
	@RequestMapping(value="/alert", method = RequestMethod.POST)
	public ModelAndView getAlertModal(@RequestParam String text) throws Exception{
		logger.debug("getAlertModal: " + text);
		ModelAndView model = new ModelAndView("alert");
		model.addObject("text", text);
		return model;
	}
	
	@RequestMapping(value="/confirm", method = RequestMethod.POST)
	public ModelAndView getConfirmModal(@RequestParam String text) throws Exception{
		logger.debug("getConfirmModal: " + text);
		ModelAndView model = new ModelAndView("confirm");
		model.addObject("text", text);
		return model;
	}
	
	@RequestMapping(value="/searchParent")
	public ModelAndView searchParent() throws Exception{
		logger.debug("searchParent" );
		ModelAndView model = new ModelAndView("searchParent");
		model.addObject("lstParents",usersService.search(new UserSearch(null, Constans.ROLE_PARENT, null, null, null, null)));
		return model;
	}
}
