package es.uned.lsi.pfg.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Carlos Navalon Urrea
 * Controlador de inicio de sesion
 */
@Controller
public class LoginController {
	
	/**
	 * Devuelve la pagina de inicio de sesion
	 * @param request
	 * @param response
	 * @param locale
	 * @param error: codigo de error <ul>
	 * 		<li><code>null</code> - no hay error</li>
	 * 		<li>0 - error de autenticacion</li>
	 * 		<li>1 - usuario no autorizado</li>
	 * 		<li>2 - otro error</li>
	 * 		</ul>
	 * @param logout: codigo de cierre de sesion. No <code>null</code> - sesion cerrada
	 * @return pagina de acceso
	 */
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(
		HttpServletRequest request,
		HttpServletResponse response,
		Locale locale,
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			if (error.equals("0")) 
				model.addObject("error", "login.invalid");
			else if (error.equals("1"))
				model.addObject("error", "login.notAuthorized");
			else
				model.addObject("error", "login.unexpectedError");
		}

		if (logout != null) {
			model.addObject("msg", "login.logout");
		}
		model.setViewName("login");

		return model;
	}
	
	/**
	 * Cierra la sesion y redirige a página de inicio de sesion
	 * @param request
	 * @param response
	 * @return redireccion a pagina de inicio de sesion
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";
	}
}