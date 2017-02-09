package es.uned.lsi.pfg.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @author Carlos Navalon Urrea
 * Gestor de autenticaciones fallidas
 */
@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler implements AuthenticationFailureHandler {

	/**
	 * Redirege segun el codigo de error
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
            setDefaultFailureUrl("/login?error=0");
        }
		else if (exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
			setDefaultFailureUrl("/login?error=0");
		}
		else 
			setDefaultFailureUrl("/login?error=2");
		super.onAuthenticationFailure(request, response, exception);
	}
	
}
