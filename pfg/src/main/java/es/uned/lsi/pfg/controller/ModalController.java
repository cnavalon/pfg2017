/**
 * 
 */
package es.uned.lsi.pfg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador de modales genericas
 * @author Carlos Navalon Urrea
 *
 */
@Controller
@RequestMapping("/modal")
public class ModalController {

	private final static Logger logger = LoggerFactory.getLogger(ModalController.class);

	/**
	 * Obtiene un modal de alerta
	 * @param text texto 
	 * @return modal de alerta
	 * @throws Exception
	 */
	@RequestMapping(value="/alert", method = RequestMethod.POST)
	public ModelAndView getAlertModal(@RequestParam String text) throws Exception{
		logger.debug("getAlertModal: " + text);
		ModelAndView model = new ModelAndView("alert");
		model.addObject("text", text);
		return model;
	}
	
	/**
	 * Obtiene un modal de confirmacion
	 * @param text texto
	 * @returnun modal de confirmacion
	 * @throws Exception
	 */
	@RequestMapping(value="/confirm", method = RequestMethod.POST)
	public ModelAndView getConfirmModal(@RequestParam String text) throws Exception{
		logger.debug("getConfirmModal: " + text);
		ModelAndView model = new ModelAndView("confirm");
		model.addObject("text", text);
		return model;
	}
	
	
}
