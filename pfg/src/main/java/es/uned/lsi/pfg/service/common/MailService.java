/**
 * 
 */
package es.uned.lsi.pfg.service.common;

import java.util.List;

import es.uned.lsi.pfg.model.Person;

/**
 * Servicio de correo electronico
 * @author Carlos Navalon Urrea
 *
 */
public interface MailService {

	/**
	 * Envia un correo electronico
	 * @param person destinatario 
	 * @param subject asunto
	 * @param text cuerpo del correo
	 */
	public void sendMail(Person person, String subject, String text) throws Exception;
	
	/**
	 * Envia un correo a una lista de destinatarios, todos ellos en BCC
	 * @param lstPerson listado de destinatarios
	 * @param subject asunto
	 * @param text cuerpo del correo
	 */
	public void sendListMail(List<Person> lstPerson, String subject, String text) throws Exception;
	
}
