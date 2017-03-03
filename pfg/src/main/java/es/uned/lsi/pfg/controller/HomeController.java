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
	 * Obtiene la pagina de inicio segun el perfil
	 * @param session sesion de usuario
	 * @return pagina de inicio del perfil
	 * @throws Exception
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView redirectHome (HttpSession session) throws Exception{
		logger.debug("redirectHome");
		ModelAndView model = new ModelAndView();
		
		Role role = (Role)session.getAttribute(Constans.SESSION_ROLE);
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		
		switch (role.getIdRole()) {
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
		return model;
	}
	
	
}
