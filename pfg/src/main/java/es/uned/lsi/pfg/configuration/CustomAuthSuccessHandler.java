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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import es.uned.lsi.pfg.utils.Constans;

/**
 * @author Carlos Navalon Urrea
 *
 */
@Component
public class CustomAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private static Logger logger = LoggerFactory.getLogger(CustomAuthSuccessHandler.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		
		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			logger.warn("Can't redirect");
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/*
	 * This method extracts the roles of currently logged-in user and returns
	 * appropriate URL according to his/her role.
	 */
	private String determineTargetUrl(Authentication authentication) {
		List<String> roles = getRoles(authentication);
		return determineUrl(roles);
	}

	private List<String> getRoles(Authentication authentication) {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<String> roles = new ArrayList<String>();

		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}
		return roles;
	}

	
	private String determineUrl(List<String> roles) {
		String role = "";
		String url = "";
		
		for(int i=0; i < Constans.LST_ROLES.length; i++){
			if(roles.contains(Constans.LST_ROLES[i])){
				role = Constans.LST_ROLES[i];
				break;
			}
		}
		
		switch (role) {
		case Constans.ROLE_ADMIN:
			url = "/home/a/";
			break;
		case Constans.ROLE_TEACHER:
			url = "/home/t/";
			break;
		case Constans.ROLE_STUDENT:
			url = "/home/s/";
			break;
		case Constans.ROLE_PARENT:
			url = "/home/p/";
			break;
		default:
			url = "/login?error=2";
			break;
		}
		
		return url;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
}
