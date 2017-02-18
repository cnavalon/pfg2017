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
			.authoritiesByUsernameQuery("select id,role from users where id=?");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
      	.antMatchers("/static/**","/login").permitAll()
        .antMatchers("/*/adm/**").hasAuthority(Constans.ROLE_ADMIN)
        .antMatchers("/*/tch/**").hasAuthority(Constans.ROLE_TEACHER)
        .antMatchers("/*/std/**").hasAuthority(Constans.ROLE_STUDENT)
        .antMatchers("/*/par/**").hasAuthority(Constans.ROLE_PARENT)
        .anyRequest().authenticated()
        .and().exceptionHandling().accessDeniedPage("/accessDenied")
        .and().formLogin().loginPage("/login")
        .successHandler(customSuccessHandler)
        .failureHandler(customFailureHandler)
        .usernameParameter("username").passwordParameter("password")
        .and().csrf();
    }
    
//	private void findAllRolesCombinations(HttpSecurity http, String[] roles) throws Exception {
//		List<Integer> lstIndexes = new ArrayList<Integer>();
//		lstIndexes.add(0);
//		findRoleCombinations(http, lstIndexes, roles);
//	}
//	
//	private void findRoleCombinations(HttpSecurity http, List<Integer> lstIndexes, String[] rolesArray) throws Exception{
//		addRule(http, lstIndexes, rolesArray);
//		for(int i = lstIndexes.get(lstIndexes.size()-1)+1; i < rolesArray.length; i++ ){
//			List<Integer> newLstIndex = new ArrayList<Integer>(lstIndexes);
//			newLstIndex.add(i);
//			findRoleCombinations(http, newLstIndex, rolesArray);
//		}
//	}
//
//	private void addRule(HttpSecurity http, List<Integer> lstIndexes, String[] rolesArray) throws Exception {
//		List<String> lstRoles = new ArrayList<String>();
//		for(int index : lstIndexes){
//			lstRoles.add(rolesArray[index]);
//		}
//		String[] roles = new String[lstRoles.size()];
//		lstRoles.toArray(roles);
//		String code = "";
//		for(String role : roles){
//			code += role.substring(5) + "_";
//		}
//		code = code.substring(0, code.length()-1).toLowerCase();
//		if(roles.length == 1){
//			http.requestMatchers().antMatchers("/*/" + code + "/**").and().authorizeRequests().anyRequest().hasAuthority(roles[0]);
//		} else {
//			http.requestMatchers().antMatchers("/*/" + code + "/**").and().authorizeRequests().anyRequest().hasAnyAuthority(roles);
//		}
//	}
	
	@Bean
    public ShaPasswordEncoder passwordEncoder(){
    	return new ShaPasswordEncoder();
    }
}
