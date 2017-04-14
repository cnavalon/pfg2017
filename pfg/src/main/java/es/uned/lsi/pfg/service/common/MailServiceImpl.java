/**
 * 
 */
package es.uned.lsi.pfg.service.common;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import es.uned.lsi.pfg.model.Person;

/**
 * Implementacion de servicio de correo electronico
 * @author Carlos Navalon Urrea
 *
 */
@Service
public class MailServiceImpl implements MailService {
	
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private HttpServletRequest servletContext;
	
	@Override
	public void sendMail(Person person, String subject, String text) throws Exception {
		logger.debug("sendMail: " + person + ", " + subject + ", " + text);
		if(person.getEmail() != null){
			send(InternetAddress.parse(person.getEmail()), subject, text);
		}
	}
	
	private void send(InternetAddress[] address, String subject, String text) throws Exception {
		text += getFootEmail();
		MimeMessage email = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(email);
		if(address.length == 1){
			helper.setTo(address);
		} else {
			helper.setBcc(address);
		}
		helper.setSubject(subject);
		helper.setText(text, true);
		
		mailSender.send(email);
		
		logger.info("Correo electronico enviado a : " + address);
	}

	@Override
	public void sendListMail(List<Person> lstPerson, String subject, String text) throws Exception {
		logger.debug("sendListMail: " + lstPerson + ", " + subject + ", " + text);
		List<InternetAddress> lstEmail = new ArrayList<InternetAddress>();
		for(Person person : lstPerson){
			if(person.getEmail() != null){
				try {
					lstEmail.add(new InternetAddress(person.getEmail()));
				} catch (Exception e) {
					logger.error("Error parsing email: " + person.getEmail());
				}
			}
		}
		send(lstEmail.toArray(new InternetAddress[0]), subject, text);
	}
	
	private String getFootEmail(){
		String url = servletContext.getServerName() + ":" + servletContext.getServerPort() + servletContext.getContextPath();
		String link = "<html><a href='http://"+ url + "/'>web</a></html>";
		return messageSource.getMessage("common.email.foot", new String[]{link}, LocaleContextHolder.getLocale());
	}
	

}
