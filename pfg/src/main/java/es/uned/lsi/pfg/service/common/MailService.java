/**
 * 
 */
package es.uned.lsi.pfg.service.common;

import java.util.List;

/**
 * Servicio de correo electronico
 * @author Carlos Navalon Urrea
 *
 */
public interface MailService {

	/**
	 * Envia un correo electronico
	 * @param email destinatario 
	 * @param subject asunto
	 * @param text cuerpo del correo
	 * @throws Exception
	 */
	public void sendMail(String email, String subject, String text) throws Exception;
	
	/**
	 * Envia un correo a una lista de destinatarios, todos ellos en BCC
	 * @param lstEmail listado de destinatarios
	 * @param subject asunto
	 * @param text cuerpo del correo
	 * @throws Exception
	 */
	public void sendListMail(List<String> lstEmail, String subject, String text) throws Exception;
	
}
