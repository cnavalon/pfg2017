package es.uned.lsi.pfg.controller;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.uned.lsi.pfg.utils.Constans;

/**
 * @author Carlos Navalon Urrea
 * Controlador de pagina de inicio
 */
@Controller
public class HomeController {
	
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Devuelve la pagina de inicio segun el perfil
	 * @param session sesion de usuario
	 * @return pagina de inicio del perfil
	 * @throws Exception
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView redirectHome (HttpSession session) throws Exception{
		logger.debug("redirectHome");
		ModelAndView model = new ModelAndView();
		
		String roleId = (String)session.getAttribute(Constans.SESSION_ROLE_HOME);
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		
		switch (roleId) {
		case Constans.ROLE_ADMIN:
			model = getAdminHome(model, userId);
			break;
		case Constans.ROLE_TEACHER:
			model = getTeacherHome(model, userId);
			break;
		case Constans.ROLE_STUDENT:
			model = getStudentHome(model, userId);
			break;
		case Constans.ROLE_PARENT:
			model = getParentHome(model, userId);
			break;
		default:
			break;
		}

		return model;
	}
	
	/**
	 * Obtiene la pagina de inicio del perfil administrador
	 * @param model
	 * @param userId id de usuario
	 * @return pagina de inicio del perfil administrador
	 */
	private ModelAndView getAdminHome(ModelAndView model, String userId) {
		logger.debug("getAdminHome");
		model.setViewName("adminHome");
		model.addObject("greeting", "Bienvenido " + userId + " a la pagina principal de administrador");
		return model;
	}
	
	/**
	 * Obtiene la pagina de inicio del perfil profesor
	 * @param model
	 * @param userId id de usuario
	 * @return pagina de inicio del perfil profesor
	 */
	private ModelAndView getTeacherHome(ModelAndView model, String userId) {
		logger.debug("getTeacherHome");
		model.setViewName("teacherHome");
		model.addObject("greeting", "Bienvenido " + userId + " a la pagina principal de profesor");
		return model;
	}
	
	/**
	 * Obtiene la pagina de inicio del perfil estudiante
	 * @param model
	 * @param userId id de usuario
	 * @return pagina de inicio del perfil estudiante
	 */
	private ModelAndView getStudentHome(ModelAndView model, String userId) {
		logger.debug("getStudentHome");
		model.setViewName("studentHome");
		model.addObject("greeting", "Bienvenido " + userId + " a la pagina principal de alumno");
		return model;
	}
	
	/**
	 * Obtiene la pagina de inicio del perfil padre
	 * @param model
	 * @param userId id de usuario
	 * @return pagina de inicio del perfil padre
	 */
	private ModelAndView getParentHome(ModelAndView model, String userId) {
		logger.debug("getParentHome");
		model.setViewName("parentHome");
		model.addObject("greeting", "Bienvenido " + userId + " a la pagina principal de padre");
		return model;
	}
	
	
}
