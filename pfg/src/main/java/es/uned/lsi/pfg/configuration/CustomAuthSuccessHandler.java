/**
 * 
 */
package es.uned.lsi.pfg.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import es.uned.lsi.pfg.dao.RolesDAO;
import es.uned.lsi.pfg.model.Role;
import es.uned.lsi.pfg.service.UsersService;
import es.uned.lsi.pfg.utils.Constans;

/**
 * @author Carlos Navalon Urrea
 * Gestor de autenticaciones correctas
 */
@Component
public class CustomAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private static Logger logger = LoggerFactory.getLogger(CustomAuthSuccessHandler.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private RolesDAO rolesDAO;
	
	@Autowired
	private UsersService usersService;

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		String targetUrl = "/";
		try {
			HttpSession session = request.getSession(true);
			List<Role> lstRoles = getRoles(authentication);
			String userId = authentication.getName();
			
			if(lstRoles.isEmpty()){
				logger.warn("The user has not any role");
				new SecurityContextLogoutHandler().logout(request, response, authentication);
				targetUrl = "/login?error=1";
			} else {
				session.setAttribute(Constans.SESSION_ROLES, lstRoles);
				session.setAttribute(Constans.SESSION_ROLE_HOME, lstRoles.get(0).getRole());
				session.setAttribute(Constans.SESSION_USER_NAME, usersService.getFullName(userId));
			}
			
		} catch (Exception e) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
			targetUrl = "/login?error=2";
		}
		
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/**
	 * Obtiene una lista de los perfiles asociados al usuario
	 * @param authentication
	 * @return los perfiles
	 */
	private List<Role> getRoles(Authentication authentication) {
		List<Role> lstRoles = new ArrayList<Role>();
		List<String> lstIdRoles = getIdRoles(authentication);
		List<Role> lstAllRoles = rolesDAO.findAll();
		
		for(Role role : lstAllRoles){
			if(lstIdRoles.contains(role.getRole())){
				lstRoles.add(role); 
			}
		}
		
		return lstRoles;
	}

	/**
	 * Obtiene una lista con los id de los perfiles asociados asociados al usuario
	 * @param authentication
	 * @return los ids 
	 */
	private List<String> getIdRoles(Authentication authentication) {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> lstIdRoles = new ArrayList<String>();

		for (GrantedAuthority a : authorities) {
			lstIdRoles.add(a.getAuthority());
		}
		
		return lstIdRoles;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
}
