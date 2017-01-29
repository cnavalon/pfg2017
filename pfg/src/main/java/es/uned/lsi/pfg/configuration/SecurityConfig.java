/**
 * 
 */
package es.uned.lsi.pfg.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import es.uned.lsi.pfg.utils.Constans;

/**
 * @author Carlos Navalon Urrea
 * Configuracion de seguridad
 */
@Configuration
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomAuthSuccessHandler customSuccessHandler;
	
	@Autowired
	CustomAuthFailureHandler customFailureHandler;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
			.usersByUsernameQuery("select id,password,enabled from users where id=?")
			.authoritiesByUsernameQuery("select userid,role from user_role where userid=?");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
      	.antMatchers("/static/**").permitAll()
        .antMatchers("/*/adm/**").hasAuthority(Constans.ROLE_ADMIN)
        .antMatchers("/*/tch/**").hasAuthority(Constans.ROLE_TEACHER)
        .antMatchers("/*/std/**").hasAuthority(Constans.ROLE_STUDENT)
        .antMatchers("/*/par/**").hasAuthority(Constans.ROLE_PARENT)
        .antMatchers("/*/all/**").hasAnyAuthority(Constans.ROLE_ADMIN,Constans.ROLE_TEACHER,Constans.ROLE_STUDENT,Constans.ROLE_PARENT)
        .anyRequest().authenticated()
        .and().exceptionHandling().accessDeniedPage("/accessDenied")
        .and().formLogin().loginPage("/login").permitAll()
        .successHandler(customSuccessHandler)
        .failureHandler(customFailureHandler)
        .usernameParameter("username").passwordParameter("password")
        .and().csrf();
    }
    
	@Bean
    public ShaPasswordEncoder passwordEncoder(){
    	return new ShaPasswordEncoder();
    }
}
