/**
 * 
 */
package es.uned.lsi.pfg.service.common;

import es.uned.lsi.pfg.model.Payment;
import es.uned.lsi.pfg.model.Student;

/**
 * Servicio PDF
 * @author Carlos Navalon Urrea
 *
 */
public interface PDFService {
	
	/**
	 * Genera un recibo de pago en pdf
	 * @param payment pago
	 * @param student alumno
	 * @return recibo de pago en pdf 
	 * @throws Exception
	 */
	public byte[] generateReceipt(Payment payment, Student student) throws Exception;

}
