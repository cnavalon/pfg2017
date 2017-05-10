/**
 * 
 */
package es.uned.lsi.pfg.dao.schoolCanteen;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.uned.lsi.pfg.dao.AbstractJpaDao;
import es.uned.lsi.pfg.model.Payment;

/**
 * Implementacion de repositorio de pagos
 * @author Carlos Navalon Urrea
 *
 */
@Repository
public class PaymentsDAOImpl extends AbstractJpaDao implements PaymentsDAO {

	private static final Logger logger = LoggerFactory.getLogger(PaymentsDAOImpl.class);
	
	@Override
	public Payment findById(Integer id) {
		logger.debug("findById: " + id);
		try {
			return em.createNamedQuery(Payment.Q_FIND_BY_ID, Payment.class)
			.setParameter("id", id)
			.getSingleResult();
		} catch (NoResultException e) {
			logger.debug("Empty results");
			return null;
		} catch (Exception e) {
			logger.error("Error recuperando pago: " + id, e);
			throw e;
		}
	}

	@Override
	public List<Payment> findByStudent(Integer student) {
		logger.debug("findByStudent: " + student);
		List<Payment> lstlstPayments = new ArrayList<Payment>();
		try {
			lstlstPayments =  em.createNamedQuery(Payment.Q_FIND_BY_STUDENT, Payment.class)
			.setParameter("student", student)
			.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando pagos por alumno: " + student, e);
			throw e;
		}
		return lstlstPayments;
	}

	@Override
	public void insert(Payment payment) {
		logger.debug("insert: " + payment);
		try {
			em.persist(payment);
		} catch (Exception e) {
			logger.error("Error insertando pago: " + payment, e);
			throw e;
		}
	}

}
