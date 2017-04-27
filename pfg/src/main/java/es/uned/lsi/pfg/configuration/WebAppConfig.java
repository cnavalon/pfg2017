package es.uned.lsi.pfg.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * Configuracion de la aplicacion
 * @author Carlos Navalon Urrea
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "es.uned.lsi.pfg")
@PropertySource("classpath:application.properties")
public class WebAppConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private Environment env;
	
	/**
     * Configurador tiles
     */
    @Bean
    public TilesConfigurer tilesConfigurer(){
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/tiles.xml"});
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }
 
    /**
     * Configurar solucionador de vistas
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
    }
     
    /**
     * Configurar controlador de rescursos estáticos
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
    
    /**
     * Configurador de recursos properties 
     */
    @Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
    
    /**
     * Configuracion de internacionalizacion
     */
    @Bean
    public MessageSource messageSource(){
    	  ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    	  messageSource.setBasename("/i18n/messages");
    	  messageSource.setDefaultEncoding("UTF-8");
    	  Properties prop = new Properties();
    	  prop.setProperty("/i18n/messages.properties", "UTF-8");
    	  messageSource.setFileEncodings(prop);
    	  messageSource.setCacheSeconds(1);
    	  return messageSource;
    }
    
    /**
     * Configuracion de locale
     */
    @Bean
    public LocaleResolver localeResolver(){
    	SessionLocaleResolver  resolver = new SessionLocaleResolver ();
    	return resolver;
    } 
    
    /**
     * Configuracion de interceptor de idiomas
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        registry.addInterceptor(interceptor);
    } 
    
    /**
     * Configuracion de controlador multipart
     */
    @Bean
    public MultipartResolver multipartResolver(){
    	CommonsMultipartResolver multipartResolver =  new CommonsMultipartResolver();
    	multipartResolver.setMaxUploadSize(10485760); //10 MB
    	return multipartResolver;
    }
    
    /**
     * Configuracion de servicio de correo electronico
     */
    @Bean 
    public JavaMailSender mailResolver() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername(env.getProperty("email.user"));
        javaMailSender.setPassword(env.getProperty("email.pass"));
        
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        javaMailSender.setJavaMailProperties(props);
        
        return javaMailSender;
    }
    
}
