/**
 * 
 */
package es.uned.lsi.pfg.service.schoolCanteen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uned.lsi.pfg.dao.schoolCanteen.PaymentsDAO;
import es.uned.lsi.pfg.model.Constans;
import es.uned.lsi.pfg.model.Payment;

/**
 * Implementacion de servicio de pagos
 * @author Carlos Navalon Urrea
 *
 */
@Service
public class PaymentsServiceImpl implements PaymentsService {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentsServiceImpl.class);

	@Autowired
	private PaymentsDAO paymentsDAO;
	
	@Override
	public List<Payment> getPaymentsByStudent(Integer student) {
		logger.debug("getPaymentsByStudent: " + student);
		List<Payment> lstPayments = new ArrayList<Payment>();
		List<Payment> lstPaids = paymentsDAO.findByStudent(student);
		for(int i=0; i < 12; i++){
			if(i != 6 && i!= 7){
				Payment payment = getPayment(lstPaids, i);
				lstPayments.add(payment);
			}
		}
		return lstPayments;
	}

	private Payment getPayment(List<Payment> lstPaids, int i) {
		for(Payment payment : lstPaids){
			if(payment.getMonth() == i)
				return payment;
		}
		Payment newPayment = new Payment();
		newPayment.setMonth(i);
		newPayment.setAmount(Constans.PAYMENT_FEE);
		return newPayment;
	}

	@Transactional
	@Override
	public void insert(Payment payment) {
		logger.debug("insert: " + payment);
		payment.setInsertDate(new Date());
		paymentsDAO.insert(payment);
		
	}
	
	@Override
	public Payment getPayment(Integer id) {
		logger.debug("getPayment");
		return paymentsDAO.findById(id);
	}

	
	
}
