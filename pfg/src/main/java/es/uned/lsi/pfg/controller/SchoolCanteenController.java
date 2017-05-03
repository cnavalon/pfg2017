package es.uned.lsi.pfg.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import es.uned.lsi.pfg.model.DailyMenu;
import es.uned.lsi.pfg.model.Menu;
import es.uned.lsi.pfg.service.schoolCanteen.MenuService;

/**
 * Controlador de comedor escolar
 * @author Carlos Navalon Urrea
 */
@Controller
@RequestMapping("/schoolCanteen")
public class SchoolCanteenController {
	
	private static final Logger logger = LoggerFactory.getLogger(SchoolCanteenController.class);
	
	@Autowired
	private MenuService menusService;
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Obtiene la pagina de menu
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/menu", method = RequestMethod.GET)
	public ModelAndView menuPage (Locale locale) throws Exception {
		logger.debug("menuPage");
		ModelAndView model = new ModelAndView("menus");
		model.addObject("lstMenuTypes", menusService.getAllMenuTypes());
		model.addObject("locale", locale.getLanguage());
		return model;
	}
	
	/**
	 * Obtiene un menu mensual
	 * @param month mes
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/getMenuMonth/{month}", method = RequestMethod.GET)
	public DailyMenu[] getMenuMonth (@PathVariable("month") Integer month) throws Exception {
		logger.debug("getMenuMonth: " + month);
		return menusService.getMenuByMonth(month);
	}
	
	/**
	 * Obtiene el formulario de un menu
	 * @param month mes
	 * @param day dia
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/adm/modalMenu/{month}/{day}", method = RequestMethod.GET)
	public ModelAndView modalMenu(@PathVariable("month") Integer month, @PathVariable("day") Integer day) throws Exception {
		logger.debug("modalMenu: "  + month + ", " + day);
		ModelAndView model = new ModelAndView("modalMenu");
		model.addObject("lstMenuTypes", menusService.getAllMenuTypes());
		return model;
	}
	
	/**
	 * Inserta / actualiza / elimina un listado de menus
	 * @param lstMenus listado de menus
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/adm/saveMenu", method = RequestMethod.POST)
	public String saveMenus(@RequestBody List<Menu> lstMenus, Locale locale) throws Exception {
		logger.debug("saveMenus: " + lstMenus);
		String response = "";
		try {
			menusService.save(lstMenus);
			response = messageSource.getMessage("menu.save.ok", null, locale);
		} catch (Exception e) {
			logger.error("Error guardando menus " + lstMenus, e);
			response = messageSource.getMessage("menu.save.error", null, locale);
		}
		return response;
	}

	/**
	 * Elimina un menu diario
	 * @param month mes 
	 * @param day dia
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/adm/deleteMenuDay/{month}/{day}", method = RequestMethod.GET)
	public String deleteMenuDay(@PathVariable("month") Integer month, @PathVariable("day") Integer day, Locale locale) throws Exception {
		logger.debug("deleteMenuDay: "  + month + ", " + day);
		String response = "";
		try {
			menusService.deleteDailyMenu(month, day);
			response = messageSource.getMessage("menu.delete.ok", null, locale);
		} catch (Exception e) {
			logger.error("Error eliminando menu diario"  + month + ", " + day, e);
			response = messageSource.getMessage("menu.delete.error", null, locale);
		}
		return response;
	}
}
