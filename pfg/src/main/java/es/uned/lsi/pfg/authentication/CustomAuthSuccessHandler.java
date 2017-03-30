/**
 * 
 */
package es.uned.lsi.pfg.authentication;

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

import es.uned.lsi.pfg.model.Role;
import es.uned.lsi.pfg.service.users.RolesService;
import es.uned.lsi.pfg.service.users.UsersService;
import es.uned.lsi.pfg.utils.Constans;

/**
 * Gestor de autenticaciones correctas
 * @author Carlos Navalon Urrea
 */
@Component
public class CustomAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private static Logger logger = LoggerFactory.getLogger(CustomAuthSuccessHandler.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private UsersService usersService;

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		String targetUrl = "/";
		try {
			HttpSession session = request.getSession(true);
			Role role = getRole(authentication);
			String idUser = authentication.getName();
			
			if(role == null){
				logger.warn("The user has not any role");
				new SecurityContextLogoutHandler().logout(request, response, authentication);
				targetUrl = "/login?error=1";
			} else {
				session.setAttribute(Constans.SESSION_ROLE, role);
				session.setAttribute(Constans.SESSION_USER_NAME, usersService.getFullNameByUser(idUser, role.getIdRole()));
			}
			
		} catch (Exception e) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
			targetUrl = "/login?error=2";
		}
		
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/**
	 * Obtiene el perfil del usuario
	 * @param authentication
	 * @return el perfil del usuario
	 */
	private Role getRole(Authentication authentication) {
		List<String> lstIdRoles = getIdRoles(authentication);
		List<Role> lstAllRoles = rolesService.getAllRoles();
		
		for(Role role : lstAllRoles){
			if(lstIdRoles.contains(role.getIdRole())){
				return role;
			}
		}
		
		return null;
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
