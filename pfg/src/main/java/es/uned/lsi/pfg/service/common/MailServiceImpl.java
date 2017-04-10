/**
 * 
 */
package es.uned.lsi.pfg.service.common;

import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Implementacion de servicio de correo electronico
 * @author Carlos Navalon Urrea
 *
 */
@Service
public class MailServiceImpl implements MailService {
	
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	
	@Autowired
	private Environment env;
	
	private String user;
	private String pass;
	
	private Session session;
	
	@PostConstruct
	public void init(){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		user = env.getProperty("email.user");
		pass = env.getProperty("email.pass");
		
		session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user,pass);
				}
			});
	}
	
	@Override
	public void sendMail(String email, String subject, String text) throws Exception {
		logger.debug("sendMail: " + email + ", " + subject + ", " + text);
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(email));
			message.setSubject(subject);
			message.setText(text);
	
			Transport.send(message);
		} catch (Exception e) {
			logger.error("ERROR enviando mensaje: " + email + ", " + subject + ", " + text + ". " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void sendListMail(List<String> lstEmail, String subject, String text) throws Exception {
		logger.debug("sendListMail: " + lstEmail + ", " + subject + ", " + text);
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			for(String email : lstEmail){
				message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(email));
			}
			message.setSubject(subject);
			message.setText(text);
	
			Transport.send(message);
		} catch (Exception e) {
			logger.error("ERROR enviando mensaje: " + lstEmail + ", " + subject + ", " + text + ". " + e.getMessage());
			throw e;
		}
	}
	

}
