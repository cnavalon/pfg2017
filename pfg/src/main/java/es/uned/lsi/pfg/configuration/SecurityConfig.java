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

import es.uned.lsi.pfg.authentication.CustomAuthFailureHandler;
import es.uned.lsi.pfg.authentication.CustomAuthSuccessHandler;
import es.uned.lsi.pfg.model.Constans;

/**
 * Configuracion de seguridad
 * @author Carlos Navalon Urrea
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
			.usersByUsernameQuery("select id_user,password,enabled from users where id_user=?")
			.authoritiesByUsernameQuery("select id_user,id_role from users where id_user=?");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
      	.antMatchers("/static/**","/login").permitAll()
        .antMatchers("/*/adm/**").hasAuthority(Constans.ROLE_ADMIN)
        .antMatchers("/*/tch/**").hasAuthority(Constans.ROLE_TEACHER)
        .antMatchers("/*/std/**").hasAuthority(Constans.ROLE_STUDENT)
        .antMatchers("/*/par/**").hasAuthority(Constans.ROLE_PARENT)
        .antMatchers("/*/emp/**").hasAnyAuthority(Constans.ROLE_ADMIN,Constans.ROLE_TEACHER)
        .antMatchers("/*/tut/**").hasAnyAuthority(Constans.ROLE_PARENT,Constans.ROLE_TEACHER)
        .antMatchers("/*/atp/**").hasAnyAuthority(Constans.ROLE_ADMIN,Constans.ROLE_TEACHER,Constans.ROLE_PARENT)
        .antMatchers("/*/ap/**").hasAnyAuthority(Constans.ROLE_ADMIN,Constans.ROLE_PARENT)
        .anyRequest().authenticated()
        .and().exceptionHandling().accessDeniedPage("/accessDenied")
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
