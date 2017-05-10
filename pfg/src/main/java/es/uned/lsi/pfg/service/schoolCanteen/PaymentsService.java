/**
 * 
 */
package es.uned.lsi.pfg.service.schoolCanteen;

import java.util.List;

import es.uned.lsi.pfg.model.Payment;

/**
 * Servicio de pagos
 * @author Carlos Navalon Urrea
 *
 */
public interface PaymentsService {

	/**
	 * Obtiene un listado de pagos por alumno
	 * @param student alumno
	 * @return
	 */
	public List<Payment> getPaymentsByStudent(Integer student);

	/**
	 * Inserta un pago
	 * @param payment pago
	 */
	public void insert(Payment payment);

	/**
	 * Obtiene un pago por id
	 * @param id id de pago
	 * @return pago
	 */
	public Payment getPayment(Integer id);

}
