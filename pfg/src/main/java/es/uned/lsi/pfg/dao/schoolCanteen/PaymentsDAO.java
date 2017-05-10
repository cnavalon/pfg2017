/**
 * 
 */
package es.uned.lsi.pfg.dao.schoolCanteen;

import java.util.List;

import es.uned.lsi.pfg.model.Payment;

/**
 * Repositorio de pagos
 * @author Carlos Navalon Urrea
 *
 */
public interface PaymentsDAO {
	
	/**
	 * Encuentra un pago por id
	 * @param id id del pago
	 * @return el pago
	 */
	public Payment findById(Integer id);

	/**
	 * Encuentra un listado de pagos por alumno
	 * @param student id de alumno
	 * @return listado de pagos
	 */
	public List<Payment> findByStudent(Integer student);
	
	/**
	 * Inserta un pago
	 * @param payment pago
	 */
	public void insert(Payment payment);
}
