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
 *
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
//        auth.jdbcAuthentication()inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
			.usersByUsernameQuery("select id,password,enabled from users where id=?")
			.authoritiesByUsernameQuery("select userid,role from user_role where userid=?");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
      	.antMatchers("/login").permitAll()
      	.antMatchers("/static/**").permitAll()
      	.antMatchers("/").authenticated()
        .antMatchers("/*/*a*/").access("hasRole('" + Constans.ROLE_ADMIN +"')")
        .antMatchers("/*/*t*/").access("hasRole('" + Constans.ROLE_TEACHER +"')")
        .antMatchers("/*/*s*/").access("hasRole('" + Constans.ROLE_STUDENT +"')")
        .antMatchers("/*/*p*/").access("hasRole('" + Constans.ROLE_PARENT +"')")
        .and().formLogin().loginPage("/login")
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
